import exception.DBException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DB {
    public static Properties loadProperties(){
        try(FileInputStream fs = new FileInputStream("src/db.properties")){
            Properties props = new Properties();
            props.load(fs);
            return props;
        } catch (IOException e){
            throw new DBException(e.getMessage());
        }
    }
}