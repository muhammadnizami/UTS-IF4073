package nizami_13512501.UTS;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * Created by nim_13512501 on 20/09/16.
 */
public class MatriksKonvolusiBerskala extends MatriksKonvolusi {
    public MatriksKonvolusiBerskala(float[][] matriksKonvolusi) {
        super(matriksKonvolusi);
    }

    public MatriksKonvolusiBerskala(float[] floatArrayConstants) {
        super(floatArrayConstants);
    }

    public Bitmap eksekusi(ExtendedBitmap extendedBitmap) {
        int width = extendedBitmap.getWidth();
        int height = extendedBitmap.getHeight();

        float red[][] = new float[height][width];
        float green[][] = new float[height][width];
        float blue[][] = new float[height][width];
        float alpha[][] = new float[height][width];
        float maxalpha = 0;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                red[y][x] = eksekusiR(extendedBitmap, x, y);
                green[y][x] = eksekusiG(extendedBitmap, x, y);
                blue[y][x] = eksekusiB(extendedBitmap, x, y);
                alpha[y][x] = eksekusiA(extendedBitmap, x, y);
                if (alpha[y][x]>maxalpha)
                    maxalpha=alpha[y][x];
            }
        }

        int r [][] = skalakanMatriks(red);
        int g [][] = skalakanMatriks(green);
        int b [][] = skalakanMatriks(blue);
        int a [][];
        if (maxalpha>0)
            a = skalakanMatriks(alpha);
        else
            a = matriks255(alpha);

        Bitmap gambarHasil = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                gambarHasil.setPixel(x, y, Color.argb(a[y][x],r[y][x],g[y][x],b[y][x]));
            }
        }
        return gambarHasil;
    }

    private int [][] skalakanMatriks(float[][] mat) {
        int imat[][] = new int[mat.length][];
        float max = mat[0][0];
        float min = mat[0][0];
        float scale;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] > max) max = mat[i][j];
                if (mat[i][j] < min) min = mat[i][j];
            }
        }
        if (max==min){
            scale = 1;
            min=0;
            max=255;
        }else{
            scale = 255 / (max - min);
        }
        for (int i = 0; i < mat.length; i++) {
            imat[i]=new int[mat[i].length];
            for (int j = 0; j < mat[i].length; j++) {
                imat[i][j] = (int) ((mat[i][j] - min) * scale);
            }
        }
        return imat;
    }
    private int [][] matriks255(float[][] mat){
        int matriksHasil [][] = new int[mat.length][];
        for (int i=0;i<mat.length;i++){
            matriksHasil [i]= new int[mat[i].length];
            for (int j=0;j<mat[i].length;j++){
                matriksHasil[i][j]=255;
            }
        }
        return matriksHasil;
    }
}
