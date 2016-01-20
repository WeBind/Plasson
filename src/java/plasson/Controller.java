/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plasson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;
import plasson.ResultsHelper.ComputedResults;



/**
 *
 * @author Nicolas
 */
public class Controller implements BrokerHelper.BrokerListener{

    public int responseCounter = 0;
    public Timer scenarioTimeout;
    public BrokerHelper myBroker;


    public Controller(){
        myBroker = new BrokerHelper(Config.exchangeName, Config.broadcastName, Config.callbackName, this);
        scenarioTimeout = new Timer();

    }

    public void fillModel(String xml) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException{

        XMLHelper xmlHelper = new XMLHelper();

        XMLHelper.ConsumerReturn consumers = xmlHelper.getConsumers(xml);
        XMLHelper.ProviderReturn providers = xmlHelper.getProviders(xml);

        Model.getInstance().setConsumers(consumers.getConsumers());
        Model.getInstance().setProviders(providers.getProviders());
        Model.getInstance().setScenarioEndTime(consumers.getMaxConsumerEndTime()+providers.getMaxProviderEndTime());

    }

    public void deployScenario(){
        DeployHelper deployHelper = new DeployHelper();
        HashMap<String, Consumer> consumers = Model.getInstance().getConsumers();
        HashMap<String, Provider> providers = Model.getInstance().getProviders();


        for(String consumerId : consumers.keySet()){
            deployHelper.deployConsumer(consumerId, Config.exchangeName, Config.broadcastName, Config.callbackName);
        }
        for(String providerId : providers.keySet()){
            deployHelper.deployProvider(providerId, Config.exchangeName, Config.broadcastName, Config.callbackName);
        }

    }

    public void startScenario() throws IOException{

        JsonHelper jsonBuilder = new JsonHelper();
        myBroker.connect();
        HashMap<String, Consumer> consumers = Model.getInstance().getConsumers();
        HashMap<String, Provider> providers = Model.getInstance().getProviders();

        myBroker.setUpCallbackQueue();

        for(String consumerId : consumers.keySet()){
            myBroker.send(jsonBuilder.getJson(consumers.get(consumerId)).toString(),consumerId);
        }
        for(String providerId : providers.keySet()){
            myBroker.send(jsonBuilder.getJson(providers.get(providerId)).toString(),providerId);
        }

        myBroker.sendBroadcast(jsonBuilder.getGoJson().toString());
        startTimeout();
    }

    public void receiveResult(Results results) {

        System.out.println("Got a result for id "+results.getId());

        if(Model.getInstance().getConsumers().containsKey(results.getId())){
            Consumer consumer = Model.getInstance().getConsumers().get(results.getId());
            consumer.setResults(results);
            ResultsHelper resultHelper = new ResultsHelper();
            String idProvider = Config.providerPrefix+consumer.getProvider();
            ComputedResults cr = resultHelper.computeConsumerResults(
                    consumer,
                    Model.getInstance().getProviders().get(idProvider).getResponseTime());
            consumer.setLostRequests(cr.getLostRequest());
            consumer.setAverageRequestResponseTime(cr.getAverageRequestResponseTime());
            if(this.isResultsTotallyReceived()){
                System.out.println("Scenario's end !");
                System.out.println("Computing results...");
                this.stopScenario();
            }
        }else{
            System.out.println("Error: not found corresponding results");

        }


    }

    public boolean isResultsTotallyReceived(){
        boolean isResultsTotallyReceived = true;
        int i = 0;
        Iterator iter = Model.getInstance().getConsumers().entrySet().iterator();

        while(isResultsTotallyReceived && iter.hasNext()){
            Map.Entry ent = (Map.Entry) iter.next();
            //La clé de la HashMap
            String key = (String)ent.getKey();
            //La Valeur de la HashMap
            Consumer consumer = (Consumer)ent.getValue();
            if(consumer.getResults()==null){
                isResultsTotallyReceived = false;
            }
            i++;
        }

        return isResultsTotallyReceived;
    }

    private void computeGlobalResults() {
        ResultsHelper rh = new ResultsHelper();
        System.out.println("Drop rate = "+rh.getDropRate(Model.getInstance().getConsumers()));
        System.out.println("Average response time = "+rh.getResponseTime(Model.getInstance().getConsumers()));

    }

    private void startTimeout(){
        System.out.println("Start Timer");
        scenarioTimeout.schedule(new TimerTask() {
        @Override
        public void run() {
            stopScenario();
        }
        }, 15*1000);
    }

    private void stopScenario(){
        try {
            System.out.println("Stop scenario");
            stopTimeout();
            myBroker.close();
            computeGlobalResults();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void stopTimeout(){
        scenarioTimeout.cancel();
    }


}

