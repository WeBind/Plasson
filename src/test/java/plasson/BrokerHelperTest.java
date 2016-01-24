/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plasson;

import junit.framework.TestCase;

/**
 *
 * @author Nicolas
 */
public class BrokerHelperTest extends TestCase {
    
    public BrokerHelperTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of connect method, of class BrokerHelper.
     */
    public void testConnect() throws Exception {
        System.out.println("connect");
        BrokerHelper instance = null;
        instance.connect();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of send method, of class BrokerHelper.
     */
    public void testSend() throws Exception {
        System.out.println("send");
        String message = "";
        String routingKey = "";
        BrokerHelper instance = null;
        instance.send(message, routingKey);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sendBroadcast method, of class BrokerHelper.
     */
    public void testSendBroadcast() throws Exception {
        System.out.println("sendBroadcast");
        String message = "";
        BrokerHelper instance = null;
        instance.sendBroadcast(message);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUpCallbackQueue method, of class BrokerHelper.
     */
    public void testSetUpCallbackQueue() {
        System.out.println("setUpCallbackQueue");
        BrokerHelper instance = null;
        instance.setUpCallbackQueue();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of close method, of class BrokerHelper.
     */
    public void testClose() throws Exception {
        System.out.println("close");
        BrokerHelper instance = null;
        instance.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
