package src.setup;

public class PlayerInfo {
    public static String username;
    public static String email;
    public static int gameProgress;

    public static int DormHighScore;
    public static int DormLeastTime;

    public static int ClassroomHighScore;
    public static int ClassroomLeastTime;

    public static int LibraryHighScore;
    public static int LibraryLeastTime;

    public static int CDSHighScore;
    public static int CDSLeastTime;

    public static int DormV2HighScore;
    public static int DormV2LeastTime;


    public PlayerInfo(int gameProgress){
        this.gameProgress = gameProgress;
    }
}
