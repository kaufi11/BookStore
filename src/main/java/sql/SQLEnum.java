/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;

/**
 * 
 * @author timon_kaufmann
 */
public enum SQLEnum {
    STMT_ALL_PUBLISHER("SELECT * FROM publisher");
    private String sql;

    private SQLEnum(String sql) {
        this.sql = sql;
    }

    public String getSql() {
        return sql;
    }
    
    
}
