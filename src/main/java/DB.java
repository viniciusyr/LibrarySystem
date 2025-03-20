import exception.DBException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {

    public static Connection conn = null;

    public static Properties loadProperties(){
        try(FileInputStream fs = new FileInputStream("src/db.properties")){
            Properties props = new Properties();
            props.load(fs);
            return props;
        } catch (IOException e){
            throw new DBException(e.getMessage());
        }
    }

    public static Connection getConnection(){
        if(conn == null){
            try{
                Properties props = DB.loadProperties();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);
            } catch (SQLException e) {
                throw new DBException("Error to connect: "+ e.getMessage());
            }
        }
        return conn;
    }

    public static void closeConnection(Connection conn){
        if(conn != null){
            try{
                conn.close();
            } catch (SQLException e) {
                throw new DBException("Error close Connection: " + e.getMessage());
            }
        }
    }

    public static void closeStatement(Statement st){
        if (st != null){
            try{
                st.close();
            } catch (SQLException e){
                throw new DBException("Error close Statement: " + e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs){
        if(rs != null){
            try{
                rs.close();
            } catch (SQLException e){
                throw new DBException("Error close ResultSet: " + e.getMessage());
            }
        }
    }
}

