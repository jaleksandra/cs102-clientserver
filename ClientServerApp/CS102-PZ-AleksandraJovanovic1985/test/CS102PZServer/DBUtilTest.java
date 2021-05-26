/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CS102PZServer;

import CS102PZCommon.Message;
import CS102PZCommon.MessageList;
import CS102PZCommon.Status;
import CS102PZCommon.User;
import CS102PZCommon.UserList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Aleksandra
 */
public class DBUtilTest {
    
    public DBUtilTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of loginUserQuery method, of class DBUtil.
     */
    @Test
    public void testLoginUserQuery() {
        System.out.println("Testiranje loginUserQuery-a");
        User user = new User("test4", "test4");
        boolean expResult = true;
        boolean result = DBUtil.loginUserQuery(user);
        assertEquals(expResult, result);
        user = new User("test", "");
        expResult = false;
        result = DBUtil.loginUserQuery(user);
        assertEquals(expResult, result);
    }

    /**
     * Test of registerUserQuery method, of class DBUtil.
     */
    @Test
    public void testRegisterUserQuery() {
        System.out.println("Testiranje registerUserQuery-a");
        User user = new User("test4", "test4");
        boolean expResult = false;
        boolean result = DBUtil.registerUserQuery(user);
        assertEquals(expResult, result);
        user = new User("test2", "test");
        expResult = false;
        result = DBUtil.registerUserQuery(user);
        assertEquals(expResult, result);

    }

    /**
     * Test of sendMessageQuery method, of class DBUtil.
     */
    @Test
    public void testSendMessageQuery() {
        System.out.println("Testiranje sendMessageQuery-a");
        Message message = new Message("test4", "test4", "test4");
        boolean expResult = true;
        boolean result = DBUtil.sendMessageQuery(message);
        assertEquals(expResult, result);
    }

    /**
     * Test of changeStatusQuery method, of class DBUtil.
     */
    @Test
    public void testChangeStatusQuery() {
        System.out.println("Testiranje changeStatusQuery-a");
        User user = new User("test4");
        user.setStatus(Status.AWAY);
        boolean expResult = true;
        boolean result = DBUtil.changeStatusQuery(user);
        assertEquals(expResult, result);
        user = new User("test4");
        user.setStatus(Status.ONLINE);
        expResult = true;
        result = DBUtil.changeStatusQuery(user);
        assertEquals(expResult, result);
        user = new User("test4");
        user.setStatus(Status.OFFLINE);
        expResult = true;
        result = DBUtil.changeStatusQuery(user);
        assertEquals(expResult, result);
    }

    /**
     * Test of listUsersQuery method, of class DBUtil.
     */
    @Test
    public void testListUsersQuery() {
        
    }

    /**
     * Test of listMessagesQuery method, of class DBUtil.
     */
    @Test
    public void testListMessagesQuery() {
        
    }
    
}
