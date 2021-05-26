
package CS102PZClient;

/**
 *
 * @author Aleksandra
 */

/**
 * ClientSettings je staticka klasa koja cuva podesavanja klijenta
 */

public class ClientSettings {

    /**
     * SERVER HOSTNAME
     */
    private static final String SERVER_HOSTNAME = "localhost";
    /**
     * JOB_SERVER PODESAVANJA
     */
    private static final int SERVER_PORT = 8089;

    /**
     * GETTER za SERVER_HOSTNAME
     *
     * @return SERVER_HOSTNAME
     */
    public static String getSERVER_HOSTNAME() {
        return SERVER_HOSTNAME;
    }

    /**
     * GETTER za SETVER_PORT
     *
     * @return SERVER_PORT
     */
    public static int getSERVER_PORT() {
        return SERVER_PORT;
    }
    
}
