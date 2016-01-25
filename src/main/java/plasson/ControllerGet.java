/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plasson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.xml.ws.WebServiceContext;
import plasson.ResultsHelper.ComputedResults;

/**
 *
 * @author Nicolas
 */
public class ControllerGet implements BrokerHelper.BrokerListener{


    Logger lg = java.util.logging.Logger.getLogger(Config.tag);


    private final String contextName = Config.contextName;
    private int responseCounter = 0;
    private ServletContext myContext;
    private BrokerHelper myBroker;
    private Model myModel;
    private final String CLEAN_UP_PATH = "/opt/Plasson/petals/cleanup.sh";
    private String timelineResults = null;



    public ControllerGet(ServletContext svcCtx) throws EmptyContextException{
        //NULL because no one wants to listen results for now
        myBroker = new BrokerHelper(Config.exchangeName, Config.broadcastName, Config.callbackName, this);
        lg.log(Level.INFO, "Getting Context");
        myContext = svcCtx;
        if (myContext.getAttribute(contextName) != null) {
            myModel = (Model) myContext.getAttribute(contextName);
        } else {
            throw new EmptyContextException("[ControllerGet] Null context");
        }

    }



    public String listenToResults(){
        try {
            lg.log(Level.INFO, "Connecting to RMQ");
            myBroker.connect();
            lg.log(Level.INFO, "Setting up queue callback");
            myBroker.setUpCallbackQueue();
        } catch (IOException ex) {
            Logger.getLogger(ControllerGet.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(this.timelineResults == null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ControllerGet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return timelineResults;
    }


    // Method implemented from the BrokerListener interface
    // This method is called whenever the broker receive a result
    // Computes the received results and fills the model
    // If all the results have been received, it stops the scenario
    public void receiveResult(Results results) {

        System.out.println("Got a result for id " + results.getId());
        lg.log(Level.INFO, "Receiving results for "+ results.getId());
        if (myModel.getConsumers().containsKey(results.getId())) {
            Consumer consumer = myModel.getConsumers().get(results.getId());
            consumer.setResults(results);
            ResultsHelper resultHelper = new ResultsHelper();
            String idProvider = Config.providerPrefix + consumer.getProvider();
            ComputedResults cr = resultHelper.computeConsumerResults(
                    results,
                    myModel.getProviders().get(idProvider).getResponseTime());
            consumer.setLostRequests(cr.getLostRequest());
            consumer.setAverageRequestResponseTime(cr.getAverageRequestResponseTime());
            //It is the broker which tell us the end of the results
            //if (this.isResultsTotallyReceived()) {
            //    System.out.println("Scenario's end !");
            //    System.out.println("Computing results...");
            //    this.stopScenario();
            //}
        } else {
            lg.log(Level.INFO, "Error: not found corresponding results");
            System.out.println("Error: not found corresponding results");

        }


    }

    // Check if all consumers have sent a result
    private boolean isResultsTotallyReceived() {
        boolean isResultsTotallyReceived = true;
        int i = 0;
        Iterator iter = myModel.getConsumers().entrySet().iterator();

        while (isResultsTotallyReceived && iter.hasNext()) {
            Map.Entry ent = (Map.Entry) iter.next();
            //La clé de la HashMap
            String key = (String) ent.getKey();
            //La Valeur de la HashMap
            Consumer consumer = (Consumer) ent.getValue();
            if (consumer.getResults() == null) {
                isResultsTotallyReceived = false;
            }
            i++;
        }

        return isResultsTotallyReceived;
    }

    // Compute the results once the scenario is done
    // using a ResultsHelper, it computes :
    // - Drop rate
    // - Average response time
    private void computeGlobalResults() {
        ResultsHelper rh = new ResultsHelper();
        System.out.println("Drop rate = " + rh.getDropRate(myModel.getConsumers()));
        System.out.println("Average response time = " + rh.getResponseTime(myModel.getConsumers()));
        System.out.println("Total sent messages = " + rh.getTotalSentMessages(myModel.getConsumers()));


    }

    // Start the timeout
    // If the scenario lasts to long, it stop the scenario
    // No more implemented Timeout
    private void startTimeout() {
        System.out.println("Start Timer");
        //scenarioTimeout.schedule(new TimerTask() {

        //    @Override
         //   public void run() {
        //        stopScenario();
        //    }
       // }, Model.getInstance().getScenarioEndTime() + 10000);
    }

    // Stops the timeout
    // Closes RabbitMQ connection
    // Computes results
    private void stopScenario() {
        try {
            System.out.println("Stop scenario");
            lg.log(Level.INFO, "Stopping Scenario");

            //stopTimeout();
            lg.log(Level.INFO, "Closing RMQ");

            myBroker.close();
            lg.log(Level.INFO, "Computing global results");
            computeGlobalResults();
            lg.log(Level.INFO, "Computing timeline");
            timelineResults = (new XMLHelper()).writeTimelineToXML(setTimeline());
            lg.log(Level.INFO, "Cleaning providers");
            cleanup();
        } catch (IOException ex) {
            Logger.getLogger(ControllerPost.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    public Timeline setTimeline() {
        //TODO change 30 to scenario end time
        Timeline timeline = new Timeline(10, myModel.getScenarioEndTime());
        timeline.setIntervalsConsumersRates(myModel.getConsumers(), myModel.getProviders());
        //timeline.display();

        return timeline;
    }

    private void cleanup() {
        try {
            List<String> cleanup = new ArrayList();
            cleanup.add(CLEAN_UP_PATH);
            cleanup.addAll(myModel.getProviders().keySet());
            Process p = new ProcessBuilder(cleanup).start();
            p.waitFor();
        } catch (InterruptedException ex) {
            Logger.getLogger(ControllerPost.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ControllerPost.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void endOfResults() {
        lg.log(Level.INFO, "End of results");
        stopScenario();
    }

}
