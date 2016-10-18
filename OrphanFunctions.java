package nizami_13512501.UTS;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

/**
 * Created by nim_13512501 on 14/09/16.
 */
public class OrphanFunctions {
    /**
     * @param gambarWarna sebuah gambar
     * @return sebuah gambar di mana untuk setiap pixel, nilai R = nilai G = nilai B
     */
    public static Bitmap buatGrayscale(Bitmap gambarWarna){
        int width, height;
        height = gambarWarna.getHeight();
        width = gambarWarna.getWidth();

        Bitmap gambarGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Canvas c = new Canvas(gambarGrayscale);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(gambarWarna, 0, 0, paint);
        return gambarGrayscale;
    }

    /**
     *
     * @param gambarWarna
     * @return matrix [width][height] di mana grayLevel(gambarWarna)[x][y] == gray level dari pixel gambar Warna (x,y)
     */
    public static int[][] grayLevel(Bitmap gambarWarna){
        Bitmap gambarGrayScale = buatGrayscale(gambarWarna);
        int width = gambarGrayScale.getWidth();
        int height = gambarGrayScale.getHeight();
        int [][] grayLevel = new int[width][height];
        for (int x=0;x<width;x++){
            for (int y=0;y<height;y++){
                grayLevel[x][y]= Color.red(gambarGrayScale.getPixel(x,y));
            }
        }
        return grayLevel;
    }

    /**
     *
     * @param boolMatrix matriks mxn, m>0, n>0
     * @return bitmap, true -> 255, false -> 0
     */
    public static Bitmap createBitmapFromBooleanMatrix(boolean [][] boolMatrix){
        int width = boolMatrix.length;
        int height = boolMatrix[0].length;
        Bitmap gambar = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        for (int x=0;x<width;x++){
            for (int y=0;y<height;y++){
                int col = boolMatrix[x][y]?Color.WHITE:Color.BLACK;
                gambar.setPixel(x,y,col);
            }
        }
        return gambar;
    }

    public static boolean[][] createBooleanMatrixFromGrayLevel(int [][] grayLevel, int threshold){
        int width = grayLevel.length;
        boolean [][] booleen = new boolean[width][];
        for (int x=0;x<width;x++){
            int height = grayLevel[x].length;
            booleen[x]=new boolean[height];
            for (int y=0;y<height;y++){
                booleen[x][y] = grayLevel[x][y] >= threshold;
            }
        }
        return booleen;
    }

}
