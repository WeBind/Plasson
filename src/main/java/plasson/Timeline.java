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
public class Timeline {

    private Rates globalResults;
    private Interval[] intervals;

    public Timeline(int intervalNumber, int scenarioDuration) {
        intervals = new Interval[intervalNumber];
        int intervalSize = (int)Math.floor(((double)scenarioDuration)/((double)intervalNumber));
        for(int i = 0 ; i < intervals.length-1 ; i++){
            intervals[i] = new Interval();
            intervals[i].setStart(i*intervalSize);
            intervals[i].setEnd((i+1)*intervalSize);
        }
        intervals[intervals.length-1] = new Interval();
        intervals[intervals.length-1].setStart((intervals.length-1)*intervalSize);
        intervals[intervals.length-1].setEnd(scenarioDuration);
    }

    public Timeline(int intervalNumber) {
        intervals = new Interval[intervalNumber];
    }


    public Interval getIntervalContaining(int time){
        Interval interval = null;
        boolean found = false;
        int i = 0;
        while(!found&&i<this.intervals.length){
            if(intervals[i].getStart()<=time && time < intervals[i].getEnd()){
                found = true;
                interval = intervals[i];
            }

            i++;
        }

        return interval;
    }

    public Rates getGlobalResults() {
        return globalResults;
    }

    public void setGlobalResults(Rates globalResults) {
        this.globalResults = globalResults;
    }

    public Interval[] getIntervals() {
        return intervals;
    }

    public void setIntervals(Interval[] intervals) {
        this.intervals = intervals;
    }

    public void setIntervalsConsumersRates(HashMap<String,Consumer> consumers, HashMap<String,Provider> providers){

        for( String key : consumers.keySet()){
            Consumer consumer = consumers.get(key);
            if(providers.containsKey(Config.providerPrefix+consumer.getProvider())){
                Provider provider = providers.get(Config.providerPrefix+consumer.getProvider());
                setIntervalFor(key,consumer.getResults(),provider.getResponseTime());

            }else{
                //TODO Mettre -1 en réponse et 1 en drop rate
            }

        }

    }

    private void setIntervalFor(String consumerId, Results results, int providerResponseTime){
        
                    if ( results != null ){
                    for( int i = 0 ; i < results.getSent().size() ; i++){
                        Message received = results.getReceivedById(results.getSent().get(i).getId());
                        Interval interval = this.getIntervalContaining(results.getSent().get(i).getTime());
                        if(interval.getConsumersResults().containsKey(consumerId)){

                            if(received == null){
                                //+1 lostRequests for this consumer in this interval
                                interval.getConsumersResults().get(consumerId)
                                        .setLostRequests(interval.getConsumersResults().get(consumerId).getLostRequests()+1);
                            }else{
                                //Update average response time
                                float receivedRequests = (interval.getConsumersResults().get(consumerId).getTotalMessage()-
                                        interval.getConsumersResults().get(consumerId).getLostRequests());
                                float oldResponseTime = interval.getConsumersResults().get(consumerId).getAverageResponseTime();
                                float responseTimeForThisRequest = received.getTime()-results.getSent().get(i).getTime()-(float)providerResponseTime;

                                interval.getConsumersResults().get(consumerId)
                                        .setAverageResponseTime(
                                        ((oldResponseTime*receivedRequests)
                                        +responseTimeForThisRequest)
                                        /(receivedRequests+1));
                            }

                            //+1 to the totalMessage sent for this consumer in this interval
                            interval.getConsumersResults().get(consumerId)
                                    .setTotalMessage(interval.getConsumersResults().get(consumerId).getTotalMessage()+1.0f);
                        }else{
                             Rates rates = new Rates();
                             if(received == null){
                                rates.setLostRequests(1);
                                rates.setAverageResponseTime(0);
                            }else{
                                //Update average response time
                                rates.setLostRequests(0);
                                rates.setAverageResponseTime(received.getTime()-results.getSent().get(i).getTime()-(float)providerResponseTime);

                            }
                            rates.setTotalMessage(1);
                            interval.getConsumersResults().put(consumerId, rates);

                        }

                    }
                }
    }

    //TODO Global requests
    private void setGlobalRates(){
        for( int i = 0 ; i < intervals.length ; i++ ){
            intervals[i].getIntervalResults().setAverageResponseTime(0.0f);
            intervals[i].getIntervalResults().setLostRequests(0.0f);
            intervals[i].getIntervalResults().setTotalMessage(0.0f);

            for ( String key : intervals[i].getConsumersResults().keySet()){
                intervals[i].getIntervalResults().setLostRequests(
                        intervals[i].getIntervalResults().getLostRequests()
                        +intervals[i].getConsumersResults().get(key).getLostRequests());
            }
        }

    }

    public void display(){
        for( int i = 0 ; i < intervals.length ; i ++){
            System.out.println("------- interval "+i+" -------");
            System.out.println("Start " + intervals[i].getStart());
            for(String key : intervals[i].getConsumersResults().keySet()){
                System.out.println("    Consumer : "+key);
                Rates rates = intervals[i].getConsumersResults().get(key);
                System.out.println("        lost requests : " +rates.getLostRequests());
                System.out.println("        total message : " +rates.getTotalMessage());
                System.out.println("        Response time : " +rates.getAverageResponseTime());

            }

            System.out.println("End " + intervals[i].getEnd());

        }
    }




}
