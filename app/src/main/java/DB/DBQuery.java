package DB;

public class DBQuery {
    public static final String DATABASE_NAME = "db_project";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "dbo_login";

    public static final String KEY_ID = "id";
    public static final String KEY_USER_NAME = "user_name";
    public static final String KEY_PASSWORD= "password";


    public static final String SELECT_DATA = "SELECT * FROM ";
//    public static final String INSERT_DATA = "INSERT INTO  ";

    public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
            KEY_ID + " integer primary key, " +
            KEY_USER_NAME + " TEXT, " +
            KEY_PASSWORD + " TEXT) ";
}

