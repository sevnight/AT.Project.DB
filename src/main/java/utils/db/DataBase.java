package utils.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {

    /**
     * Строка подключения к базе данных.
     * Имеет 4 параметра: 1. Адресс сервера; 2. Наименование базы; 3. Логин; 4. Пароль
     */
    private static final String CONNECTION_STRING = "jdbc:sqlserver://%s;database=%s;user=%s;password=%s;";

    private Connection connection;

    /**
     * Устанавливает соединение с сервером базы данных.
     *
     * @param sqlDatabaseName имя базы данных
     * @param user            логин пользователя
     * @param password        пароль
     * @return ссылка на экземпляр этого класса
     */
    public DataBase connectToDatabase(String sqlServerAddress, String sqlDatabaseName, String user, String password) {
        if (connection == null) {
            String connectionString =
                    String.format(CONNECTION_STRING, sqlServerAddress, sqlDatabaseName, user, password);


            try {
                connection = DriverManager.getConnection(connectionString);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    public void closeConnectionToDatabase() {
        try {
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    /**
     * Выполняет SQL-запрос без получения данных
     *
     * @param script текст SQL-запроса
     * @return результат выполнения запроса ResultSet
     */
    public DataBase executeQuery(String script) {
        try {
            Statement statement = connection.createStatement();
            statement.execute(script);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }
}
