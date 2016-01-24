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
public class DeployHelperTest extends TestCase {
    
    public DeployHelperTest(String testName) {
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
     * Test of deployConsumer method, of class DeployHelper.
     */
    public void testDeployConsumer() {
        System.out.println("deployConsumer");
        String name = "";
        String exchangeName = "";
        String broadcast = "";
        String callback = "";
        DeployHelper instance = new DeployHelper();
        instance.deployConsumer(name, exchangeName, broadcast, callback);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deployProvider method, of class DeployHelper.
     */
    public void testDeployProvider() {
        System.out.println("deployProvider");
        String name = "";
        String exchangeName = "";
        String broadcast = "";
        String callback = "";
        DeployHelper instance = new DeployHelper();
        instance.deployProvider(name, exchangeName, broadcast, callback);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
