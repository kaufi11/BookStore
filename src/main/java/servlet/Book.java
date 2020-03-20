/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * 
 * @author timon_kaufmann
 */
public class Book {
    private int book_id;
    private String title;
    private String url;
    private double price;
    private int publisher_id;
    private String isbn;

    public Book() {
    }

    public Book(int book_id, String title, String url, double price, int publisher_id, String isbn) {
        this.book_id = book_id;
        this.title = title;
        this.url = url;
        this.price = price;
        this.publisher_id = publisher_id;
        this.isbn = isbn;
    }
    
    public Book(ResultSet rs) throws SQLException {
        this.book_id = rs.getInt("book_id");
        this.title = rs.getString("title");
        this.url = rs.getString("url");
        this.price = rs.getDouble("price");
        this.publisher_id = rs.getInt("publisher_id");
        this.isbn = rs.getString("isbn");
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPublisher_id() {
        return publisher_id;
    }

    public void setPublisher_id(int publisher_id) {
        this.publisher_id = publisher_id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.book_id;
        hash = 29 * hash + Objects.hashCode(this.title);
        hash = 29 * hash + Objects.hashCode(this.url);
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        hash = 29 * hash + this.publisher_id;
        hash = 29 * hash + Objects.hashCode(this.isbn);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        if (this.book_id != other.book_id) {
            return false;
        }
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        if (this.publisher_id != other.publisher_id) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        if (!Objects.equals(this.isbn, other.isbn)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
