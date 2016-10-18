package nizami_13512501.UTS;

import android.support.v4.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by nim_13512501 on 17/10/16.
 * http://cgm.cs.mcgill.ca/~godfried/teaching/projects97/azar/skeleton.html
 */
public class HilditchSkeletonization {
    public static class XYPoint {
        private final int[][] neighborsdelta = {{-1,0},{-1,-1},{0,-1},{1,-1},{1,0},{1,1},{0,1},{-1,1}};
        public int x;
        public int y;
        public XYPoint(int x, int y){
            this.x = x;
            this.y = y;
        }
        public List<XYPoint> getNeighbors(){
            List<XYPoint> neighbors = new ArrayList<>(8);
            for (int [] neighbordelta : neighborsdelta){
                neighbors.add(new XYPoint(x+neighbordelta[0],y+neighbordelta[1]));
            }
            return neighbors;
        }
        public String toString(){
            return "("+x+","+y+")";
        }

    }
    public static void skeletonize(boolean[][] image){
        ExtendedBooleanMatrix extendedImage = new ExtendedBooleanMatrix(image);
        while (passThrough(image,extendedImage)){}
    }

    /**
     * returns true if something happens
     */
    public static boolean passThrough(boolean[][] image, ExtendedBooleanMatrix extendedImage){
        int width = extendedImage.getWidth();
        int height = extendedImage.getHeight();
        boolean somethingHappened = false;
        Queue<XYPoint> deleteQueue = new LinkedBlockingQueue<>();
        for (int x=0;x<width;x++){
            for (int y=0;y<height;y++){
                if (image[x][y]) {
                    XYPoint p1 = new XYPoint(x, y);
                    List<XYPoint> p = p1.getNeighbors();
                    int Bp1 = B(extendedImage, p1);
                    boolean condition1 = 2 <= Bp1 && Bp1 <= 6;
                    boolean condition2 = A(extendedImage, p1) == 1;
                    boolean condition3 = (!(extendedImage.getPixel(p.get(2)) && extendedImage.getPixel(p.get(4)) && extendedImage.getPixel(p.get(0)))
                            || A(extendedImage, p.get(2)) != 1);
                    boolean condition4 = (!(extendedImage.getPixel(p.get(2)) && extendedImage.getPixel(p.get(4)) && extendedImage.getPixel(p.get(6)))
                            || A(extendedImage, p.get(4)) != 1);
                    if (condition1 && condition2 && condition3 && condition4) {
                        deleteQueue.add(p1);
                        somethingHappened = true;
                    }
                }
            }
        }
        for (XYPoint p:
                deleteQueue) {
            image[p.x][p.y] = false;
        }
        return somethingHappened;
    }


    public static int B(ExtendedBooleanMatrix image, XYPoint p1){
        int num = 0;
        for (XYPoint neighbor : p1.getNeighbors()){
            if (image.getPixel(neighbor)){
                num++;
            }
        }
        return num;
    }

    public static int A(ExtendedBooleanMatrix image, XYPoint p1){
        List<XYPoint> neighbors = p1.getNeighbors();
        int num = 0;
        for (int i=0;i<neighbors.size()-1;i++){
            if (!image.getPixel(neighbors.get(i))&&image.getPixel(neighbors.get(i+1))){
                num++;
            }
        }
        if (!image.getPixel(neighbors.get(neighbors.size()-1))&&image.getPixel(neighbors.get(0))){
            num++;
        }
        return num;
    }
}