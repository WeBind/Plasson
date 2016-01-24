/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plasson;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;
import plasson.ResultsHelper.ComputedResults;


/**
 *
 * @author Nicolas
 */
public class ResultsHelperTest extends TestCase {

            final String dir;

    
    public ResultsHelperTest(String testName) {
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
     * Test of getDropRate method, of class ResultsHelper.
     */
    public void testGetDropRate() {
        System.out.println("getDropRate");
        HashMap<String, Consumer> consumers = createConsumers();


        ResultsHelper instance = new ResultsHelper();
        float expResult = (15.0f+9.0f)/(82.0f+45.0f);
        float result = instance.getDropRate(consumers);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getResponseTime method, of class ResultsHelper.
     */
    public void testGetResponseTime() {
        System.out.println("getResponseTime");
        HashMap<String, Consumer> consumers = createConsumers();
        ResultsHelper instance = new ResultsHelper();
        float expResult = ((float)((6*(82-15))+(9*(45-9))))/((float)((82-15)+(45-9)));        
        float result = instance.getResponseTime(consumers);
        assertEquals(expResult, result, 0.0);
       
    }

    /**
     * Test of getTotalSentMessages method, of class ResultsHelper.
     */
    public void testGetTotalSentMessages() {
        System.out.println("getTotalSentMessages");
        HashMap<String, Consumer> consumers = createConsumers();
        ResultsHelper instance = new ResultsHelper();
        int expResult = 82+(82-15)+45+(45-9);
        int result = instance.getTotalSentMessages(consumers);
        assertEquals(expResult, result);
    }

    /**
     * Test of computeConsumerResults method, of class ResultsHelper.
     */
    public void testComputeConsumerResults() {
        try {
            System.out.println("computeConsumerResults");
            String consumerResultsPath = "";
            if (System.getProperty("os.name").toLowerCase().contains("windows".toLowerCase())) {
                consumerResultsPath = dir + "\\src\\test\\java\\resources\\testConsumer18.json";
            } else {
                consumerResultsPath = dir + "/src/test/java/resources/testConsumer18.json";
            }
            File consumerResultsFile = new File(consumerResultsPath);
            String receivedResults = new Scanner(consumerResultsFile).useDelimiter("\\Z").next();
            JsonHelper jh = new JsonHelper();
            Results results = jh.getResults(receivedResults);

            int providerResponseTime = 1;

            ResultsHelper instance = new ResultsHelper();
            ComputedResults expResult = new ComputedResults(1,2.5f);
            ComputedResults result = instance.computeConsumerResults(results, providerResponseTime);
            assertEquals(expResult.toString(), result.toString());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ResultsHelperTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private HashMap<String, Consumer> createConsumers(){
        HashMap<String, Consumer> consumers = new HashMap<String, Consumer>();
        Consumer consumer1 = new Consumer();
        Consumer consumer2 = new Consumer();

        Results results1 = new Results();
        results1.setSent(new ArrayList<Message>());
        results1.setReceived(new ArrayList<Message>());

        Results results2 = new Results();
        results2.setSent(new ArrayList<Message>());
        results2.setReceived(new ArrayList<Message>());



        for(int i = 0; i<82 ; i++){
            results1.getSent().add(new Message());
        }

        for(int i = 0; i<(82-15) ; i++){
            results1.getReceived().add(new Message());
        }

        for(int i = 0; i<45 ; i++){
            results2.getSent().add(new Message());
        }
        for(int i = 0; i<(45-9) ; i++){
            results2.getReceived().add(new Message());
        }


        consumer1.setResults(results1);
        consumer1.setLostRequests(15);
        consumer1.setAverageRequestResponseTime(6);
        consumer2.setResults(results2);
        consumer2.setLostRequests(9);
        consumer2.setAverageRequestResponseTime(9);


        consumers.put("consumer1",consumer1);
        consumers.put("consumer2",consumer2);

        return consumers;
    }

}
