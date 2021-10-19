import utils.db.DataBase;

public class Test {

    private DataBase dataBase = new DataBase();

    @org.junit.Test
    public void testDB() {
        String sqlServerAddress = "";
        String sqlDatabaseName = "";
        String user = "";
        String password = "";

        String script = "";

        dataBase.connectToDatabase(sqlServerAddress, sqlDatabaseName, user, password)
                .executeQuery(script)
                .closeConnectionToDatabase();

    }
}
