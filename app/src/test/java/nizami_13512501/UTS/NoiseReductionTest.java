package nizami_13512501.UTS;

import org.junit.Test;

import static junit.framework.Assert.assertFalse;


/**
 * Created by nim_13512501 on 17/10/16.
 */
public class NoiseReductionTest {
    @Test
    public void test1() throws Exception {
        boolean [][] test1Image = {{false,false,false,false,false},
                {false,false,true,true,false},
                {false,false,false,true,false},
                {false,false,false,false,false},
                {false,false,false,false,false}
        };
        (new NoiseReduction(4)).attemptNoiseRemove(test1Image);
        for (boolean[] barr : test1Image){
            for (boolean b : barr){
                assertFalse(b);
            }
        }
    }
    @Test
    public void test2() throws Exception {
        boolean [][] test1Image = {{false,false,false,false,false},
                {false,false,true,true,false},
                {false,false,false,true,false},
                {false,false,false,false,false},
                {false,false,false,false,false},
                {false,false,false,false,false},
                {false,false,true,true,false},
                {false,false,false,true,false},
                {false,false,false,false,false},
                {false,false,false,false,false}
        };
        (new NoiseReduction(4)).attemptNoiseRemove(test1Image);
        for (boolean[] barr : test1Image){
            for (boolean b : barr){
                assertFalse(b);
            }
        }
    }
    @Test
    public void test3() throws Exception {
        boolean [][] test1Image = {
                {false,false,false,false,false,false,false,false,false,false},
                {false,false,false,false,false,false,false,true,true,false},
                {false,false,false,false,false,false,false,false,true,false},
                {false,false,true,false,false,false,false,false,false,false},
                {false,false,false,false,false,false,false,false,false,false}
        };
        (new NoiseReduction(4)).attemptNoiseRemove(test1Image);
        for (boolean[] barr : test1Image){
            for (boolean b : barr){
                assertFalse(b);
            }
        }
    }

}
