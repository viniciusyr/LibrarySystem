package database;

import exception.DBException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {

    public static Connection conn = null;

    public static Properties loadProperties(){
        try(FileInputStream fs = new FileInputStream("db.properties")){
            Properties props = new Properties();
            props.load(fs);
            return props;
        } catch (IOException e){
            throw new DBException(e.getMessage());
        }
    }

    public static Connection getConnection(){
        try{
            Properties props = DB.loadProperties();
            String url = props.getProperty("dburl");
            return DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            throw new DBException("Error to connect: "+ e.getMessage());
        }
    }
}

