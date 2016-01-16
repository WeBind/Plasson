/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plasson;

import java.util.List;

/**
 *
 * @author Nicolas
 */
public class Results {

    private String id;
    private List<Message> sent;
    private List<Message> received;

    public Results(String id, List<Message> sent, List<Message> received) {
        this.id = id;
        this.sent = sent;
        this.received = received;
    }

    public Results() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Message> getReceived() {
        return received;
    }

    public void setReceived(List<Message> received) {
        this.received = received;
    }

    public List<Message> getSent() {
        return sent;
    }

    public void setSent(List<Message> sent) {
        this.sent = sent;
    }





}
