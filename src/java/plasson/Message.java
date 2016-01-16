/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plasson;

/**
 *
 * @author Nicolas
 */
public class Message {

    private String id;
    private int time;

    public Message(String id, int time) {
        this.id = id;
        this.time = time;
    }

    public Message() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }



}
