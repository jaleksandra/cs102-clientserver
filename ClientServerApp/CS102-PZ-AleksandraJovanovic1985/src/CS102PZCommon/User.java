
package CS102PZCommon;

import java.io.Serializable;

/**
 *
 * @author Aleksandra
 */

/**
 * User klasa za slanje korisnika preko mreze
 */

public class User implements Serializable{

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Status status;

    /**
     * konstruktor klase User 
     *
     * @param username korisnicko ime
     */
    public User(String username) {
        this.username = username;
        this.password = " ";
        this.firstName = " ";
        this.lastName = " ";
        this.status = Status.OFFLINE;
    }

    /**
     * konstruktor klase User 
     *
     * @param username korisnicko ime
     * @param password lozinka
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.firstName = " ";
        this.lastName = " ";
        this.status = Status.OFFLINE;
    }

    /**
     * konstruktor klase User 
     *
     * @param username korisnicko ime
     * @param password lozinka
     * @param status status korisnika
     */
    public User(String username, String password, Status status) {
        this.username = username;
        this.password = password;
        this.firstName = " ";
        this.lastName = " ";
        this.status = status;
    }

    /**
     * konstruktor klase User
     *
     * @param username korisnicko ime
     * @param firstName ime
     * @param lastName prezime
     * @param status status korisnika
     */
    public User(String username, String firstName, String lastName, Status status) {
        this.username = username;
        this.password = "";
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
    }

    /**
     * konstruktor klase User
     *
     * @param username korisnicko ime
     * @param password lozinka
     * @param firstName ime
     * @param lastName prezime
     */
    public User(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = Status.OFFLINE;
    }

    /**
     * konstruktor klase User
     *
     * @param username korisnicko ime
     * @param password lozinka
     * @param firstName ime
     * @param lastName prezime
     * @param status status korisnika
     */
    public User(String username, String password, String firstName, String lastName, Status status) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
    }

    /**
     * getter metoda za korisnicko ime
     *
     * vraca korisnicko ime
     */
    public String getUsername() {
        return username;
    }

    /**
     * setter metoda za korisnicko ime
     *
     * @param username korisnicko ime
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * getter metoda za lozinku
     *
     * @return lozinku
     */
    public String getPassword() {
        return password;
    }

    /**
     * setter metoda za lozinku
     *
     * @param password lozinka
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * getter metoda za ime
     *
     * vraca ime
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * setter metoda za ime
     *
     * @param firstName ime
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * getter metoda za prezime
     *
     * vraca prezime
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * setter metoda za prezime
     *
     * @param lastName prezime
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * getter metoda za status korisnika
     *
     * vraca status korisnika
     */
    public Status getStatus() {
        return status;
    }

    /**
     * setter metoda za status korisnika
     *
     * @param status status korisnika
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * predefinisanje toString metode
     *
     * vraca string reprezentaciju objekta
     */
    @Override
    public String toString() {
        return "User{" + "username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName + ", status=" + status + '}';
    }

    
}
