
package CS102PZCommon;

import java.io.Serializable;

/**
 *
 * @author Aleksandra
 */

/**
 * Job enum koristi se za razlikovanje zahteva od Servera
 */

public enum Job implements Serializable {
    LOGIN, REGISTER, MESSAGE, STATUS, LIST_USERS, GET_MESSAGES, KEEP_ONLINE

}
