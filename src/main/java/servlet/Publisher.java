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
public class Publisher {
    private int publisher_id;
    private String name;
    private String url;

    public Publisher(int publisher_id, String name, String url) {
        this.publisher_id = publisher_id;
        this.name = name;
        this.url = url;
    }
    public Publisher(ResultSet rs) throws SQLException {
        this.publisher_id = rs.getInt("publisher_id");
        this.name = rs.getString("name");
        this.url = rs.getString("url");
    }

    public Publisher() {
    }

    public int getPublisher_id() {
        return publisher_id;
    }

    public void setPublisher_id(int publisher_id) {
        this.publisher_id = publisher_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.publisher_id;
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + Objects.hashCode(this.url);
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
        final Publisher other = (Publisher) obj;
        if (this.publisher_id != other.publisher_id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Book{" + "publisher_id=" + publisher_id + ", name=" + name + ", url=" + url + '}';
    }
    
    
    
    
}
