/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plasson;

/**
 *
 * @author Nicolas
 */
public class Rates {
    private float lostRequests;
    private float totalMessage;
    private float averageResponseTime;



    public Rates() {
    }



    public float getAverageResponseTime() {
        return averageResponseTime;
    }

    public void setAverageResponseTime(float averageResponseTime) {
        this.averageResponseTime = averageResponseTime;
    }

    public float getLostRequests() {
        return lostRequests;
    }

    public void setLostRequests(float lostRequests) {
        this.lostRequests = lostRequests;
    }

    public float getTotalMessage() {
        return totalMessage;
    }

    public void setTotalMessage(float totalMessage) {
        this.totalMessage = totalMessage;
    }

    

}
