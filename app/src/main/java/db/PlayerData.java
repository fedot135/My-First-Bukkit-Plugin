package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.HashMap;

public class PlayerData<T> {



    private Connection connection;
    private PreparedStatement preparedStatement;

    private HashMap<String, T> tempData = new HashMap<String, T>();
    private String tableName;


    public PlayerData(String url, String tableName) {

        try {connection = DriverManager.getConnection(url);}
        catch (SQLException err) {err.printStackTrace();}

        this.tableName = tableName;

    }



    /*
        Operations with DB
    */
    public void sendDataToDBAll(String path) {
        for (String key : tempData.keySet()) { 
            sendDataToDB(key, path);
        }
    }

    public void sendDataToDB(String name, String path) {
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO " + tableName + " (name, "+path+") VALUES (?, ?) ON CONFLICT (name) DO UPDATE SET "+path+" = ?");
            preparedStatement.setString(1, name);
            {
                T val = tempData.get(name);
                preparedStatement.setObject(2, val);
                preparedStatement.setObject(3, val);
            }
            preparedStatement.executeUpdate();
            
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }



    /*
        Operations with Temp Data
    */
    public void setData(String name, T value) {
        if (tempData.get(name) != null) tempData.replace(name, value);
        else tempData.put(name, value);
    }

    public T getData(String name) {
        return tempData.get(name);
    }

    public void removeData(String name) {
        tempData.remove(name);
    }
}