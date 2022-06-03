package Data;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

/**
 * Work with the database. The singleton pattern
 * prevents you from creating more than one object 
 * of this type. There are methods implementing:
 * <ul>
 * <li>Reading from file. 
 * <li>Saving data to a file.
 * <li>Gets the database.
 * <ul>
 * 
 * 
 * @author Vadim Ryzhov
 * @since 1.0
 */
public class JSonDB {

    private static JSonDB instance = null;
    private static String fileName = "data.json";
    private Gson gson;
    private DBStruct data;

    private JSonDB() throws InterruptedException, IOException {
        gson = new Gson();
        loadData();
    }

    public static JSonDB getInstance() throws InterruptedException, IOException { 
        if(instance == null)
            instance = new JSonDB();
        return instance;
    }
    /**
     * Reading data from a file. And writing all 
     * data to the variable data.
     * 
     * @throws InterruptedException
     * @throws IOException
     */
    public void loadData() throws InterruptedException, IOException {
        FileReader reader = new FileReader(fileName);
        data = gson.fromJson(reader, DBStruct.class);
    }
    /**
     * Saving data to a file. 
     * 
     * @throws InterruptedException
     * @throws IOException
     */
    public void saveData() throws InterruptedException, IOException {
        FileWriter writer = new FileWriter(fileName);
        gson.toJson(data, writer);
        writer.close();
    }
    /**
     * Get the entire database.
     * 
     * @return data - a variable in which the entire database is stored.
     * @throws InterruptedException
     * @throws IOException
     */
    public DBStruct getData()throws InterruptedException, IOException {
        return data;
    }
} 