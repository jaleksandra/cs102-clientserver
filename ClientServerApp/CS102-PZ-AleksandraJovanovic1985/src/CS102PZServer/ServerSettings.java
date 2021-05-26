
package CS102PZServer;

/**
 *
 * @author Aleksandra
 */

import CS102PZClient.ClientSettings;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ServerSettings klasa sluzi za skladistenje podesavanja koriscenih od strane Server klase
 */

public class ServerSettings {

    /**
     * podesavanja baze podataka
     */
    private static final String DB_HOSTNAME = "localhost";
    private static final String DB_PORT = "3306";
    private static final String DB_NAME = "cs102-pz";
    private static final String URL = "jdbc:mysql://" + DB_HOSTNAME + ":" + DB_PORT + "/" + DB_NAME;
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "password";

    private static Connection getConnection(String httplocalhostphpmyadminPMAURL1db_structur) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
  
  Connection connection1;  
  Connection connection = ServerSettings.getConnection("http://localhost/phpmyadmin/#PMAURL-1:db_structure.php?db=cs102-pz&table=&server=1&target=&token=ea9c94f1ad639244aaf3953624e0c431");
    
    /**
     * SERVER ime hosta
     */
    private static final String SERVER_HOSTNAME = "localhost";
    /**
     * JOB_SERVER podesavanja
     */
    private static final int SERVER_PORT = 8089;

    /**
     *
     * GETTER za DB_PORT
     *
     * @return DB_PORT
     */
    public static String getDB_PORT() {
        return DB_PORT;
    }

    /**
     * GETTER za DB_NAME
     *
     * @return DB_NAME
     */
    public static String getDB_NAME() {
        return DB_NAME;
    }

    /**
     * GETTER za URL
     *
     * @return URL
     */
    public static String getURL() {
        return URL;
    }

    /**
     * GETTER za DB_USERNAME
     *
     * @return DB_USERNAME
     */
    public static String getDB_USERNAME() {
        return DB_USERNAME;
    }

    /**
     * GETTER za DB_PASSWORD
     *
     * @return DB_PASSWORD
     */
    public static String getDB_PASSWORD() {
        return DB_PASSWORD;
    }

    /**
     * GETTER za SERVER_PORT
     *
     * @return
     */
    public static int getSERVER_PORT() {
        return SERVER_PORT;
    }

    /**
     * GETTER za DB_HOSTNAME
     *
     * @return DB_HOSTNAME
     */
    public static String getDB_HOSTNAME() {
        return DB_HOSTNAME;
    }

    /**
     * GETTER za SERVER_HOSTNAME
     *
     * @return SERVER_HOSTNAME
     */
    public static String getSERVER_HOSTNAME() {
        return SERVER_HOSTNAME;
    }

    public ServerSettings() throws SQLException {
        this.connection1 = DriverManager.getConnection("http://localhost/phpmyadmin/#PMAURL-1:db_structure.php?db=cs102-pz&table=&server=1&target=&token=ea9c94f1ad639244aaf3953624e0c431" );
    }

    
}

