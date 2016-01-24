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
public class ControllerTest extends TestCase {
    
    public ControllerTest(String testName) {
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
     * Test of fillModel method, of class Controller.
     */
    public void testFillModel() throws Exception {
        System.out.println("fillModel");
        String xml = "";
        Controller instance = new Controller();
        instance.fillModel(xml);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deployScenario method, of class Controller.
     */
    public void testDeployScenario() {
        System.out.println("deployScenario");
        Controller instance = new Controller();
        instance.deployScenario();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of startScenario method, of class Controller.
     */
    public void testStartScenario() throws Exception {
        System.out.println("startScenario");
        Controller instance = new Controller();
        instance.startScenario();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of receiveResult method, of class Controller.
     */
    public void testReceiveResult() {
        System.out.println("receiveResult");
        Results results = null;
        Controller instance = new Controller();
        instance.receiveResult(results);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
