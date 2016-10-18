package nizami_13512501.UTS;

import android.graphics.Color;

/**
 * Created by nim_13512501 on 20/09/16.
 */
public class OperatorDifference extends MatriksKonvolusiBerskala {
    public static final float [] a = {0,0,0,0,0,0,0,0,0};
    public OperatorDifference(){
        super(a);
    }

    public OperatorDifference(float[][] matriksKonvolusi) {
        super(matriksKonvolusi);
    }

    public OperatorDifference(float[] floatArrayConstants) {
        super(floatArrayConstants);
    }

    public static final int numDifferences = 4;
    public static final int [] dx1 = {-1,0,1,-1};
    public static final int [] dy1 = {-1,-1,-1,0};
    public static final int [] dx2 = {1,0,-1,1};
    public static final int [] dy2 = {1,1,1,0};

    @Override
    public int eksekusi(ExtendedBitmap extendedBitmap, int x, int y){
        int differences[] = new int[numDifferences];
        for (int i=0;i<numDifferences;i++){
            differences[i]=colorDifferenceWithoutAlpha(extendedBitmap.getPixel(x+dx1[i],y+dy1[i]),
                    extendedBitmap.getPixel(x+dx2[i],y+dy2[i]));
        }
        int maxdifference = 0;
        for (int i=0;i<numDifferences;i++){
            maxdifference = maxRGBSumSquares(maxdifference, differences[i]);
        }
        return maxdifference;
    }

    @Override
    public float eksekusiR(ExtendedBitmap extendedBitmap, int x, int y){
        int differences[] = new int[numDifferences];
        for (int i=0;i<numDifferences;i++){
            differences[i]=Math.abs(Color.red(extendedBitmap.getPixel(x+dx1[i],y+dy1[i]))-Color.red(extendedBitmap.getPixel(x+dx2[i],y+dy2[i])));
        }
        int maxdifference = 0;
        for (int i=0;i<numDifferences;i++){
            if (differences[i]>maxdifference)
                maxdifference = differences[i];
        }
        return maxdifference;
    }

    @Override
    public float eksekusiG(ExtendedBitmap extendedBitmap, int x, int y){
        int differences[] = new int[numDifferences];
        for (int i=0;i<numDifferences;i++){
            differences[i]=Math.abs(Color.green(extendedBitmap.getPixel(x+dx1[i],y+dy1[i]))-Color.green(extendedBitmap.getPixel(x+dx2[i],y+dy2[i])));
        }
        int maxdifference = 0;
        for (int i=0;i<numDifferences;i++){
            if (differences[i]>maxdifference)
                maxdifference = differences[i];
        }
        return maxdifference;
    }

    @Override
    public float eksekusiB(ExtendedBitmap extendedBitmap, int x, int y){
        int differences[] = new int[numDifferences];
        for (int i=0;i<numDifferences;i++){
            differences[i]=Math.abs(Color.blue(extendedBitmap.getPixel(x+dx1[i],y+dy1[i]))-Color.blue(extendedBitmap.getPixel(x+dx2[i],y+dy2[i])));
        }
        int maxdifference = 0;
        for (int i=0;i<numDifferences;i++){
            if (differences[i]>maxdifference)
                maxdifference = differences[i];
        }
        return maxdifference;
    }

    @Override
    public float eksekusiA(ExtendedBitmap extendedBitmap, int x, int y){
        return 255;
    }

    protected int colorDifferenceWithoutAlpha(int color1, int color2){
        int rdiff1 = Math.abs(Color.red(color1)- Color.red(color2));
        int gdiff1 = Math.abs(Color.green(color1)- Color.green(color2));
        int bdiff1 = Math.abs(Color.blue(color1)- Color.blue(color2));
        return Color.argb(255,rdiff1,gdiff1,bdiff1);
    }

    protected int maxRGBSumSquares(int color1, int color2){
        if (RGBSumSquares(color1) > RGBSumSquares(color2)){
            return color1;
        }else{
            return color2;
        }
    }

    protected int RGBSumSquares(int color){
        return Color.red(color)*Color.red(color) + Color.green(color) + Color.green(color) + Color.blue(color) + Color.blue(color);
    }
}
