/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

 
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane; 
import servlet.Book;
import servlet.Publisher;
import sql.SQLEnum;

/**
 * 
 * @author timon_kaufmann
 */
public class DBAccess {

    private DBAccess() {
    }

    public static DBAccess getInstance() {
        return AccessDBHolder.INSTANCE;
    }

    private static class AccessDBHolder {

        private static final DBAccess INSTANCE = new DBAccess();
    }

    private final DBManager dbm = DBManager.getInstance();

    public List getAllPublisher() throws SQLException {
        List<Publisher> liste = new ArrayList<>();
        Statement stmt = dbm.createStatement();
        ResultSet rs = stmt.executeQuery(SQLEnum.STMT_ALL_PUBLISHER.getSql());

        while (rs.next()) {
            Publisher publisher = new Publisher(rs);
            System.out.println(publisher.toString());
            liste.add(publisher);
        }
        stmt.close();
        return liste;
    }
    
    public List<Book> findBookByPublisherId(Integer publisherid) throws SQLException
    {
        List<Book> books = new ArrayList<>();
        PreparedStatement pstmt = dbm.createPreparedStatement("SELECT * FROM book WHERE publisher_id = ?");
        pstmt.setInt(1, publisherid);
       ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            Book book = new Book(rs);
            System.out.println(book.toString());
            books.add(book);
        }
        pstmt.close(); 
        return books;
    }

    public static void main(String[] args) throws SQLException {
        DBAccess dba = getInstance(); 
        System.out.println(dba.findBookByPublisherId(37).toString());
    }

}
