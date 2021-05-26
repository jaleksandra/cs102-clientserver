
package CS102PZServer;

import CS102PZCommon.Message;
import CS102PZCommon.MessageList;
import CS102PZCommon.Status;
import CS102PZCommon.User;
import CS102PZCommon.UserList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DBUtil klasa se koristi za manipulaciju nad bazom
 */

/**
 *
 * @author Aleksandra
 */
public class DBUtil {

    // konekcija
    private static final Connection CONN = new DBConn().getConn();

    /**
     * loginUserQuery je metoda koja izvrsava zahtev
     *
     * @param user korisnik za prijavljivanje
     * 
     * @return vraca uspesno ili neuspesno
     */
    public static boolean loginUserQuery(User user) {
        String sql = "SELECT username,password FROM user WHERE username = ?";
        PreparedStatement pstmt;
        ResultSet resultSet;
        try {
            pstmt = CONN.prepareStatement(sql);
            pstmt.setString(1, user.getUsername());
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString("username").equals(user.getUsername())) {
                    return resultSet.getString("password").equals(user.getPassword());
                }
            }
        } catch (SQLException ex) {
            System.out.println("Los SQL " + ex);

        }
        return false;
    }

    /**
     * registerUserQuery je metoda koja izvrsava zahtev
     *
     * @param user
     * @return
     */
    public static boolean registerUserQuery(User user) {
        String sql = "SELECT username FROM user WHERE username = ?";
        PreparedStatement pstmt;
        ResultSet resultSet;
        try {
            pstmt = CONN.prepareStatement(sql);
            pstmt.setString(1, user.getUsername());
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString("username").equals(user.getUsername())) {
                    return false;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Los SQL");
        }
        String sqlInsert = "INSERT INTO user (ID, username, password, firstName, lastName, status) VALUES (NULL, ?, ?, ?, ?, 0)";
        try {
            pstmt = CONN.prepareStatement(sqlInsert);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getFirstName());
            pstmt.setString(4, user.getLastName());
            pstmt.executeQuery();
            return true;
        } catch (SQLException ex) {
            System.out.println("Los SQL");
        }
        return false;
    }

    /**
     * sendMessageQuery je metoda koja izvrsava zahtev
     *
     * @param message
     * @return
     */
    public static boolean sendMessageQuery(Message message) {
        String sql = "INSERT INTO message (messageID, senderID, recipientID, messageText) VALUES (NULL, (SELECT ID from user WHERE username = ?), (SELECT ID from user WHERE username = ?), ?)";
        PreparedStatement pstmt;
        try {
            pstmt = CONN.prepareStatement(sql);
            pstmt.setString(1, message.getSenderUsername());
            pstmt.setString(2, message.getRecipientUsername());
            pstmt.setString(3, message.getMessageText());
            pstmt.executeQuery();
            return true;
        } catch (SQLException ex) {
            System.out.println("Los SQL");
        }
        return false;
    }

    /**
     * changeStatusQuery je metoda koja izvrsava zahtev
     *
     * @param user
     * @return
     */
    public static boolean changeStatusQuery(User user) {
        String sql = "UPDATE user SET status = ? WHERE username = ? ";
        PreparedStatement pstmt;
        try {
            pstmt = CONN.prepareStatement(sql);
            switch (user.getStatus()) {
                case OFFLINE:
                    pstmt.setString(1, Status.OFFLINE.toString());
                    break;
                case ONLINE:
                    pstmt.setString(1, Status.ONLINE.toString());
                    break;
                case AWAY:
                    pstmt.setString(1, Status.AWAY.toString());
                    break;
                default:
                    pstmt.setString(1, Status.OFFLINE.toString());
                    break;
            }
            pstmt.setString(2, user.getUsername());
            pstmt.executeQuery();
            return true;
        } catch (SQLException ex) {
            System.out.println("Los SQL");
        }
        return false;
    }

    /**
     * listUserQuery je metoda koja izvrsava zahtev
     *
     * @return
     */
    public static UserList listUsersQuery() {
        String sql = "SELECT username,firstName,lastName,status from user";
        Statement stmt;
        ResultSet resultSet;
        UserList userList = new UserList();
        String username;
        String firstName;
        String lastName;
        Status status;
        try {
            stmt = CONN.createStatement();
            resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                username = resultSet.getString("username");
                firstName = resultSet.getString("firstName");
                lastName = resultSet.getString("lastName");
                if (resultSet.getString("status").equals(Status.OFFLINE.toString())) {
                    status = Status.OFFLINE;
                } else if (resultSet.getString("status").equals(Status.ONLINE.toString())) {
                    status = Status.ONLINE;
                } else if (resultSet.getString("status").equals(Status.AWAY.toString())) {
                    status = Status.AWAY;
                } else {
                    status = Status.OFFLINE;
                }

                userList.getUserList().add(new User(username, firstName, lastName, status));
            }
        } catch (SQLException ex) {
            System.out.println("Los SQL");
        }
        return userList;
    }

    /**
     * listMessageQuery je metoda koja izvrsava zahtev
     *
     * @param user
     * @return
     */
    public static MessageList listMessagesQuery(User user) {
        MessageList tempMessageList = new MessageList(user);
        String sql = "SELECT a.username AS senderUsername, b.username AS recipientUsername,message.messageID,message.messageText FROM message JOIN user a on a.ID = message.senderID JOIN user b on b.ID = message.recipientID WHERE message.senderID = (SELECT user.ID FROM user WHERE username = ? ) OR message.recipientID = (SELECT user.ID FROM user WHERE username = ? ) ORDER BY message.messageID";
        PreparedStatement pstmt;
        ResultSet resultSet;
        try {
            pstmt = CONN.prepareStatement(sql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getUsername());
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Message tempMessage = new Message(resultSet.getString("senderUsername"), resultSet.getString("recipientUsername"), resultSet.getString("messageText"));
                tempMessage.setMessageID(resultSet.getLong("messageID"));
                tempMessageList.getMessageList().add(tempMessage);
            }

        } catch (SQLException ex) {
            System.out.println("Los SQL");
        }
        return tempMessageList;
    }
    
}
