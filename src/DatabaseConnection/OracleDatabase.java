package src.DatabaseConnection;

import java.sql.*;

public class OracleDatabase {

    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    static final String USER = "gameAdmin";
    static final String PASS = "gameAdminpassword3";

    static Connection conn = null;

    static PreparedStatement preparedStatement;
    public OracleDatabase(){

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

    public static void createUser(String userName, String email) throws SQLException {
        preparedStatement.setString(1,userName);
        preparedStatement.setString(2,email);
    }
    public static boolean insertUser(String userName, String email) throws SQLException{

        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println("Inserting user");
            prepareStatementforRetrieval();
            if(retrieveUserInfo(email)==true){
                return false;
            }
            prepareStatementforInsert();
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


    public static void prepareStatementforInsert() throws SQLException {
         preparedStatement = conn.prepareStatement("insert into user_info values(?,?,0,'0')");
    }

    public static void prepareStatementforRetrieval() throws SQLException{
        preparedStatement = conn.prepareStatement("select * from user_info where email = ?");
    }


    public static void prepareStatementforCheckingLevelPlayed() throws SQLException {
        preparedStatement = conn.prepareStatement("select * from level_info where email = ? and level_number = ?");
    }

    public static boolean retrieveUserInfo(String email) throws SQLException{
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
                if(rs.getString("HAS_STARTED_GAME")=="0"){
                    PlayerInfo.hasStartedGame = true;
                }
                rs.close();
                prepareStatementforCheckingLevelPlayed();
                preparedStatement.setString(1, PlayerInfo.email);
                preparedStatement.setString(2,"1");
                rs = preparedStatement.executeQuery();
                if(rs.next()){
                    PlayerInfo.DormHighScore = rs.getInt("score");
                    PlayerInfo.DormLeastTime = rs.getInt("time_taken");
                }

                preparedStatement.setString(1, PlayerInfo.email);
                preparedStatement.setString(2,"2");
                rs = preparedStatement.executeQuery();
                if(rs.next()){
                    PlayerInfo.ClassroomHighScore = rs.getInt("score");
                    PlayerInfo.ClassroomHighScore = rs.getInt("time_taken");
                }

                preparedStatement.setString(1, PlayerInfo.email);
                preparedStatement.setString(2,"3");
                rs = preparedStatement.executeQuery();
                if(rs.next()){
                    PlayerInfo.LibraryHighScore = rs.getInt("score");
                    PlayerInfo.LibraryLeastTime = rs.getInt("time_taken");
                }

                preparedStatement.setString(1, PlayerInfo.email);
                preparedStatement.setString(2,"4");
                rs = preparedStatement.executeQuery();
                if(rs.next()){
                    PlayerInfo.CDSHighScore = rs.getInt("score");
                    PlayerInfo.CDSLeastTime = rs.getInt("time_taken");
                }

                preparedStatement.setString(1, PlayerInfo.email);
                preparedStatement.setString(2,"5");
                rs = preparedStatement.executeQuery();
                if(rs.next()){
                    PlayerInfo.DormV2HighScore = rs.getInt("score");
                    PlayerInfo.DormV2LeastTime = rs.getInt("time_taken");
                }




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