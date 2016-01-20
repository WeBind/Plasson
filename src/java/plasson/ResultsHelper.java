/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plasson;

import java.util.HashMap;

/**
 *
 * @author Nicolas
 */
public class ResultsHelper {

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

    public ComputedResults computeConsumerResults(Consumer consumer, int providerResponseTime){

        int lostRequest = 0;
        float averageRequestResponseTime = 0.0f;
      

        Message sentMessage = null;

        for(int i = 0 ; i<consumer.getResults().getSent().size() ; i++){
            sentMessage = consumer.getResults().getSent().get(i);
            Message receivedByConsumer = consumer.getReceivedById(sentMessage.getId());

            if(receivedByConsumer == null){
                lostRequest++;
            }else{
                averageRequestResponseTime += (receivedByConsumer.getTime()-sentMessage.getTime());
            }

        }
        
        averageRequestResponseTime = (averageRequestResponseTime/((float)(consumer.getResults().getSent().size()-lostRequest)))-providerResponseTime;

        System.out.println("lostRequest = "+lostRequest);
        System.out.println("averageRequestResponseTime = "+averageRequestResponseTime);


        return  new ComputedResults(lostRequest,averageRequestResponseTime);
    }

    public class ComputedResults{
        private int lostRequest;
        private float averageRequestResponseTime;

        public ComputedResults(int lostRequest, float averageRequestResponseTime) {
            this.lostRequest = lostRequest;
            this.averageRequestResponseTime = averageRequestResponseTime;
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
