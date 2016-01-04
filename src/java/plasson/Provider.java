/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plasson;

/**
 *
 * @author Nicolas
 */
public class Provider {
    private int id;
    private int responseLength;
    private int responseTime;

    public Provider(int id, int responseLength, int responseTime) {
        this.id = id;
        this.responseLength = responseLength;
        this.responseTime = responseTime;
    }

    public Provider() {
    }

    public String toString(){

        return id+" "+responseLength+" "+responseTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResponseLength() {
        return responseLength;
    }

    public void setResponseLength(int responseLength) {
        this.responseLength = responseLength;
    }

    public int getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }





}
