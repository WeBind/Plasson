/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plasson;

/**
 *
 * @author Nicolas
 */
public class Consumer{

    private int id;
    private int startingTime;
    private int size;
    private int period;
    private int duration;
    private int provider;

    private Results results;
    private int lostRequests;
    private float averageRequestResponseTime;



    public Consumer(int id, int startingTime, int size, int period, int duration, int provider) {
        this.id = id;
        this.startingTime = startingTime;
        this.size = size;
        this.period = period;
        this.duration = duration;
        this.provider = provider;
        this.results=null;
    }

    public Consumer() {
        this.results=null;
    }

    @Override
    public String toString(){

        return this.getId()+" "+startingTime+" "+size+" "+period+" "+duration+" "+provider;
    }




    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public float getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getProvider() {
        return provider;
    }

    public void setProvider(int provider) {
        this.provider = provider;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(int startingTime) {
        this.startingTime = startingTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }



    public Message getReceivedById(String messageID){

        boolean hasReceived = false;
        Message receivedMessage = null;
        int i = 0;
        while(!hasReceived && i<this.getResults().getReceived().size()){
            if(this.getResults().getReceived().get(i).getId().equals(messageID)){
                hasReceived = true;
                receivedMessage = this.getResults().getReceived().get(i);
            }
            i++;
        }

        return receivedMessage;
    }

    public float getAverageRequestResponseTime() {
        return averageRequestResponseTime;
    }

    public void setAverageRequestResponseTime(float averageRequestResponseTime) {
        this.averageRequestResponseTime = averageRequestResponseTime;
    }

    public int getLostRequests() {
        return lostRequests;
    }

    public void setLostRequests(int lostRequests) {
        this.lostRequests = lostRequests;
    }
    
}
