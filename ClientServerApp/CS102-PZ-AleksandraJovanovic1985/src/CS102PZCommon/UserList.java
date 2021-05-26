
package CS102PZCommon;

import java.io.Serializable;
import java.util.ArrayList;
/**
 *
 * @author Aleksandra
 */

/**
 * UserList klasa sluzi za slanje liste korisnika kroz mrezu
 */

public class UserList implements Serializable{

    private ArrayList<User> userList;

    /**
     * UserList konstruktor
     */
    public UserList() {
        userList = new ArrayList<>();
    }

    /**
     * getter metoda za listu korisnika
     *
     * vraca listu korisnika
     */
    public ArrayList<User> getUserList() {
        return userList;
    }

    /**
     * setter metoda za listu korisnika
     *
     * @param userList lista korisnika
     */
    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }
    
}
