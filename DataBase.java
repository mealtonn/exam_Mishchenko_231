package Project.sql_conection;

import  java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
    private final String host = "localhost";
    private final String port = "5432";
    private final String dbName = "BroneHotel";
    private final String login = "postgres";
    private final String password = "Shayrma223";

    private Connection dbCon;  //подключение бд

    private Connection getDBConnection() throws ClassNotFoundException, SQLException {
        String str = "jdbc:postgresql://" + host + ":" + port + "/" + dbName;
        Class.forName("org.postgresql.Driver");
        dbCon = DriverManager.getConnection(str, login, password);
        return dbCon;
    }

    public void isConnection() throws SQLException, ClassNotFoundException {
        dbCon = getDBConnection();
        if (dbCon.isValid(1000)) {
            System.out.println("Подключение успешно!");
        }
        else {
            System.out.println("Подключение не удачно!");
        }
    }

    public void createDb(String tableName) throws SQLException, ClassNotFoundException {
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (id INT PRIMARY KEY, nomer INT, name VARCHAR(50), bron BOOLEAN);";
        Statement statment = getDBConnection().createStatement();
        statment.executeUpdate(sql);
    }

    public void addHotel(String table, int id, int nomer, String name, boolean bron){
        try{
            Statement statment = getDBConnection().createStatement();
            int rows = statment.executeUpdate("INSERT  INTO " + table + "(id, nomer, name, bron) " + "VALUES (" + id +", " + nomer + ", '"+ name +"', "+ bron +");");
            System.out.println("Добавлено\n");
        }catch (SQLException | ClassNotFoundException e){
            System.out.println("Не удалось добавить" + e.getMessage());
        }
    }

    public void selectBroned(String tableName) throws SQLException, ClassNotFoundException {
        Statement statment = getDBConnection().createStatement();
        ResultSet resultSet = statment.executeQuery("SELECT * from "+ tableName +" WHERE bron = true;");
        while (resultSet.next()){
            int id = resultSet.getInt(1);
            int nomer = resultSet.getInt(2);
            String name = resultSet.getString(3);
            boolean bron = resultSet.getBoolean(4);
            System.out.printf("%d. %d. %s. %b \n", id, nomer, name, bron);
        }
    }

    public void selectnoBroned(String tableName) throws SQLException, ClassNotFoundException {
        Statement statment = getDBConnection().createStatement();
        ResultSet resultSet = statment.executeQuery("SELECT * from "+ tableName +" WHERE bron = false;");
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            int nomer = resultSet.getInt(2);
            String name = resultSet.getString(3);
            boolean bron = resultSet.getBoolean(4);
            System.out.printf("%d. %d. %s. %b \n", id, nomer, name, bron);
        }
    }


}
