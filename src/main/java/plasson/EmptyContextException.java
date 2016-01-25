/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plasson;

/**
 *
 * @author Nicolas
 */
public class EmptyContextException extends Exception {

    /**
     * Creates a new instance of <code>EmptyContextException</code> without detail message.
     */
    public EmptyContextException() {
    }


    /**
     * Constructs an instance of <code>EmptyContextException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public EmptyContextException(String msg) {
        super(msg);
    }
}
