/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plasson;

/**
 *
 * @author Nicolas
 */
public class Consumer {
    
    private int id;
    private int startingTime;
    private int size;
    private float frequency;
    private int duration;
    private int provider;

    public Consumer(int id, int startingTime, int size, float frequency, int duration, int provider) {
        this.id = id;
        this.startingTime = startingTime;
        this.size = size;
        this.frequency = frequency;
        this.duration = duration;
        this.provider = provider;
    }

    public Consumer() {
    }


    public String toString(){

        return id+" "+startingTime+" "+size+" "+frequency+" "+duration+" "+provider;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public float getFrequency() {
        return frequency;
    }

    public void setFrequency(float frequency) {
        this.frequency = frequency;
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


}
