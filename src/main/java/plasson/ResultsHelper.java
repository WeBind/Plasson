/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plasson;

import java.util.HashMap;

/**
 *
 * @author Nicolas
 * Computes results
 */
public class ResultsHelper {

    // From a consumers list, returns drop rate
    public float getDropRate(HashMap<String,Consumer> consumers){

        float dropRate = 0.0f;
        int totalMessages = 0;
        for(String key : consumers.keySet()){
            if(consumers.get(key).getResults()!=null){
                dropRate += consumers.get(key).getLostRequests();
                totalMessages += consumers.get(key).getResults().getSent().size();
            }
        }

        dropRate = dropRate/((float)totalMessages);


        return dropRate;

    }

    // From a consumers list, returns average response time
    public float getResponseTime(HashMap<String,Consumer> consumers){

        float responseTime = 0.0f;
        int consumerCounter = 0;
            for(String key : consumers.keySet()){
            if(consumers.get(key).getResults()!=null){
                responseTime += (consumers.get(key).getAverageRequestResponseTime()*
                        (consumers.get(key).getResults().getSent().size()-consumers.get(key).getLostRequests()));
                consumerCounter+=(consumers.get(key).getResults().getSent().size()-consumers.get(key).getLostRequests());
            }
        }
            responseTime = responseTime/((float)consumerCounter);

        return responseTime;
    }

    public int getTotalSentMessages(HashMap<String,Consumer> consumers){

        int sentMessages = 0;
        for(String key : consumers.keySet()){
           Consumer consumer = consumers.get(key);
           if(consumer.getResults()!=null){
               sentMessages += (consumer.getResults().getSent().size()
                   +consumer.getResults().getReceived().size());
           }
        }

        return sentMessages;
    }

    // From a result computes lost resquests and the average Request Response Time
    public ComputedResults computeConsumerResults(Results results, int providerResponseTime){

        int lostRequest = 0;
        float averageRequestResponseTime = 0.0f;
      

        Message sentMessage = null;

        for(int i = 0 ; i<results.getSent().size() ; i++){
            sentMessage = results.getSent().get(i);
            Message receivedByConsumer = results.getReceivedById(sentMessage.getId());

            if(receivedByConsumer == null){
                lostRequest++;
            }else{
                averageRequestResponseTime += (receivedByConsumer.getTime()-sentMessage.getTime());
            }

        }
        
        averageRequestResponseTime = (averageRequestResponseTime/((float)(results.getSent().size()-lostRequest)))-providerResponseTime;

        System.out.println("lostRequest = "+lostRequest);
        System.out.println("averageRequestResponseTime = "+averageRequestResponseTime);


        return  new ComputedResults(lostRequest,averageRequestResponseTime);
    }


    // Class to make esier the computed results return
    public static class ComputedResults{
        private int lostRequest;
        private float averageRequestResponseTime;

        public ComputedResults(int lostRequest, float averageRequestResponseTime) {
            this.lostRequest = lostRequest;
            this.averageRequestResponseTime = averageRequestResponseTime;
        }

        @Override
        public String toString(){
            return "" + this.lostRequest + " " +this.averageRequestResponseTime;
        }
        public float getAverageRequestResponseTime() {
            return averageRequestResponseTime;
        }

        public void setAverageRequestResponseTime(float averageRequestResponseTime) {
            this.averageRequestResponseTime = averageRequestResponseTime;
        }

        public int getLostRequest() {
            return lostRequest;
        }

        public void setLostRequest(int lostRequest) {
            this.lostRequest = lostRequest;
        }


    }
    


}
