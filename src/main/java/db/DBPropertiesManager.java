/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import dbconfig.ConfigEnum;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author timon_kaufmann
 */
public class DBPropertiesManager {

     private final Map<String, String> propertiesMap = new HashMap<>();
    
    private DBPropertiesManager() {
    }
    
    public static DBPropertiesManager getInstance()
    {
        return DBPropertiesManagerHolder.INSTANCE;
    }
    
    private static class DBPropertiesManagerHolder{
        private static final DBPropertiesManager INSTANCE = new DBPropertiesManager();
    }
    
    public void createProperties(ConfigEnum config)
    {
        load("src/main/java/dbconfig/postgres.db.properties");
    }
    
    public void load(String filename)
    {
        File file = new File(filename);
        String filepath = file.getAbsolutePath();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(filepath)));
            
            String line;
            while((line = reader.readLine())!= null)
            {
                String[] tokens = line.split("=");
               propertiesMap.put(tokens[0], tokens[1]);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    public String getUrl()
    {
        return propertiesMap.get("db.url");
    }
    
    public String getDriver()
    {
        return propertiesMap.get("db.driver");
    }
    public String getUsername()
    {
        return propertiesMap.get("db.username");
    }
    public String getPassword()
    {
        return propertiesMap.get("db.password");
    }

    @Override
    public String toString() {
        return "DBPropertiesManager{" + "propertiesMap=" + propertiesMap + '}';
    }
    
    public static void main(String[] args) {
        try {
            DBPropertiesManager dbpm = DBPropertiesManager.getInstance();
            dbpm.createProperties(ConfigEnum.POSTGRES);
            System.out.println(dbpm.toString());
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    
    
}
