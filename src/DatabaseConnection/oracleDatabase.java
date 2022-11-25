package src.DatabaseConnection;

import java.sql.*;

public class oracleDatabase {

    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    static final String USER = "gameAdmin";
    static final String PASS = "gameAdminpassword3";

    Connection conn = null;

    PreparedStatement preparedStatement;
    public oracleDatabase(){

        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println("Creating statement");
            stmt = conn.prepareStatement("desc user_info");

        }catch (SQLDataException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();

        }
    }

    public void createUser(String userName, String email) throws SQLException {
        preparedStatement.setString(1,userName);
        preparedStatement.setString(2,email);
    }
    public boolean insertUser(String userName, String email) throws SQLException{

        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println("Inserting user");
            if(retrieveUserInfo(email)==true){
                return false;
            }
            prepareStatementforInsert();
            if(retrieveUserInfo(email)==true){
                return false;
            }
            createUser(userName,email);

            preparedStatement.executeUpdate();
            System.out.println("User inserted");
            preparedStatement.close();
            conn.close();
            PlayerInfo.email = email;
            PlayerInfo.username = userName;
            PlayerInfo.gameProgress = 0;
            System.out.println("finished execution");
        }catch (SQLDataException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();

        }

        return true;
    }


    public void prepareStatementforInsert() throws SQLException {
         preparedStatement = conn.prepareStatement("insert into user_info values(?,?,0,'0')");
    }

    public void prepareStatementforRetrieval() throws SQLException{
        preparedStatement = conn.prepareStatement("select * from user_info where email = ?");
    }




    public boolean retrieveUserInfo(String email) throws SQLException{
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println("Getting user");
            prepareStatementforRetrieval();
            preparedStatement.setString(1,email);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                System.out.println(rs.toString());

                PlayerInfo.email = rs.getString("email");
                PlayerInfo.username = rs.getString("username");
                PlayerInfo.gameProgress = rs.getInt("levels_completed");
                rs.close();
                preparedStatement.close();
                conn.close();
                System.out.println("Got user");
            }else{
                return false;
            }
        }catch (SQLDataException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();

        }

        return true;
    }

}