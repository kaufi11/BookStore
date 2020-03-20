/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import dbconfig.ConfigEnum;
import dbconfig.DBProperties;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
/**
 * 
 * @author timon_kaufmann
 */
public class DBManager {
    private DBPropertiesManager dbpm = DBPropertiesManager.getInstance();
    private Connection con;

    private DBManager() {
        try { 
            Class.forName("org.postgresql.Driver");
            this.con = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/booksDB", "postgres", "Kaufmann1978");
            System.out.println("Fertig geladen und verbunden");
        } catch (Exception e) {
            System.out.println("Fehler beim Verbinden");
        }
    }
    
    public static DBManager getInstance()
    {
        return DBManagerHolder.INSTANCE;
    }
    
    public static class DBManagerHolder{
        private static final DBManager INSTANCE = new DBManager();
    }
   
    
    public Statement createStatement() throws SQLException
    {
        return con.createStatement();
    }
    
    public PreparedStatement createPreparedStatement(String sql, int... params) throws SQLException
    {
        switch(params.length)
        {
            case 0: return con.prepareStatement(sql);
            default: return con.prepareStatement(sql, params[0]);
        }
    }
    
    public void close() throws SQLException
    {
        con.close();   
    }
    
    public Connection loadAndConnect() throws Exception {
        Connection con = null; 

        Class.forName(DBProperties.DRIVER);
        con = DriverManager.getConnection(
                DBProperties.URL,
                DBProperties.USERNAME,
                DBProperties.PASSWORD
        );
        System.out.println("Geladen und Connection!!");
        return con;
    }
    public int readGeneratedKeys(Statement stmt) throws Exception
    {
        ResultSet rs = stmt.getGeneratedKeys();
        if(rs.next())
        {
            int id = rs.getInt(1);
            return id;
        }  
        throw new Exception("No generated KeyId");
    }
    
    public static void main(String[] args) {
        try {
            DBManager dbm = DBManager.getInstance();
        } catch (Exception e) {
        }
    }
}
