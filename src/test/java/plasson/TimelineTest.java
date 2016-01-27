/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plasson;

import java.util.ArrayList;
import java.util.HashMap;
import junit.framework.TestCase;

/**
 *
 * @author Nicolas
 */
public class TimelineTest extends TestCase {
    
    public TimelineTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getIntervalContaining method, of class Timeline.
     */
    public void testGetIntervalContaining() {
        System.out.println("getIntervalContaining");
        int time = 7;
        Timeline instance = new Timeline(4);
        Interval[] intervals = new Interval[4];
        Interval interval0to5 = new Interval(0,5);
        Interval interval5to10 = new Interval(5,10);
        Interval interval10to15 = new Interval(10,15);
        Interval interval5to20 = new Interval(15,20);
        intervals[0] = interval0to5;
        intervals[1] = interval5to10;
        intervals[2] = interval10to15;
        intervals[3] = interval5to20;

        instance.setIntervals(intervals);


        Interval expResult = interval5to10;
        Interval result = instance.getIntervalContaining(time);
        assertEquals(expResult, result);
    }

    

}
