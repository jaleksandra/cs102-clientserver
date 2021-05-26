
package CS102PZServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DBConn class is a class that stores database connection
 */

/** public class DBConn {

    private Connection conn = null;

    /**
     * DBConn constructor
     */
   /* public DBConn() {
        try {
            this.conn = DriverManager.getConnection(ServerSettings.getURL(), ServerSettings.getDB_USERNAME(), ServerSettings.getDB_PASSWORD());
            System.out.println("Database connected!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    /**
     * getter method for connection
     *
     * @return
     */
   /* public Connection getConn() {
        return conn;
    }
}*/
public class DBConn {
   public Connection Connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/#PMAURL-1:db_structure.php?db=cs102-pz&table=&server=1&target=&token=ea9c94f1ad639244aaf3953624e0c431","root","");
            return con;
        }
        catch(Exception ex){
            System.out.println(ex);       
        }
        return null;
       }

    Connection getConn() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
