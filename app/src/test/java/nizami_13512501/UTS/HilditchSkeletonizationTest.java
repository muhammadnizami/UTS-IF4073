package nizami_13512501.UTS;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by nim_13512501 on 18/10/16.
 */
public class HilditchSkeletonizationTest {
    @Test
    public void testA(){
        HilditchSkeletonization.XYPoint point = new HilditchSkeletonization.XYPoint(1,1);
        boolean [][] test1 = {{true,true,true},{true,true,true},{true,true,true}};
        Assert.assertEquals(0, HilditchSkeletonization.A(new ExtendedBooleanMatrix(test1),point));
        boolean [][] test2 = {{true,true,false},{true,true,true},{true,true,true}};
        Assert.assertEquals(1, HilditchSkeletonization.A(new ExtendedBooleanMatrix(test2),point));
        boolean [][] test3 = {{true,true,false},{true,true,false},{true,true,false}};
        Assert.assertEquals(1, HilditchSkeletonization.A(new ExtendedBooleanMatrix(test3),point));
        boolean [][] test4 = {{false,false,false},{true,true,true},{true,true,true}};
        Assert.assertEquals(1, HilditchSkeletonization.A(new ExtendedBooleanMatrix(test4),point));
    }
    @Test
    public void test1(){
        boolean [][] test1Image = {
                {false,false,false,false,false},
                {false,true,true,true,false},
                {false,true,true,true,false},
                {false,true,true,true,false},
                {false,true,true,true,false},
                {false,true,true,true,false},
                {false,true,true,true,false},
                {false,true,true,true,false},
                {false,true,true,true,false},
                {false,false,false,false,false}
        };
        HilditchSkeletonization.skeletonize(test1Image);
        for (int i=0;i<test1Image.length;i++){
            int num = 0;
            for (int j=0;j<test1Image[i].length;j++){
                if (test1Image[i][j])
                    num++;
                System.out.print("\t"+test1Image[i][j]);
            }
            System.out.println();
            Assert.assertTrue(num<=1);
        }
        for (int i=2;i<7;i++){
            int num = 0;
            for (int j=0;j<test1Image[i].length;j++){
                if (test1Image[i][j])
                    num++;
            }
            Assert.assertTrue(num==1);
        }
    }
    @Test
    public void test2(){
        boolean [][] test1Image = {
                {false,false,false,false,false,false,false,false,false},
                {false,true,true,true,true,true,true,true,false},
                {false,true,true,true,true,true,true,true,false},
                {false,true,true,true,true,true,true,true,false},
                {false,false,false,false,false,false,false,false,false}
        };
        HilditchSkeletonization.skeletonize(test1Image);
        for (int i=2;i<6;i++){
            int num = 0;
            for (int j=0;j<test1Image.length;j++){
                if (test1Image[j][i])
                    num++;
            }
            Assert.assertTrue(num==1);
        }
    }
}
