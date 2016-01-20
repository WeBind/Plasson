/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plasson;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author Nicolas
 */
public class JsonHelperTest extends TestCase {

        final String dir;
    
    public JsonHelperTest(String testName) {
        super(testName);
        dir = System.getProperty("user.dir");

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
     * Test of getJson method, of class JsonHelper.
     */
    public void testGetJson_Consumer() {
        try {
            System.out.println("getJson");
            Consumer consumer = new Consumer(18, 1000, 100, 1, 300, 0);
            JsonHelper instance = new JsonHelper();
            JSONObject expResult = new JSONObject();
            expResult.put("type", "config");
            expResult.put("startingTime", 1000);
            expResult.put("size", 100);
            expResult.put("period", 1);
            expResult.put("duration", 300);
            expResult.put("provider", "provider0");
            JSONObject result = instance.getJson(consumer);
            System.out.println(expResult.toString());
            System.out.println(result.toString());
            assertEquals(expResult.toString(), result.toString());
            
        } catch (JSONException ex) {
            Logger.getLogger(JsonHelperTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getJson method, of class JsonHelper.
     */
    public void testGetJson_Provider() {
        try {
            System.out.println("getJson");
            Provider provider = new Provider(1, 50, 600);
            JsonHelper instance = new JsonHelper();
            JSONObject expResult = new JSONObject();
            expResult.put("type", "config");
            expResult.put("responseLength", 50);
            expResult.put("responseTime", 600);
            JSONObject result = instance.getJson(provider);
            assertEquals(expResult, result);
            
        } catch (JSONException ex) {
            Logger.getLogger(JsonHelperTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getGoJson method, of class JsonHelper.
     */
    public void testGetGoJson() {
        try {
            System.out.println("getGoJson");
            JsonHelper instance = new JsonHelper();
            JSONObject expResult = new JSONObject();
            expResult.put("type", "go");
            JSONObject result = instance.getGoJson();
            assertEquals(expResult, result);
        } catch (JSONException ex) {
            Logger.getLogger(JsonHelperTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getResults method, of class JsonHelper.
     */
    public void testGetResults() {
        try {
            System.out.println("getResults");
            String consumerResultsPath ="";
            if(System.getProperty("os.name").toLowerCase().contains("windows".toLowerCase()))
         {
            consumerResultsPath = dir + "\\src\\test\\java\\resources\\testConsumer148.json";
         }
         else
         {
            consumerResultsPath = dir + "/src/test/java/resources/testConsumer148.json";

         }
            File consumerResultsFile = new File(consumerResultsPath);
            String receivedResults = new Scanner(consumerResultsFile).useDelimiter("\\Z").next();
            JsonHelper instance = new JsonHelper();
            Results expResult = new Results();
            expResult.setId("consumer148");
            List<Message> sent = new ArrayList();
            sent.add(new Message("consumer18-01",5));
            sent.add(new Message("consumer18-02",10));
            expResult.setSent(sent);

            List<Message> received = new ArrayList();
            received.add(new Message("consumer18-01",11));
            received.add(new Message("consumer18-02",16));
            expResult.setReceived(received);

            Results result = instance.getResults(receivedResults);
            System.out.println(expResult.toString());
            System.out.println(result.toString());
            assertEquals(expResult.toString(), result.toString());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JsonHelperTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
