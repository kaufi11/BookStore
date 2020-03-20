/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import db.DBAccess;
import db.DBManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author timon_kaufmann
 */
public class BookstoreModel {
    
    private List<Publisher> publisher = new ArrayList<>();
    private List<Book> books = new ArrayList<>();
    private DBManager dbm;
    private DBAccess dba;
    
    private BookstoreModel() {
        dbm = DBManager.getInstance();
        dba = DBAccess.getInstance();
    }
    
    public static BookstoreModel getInstance() {
        return BookstoreModelHolder.INSTANCE;
    }
    
    private static class BookstoreModelHolder {

        private static final BookstoreModel INSTANCE = new BookstoreModel();
    }
    
    public List<Publisher> getAllPublisher() throws SQLException
    {
        return dba.getAllPublisher();
    }
    
    public List<Book> getAllBooks(int id) throws SQLException
    {
        return dba.findBookByPublisherId(id);
    }
    
    public static void main(String[] args) throws SQLException {
        BookstoreModel model = getInstance();
        
        System.out.println(model.getAllPublisher().toString());
    }
}
