package dbconfig;

/**
 * 
 * @author timon_kaufmann
 */
public enum ConfigEnum {
DERBY("dbconfig/derby.db.properties"),
POSTGRES("src/dbconfig/postgres.db.properties");

    private final String filename;

    private ConfigEnum(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

}
