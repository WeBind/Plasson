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
public class Interval {
    private int start;
    private int end;
    private Rates intervalResults;

    private HashMap<String,Rates> consumersResults;

    public Interval() {
        consumersResults = new HashMap<String,Rates>();
    }

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    

    public HashMap<String, Rates> getConsumersResults() {
        return consumersResults;
    }

    public void setConsumersResults(HashMap<String, Rates> consumersResults) {
        this.consumersResults = consumersResults;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public Rates getIntervalResults() {
        return intervalResults;
    }

    public void setIntervalResults(Rates intervalResults) {
        this.intervalResults = intervalResults;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }



}
