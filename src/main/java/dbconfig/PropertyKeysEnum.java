package dbconfig;
/**
 * 
 * @author timon_kaufmann
 */
public enum PropertyKeysEnum {
    URL("db.url"),
    DRIVER("db.driver"),
    USERNAME("db.username"),
    PASSWORD("db.password");

    private String key;

    PropertyKeysEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

}
