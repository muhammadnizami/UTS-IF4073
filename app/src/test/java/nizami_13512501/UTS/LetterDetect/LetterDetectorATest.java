package nizami_13512501.UTS.LetterDetect;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by nim_13512501 on 18/10/16.
 */
public class LetterDetectorATest {
    @Test
    public void testTrue1(){
        boolean [][] skeleton = {
                {false,false,false,false,false,false,false},
                {false,false,false,true,true,true,false},
                {false,true,true,false,false,false,false},
                {false,false,false,true,false,false,false},
                {false,false,false,true,true,true,false},
        };
        Assert.assertTrue((new LetterDetectorA()).detect(skeleton));
    }
    @Test
    public void testFalse1(){
        boolean [][] skeleton = {
                {false,false,false,false,false,false,false},
                {false,false,false,false,false,false,false},
                {true,true,true,true,true,true,true},
                {false,false,false,false,false,false,false},
                {false,false,false,false,false,false,false},
                {false,false,false,false,false,false,false}
        };
        Assert.assertFalse((new LetterDetectorA()).detect(skeleton));
    }
    @Test
    public void testFalse2(){
        boolean [][] skeleton = {
                {false,false,false,false,false,false,false},
                {true,true,true,true,true,true,true},
                {false,false,false,true,false,false,false},
                {false,false,false,true,false,false,false},
                {false,false,false,true,false,false,false},
                {true,true,true,true,true,true,true},
                {false,false,false,false,false,false,false}
        };
        Assert.assertFalse((new LetterDetectorA()).detect(skeleton));
    }
}
