package nizami_13512501.UTS;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * Created by nim_13512501 on 19/09/16.
 */
public class MatriksKonvolusi {
    float matriksKonvolusi[][];
    public MatriksKonvolusi(float matriksKonvolusi[][]){
        this.matriksKonvolusi = matriksKonvolusi;
    }

    public MatriksKonvolusi(float floatArrayConstants[]){
        matriksKonvolusi = new float[3][3];
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                matriksKonvolusi[i][j] = floatArrayConstants[3*i+j];
                System.out.print(matriksKonvolusi[i][j]);
            }
            System.out.println();
        }
    }

    public Bitmap eksekusi(Bitmap bitmap){
        return eksekusi(new ExtendedBitmap(bitmap));
    }

    public Bitmap eksekusi(ExtendedBitmap extendedBitmap){
        int width = extendedBitmap.getWidth();
        int height = extendedBitmap.getHeight();
        Bitmap gambarHasil = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        for (int y=0; y<height; y++){
            for (int x=0;x<width; x++){
                gambarHasil.setPixel(x,y,eksekusi(extendedBitmap,x,y));
            }
        }
        return gambarHasil;
    }

    public boolean[][] eksekusi(int [][] grayLevel, int threshold){
        return eksekusi(new ExtendedGrayLevelMatrix(grayLevel),threshold);
    }

    public boolean[][] eksekusi(ExtendedGrayLevelMatrix extendedGrayLevelMatrix, int threshold){
        int width = extendedGrayLevelMatrix.getWidth();
        int height = extendedGrayLevelMatrix.getHeight();
        boolean [][] booleanHasil = new boolean[width][height];
        for (int x=0;x<width; x++){
            for (int y=0; y<height; y++){
                booleanHasil[x][y] = eksekusi(extendedGrayLevelMatrix,x,y,threshold);
            }
        }
        return booleanHasil;
    }

    public int[][] eksekusi(int [][] grayLevel){
        return eksekusi(new ExtendedGrayLevelMatrix(grayLevel));
    }

    public int[][] eksekusi(ExtendedGrayLevelMatrix extendedGrayLevelMatrix){
        int width = extendedGrayLevelMatrix.getWidth();
        int height = extendedGrayLevelMatrix.getHeight();
        int [][] hasil = new int[width][height];
        for (int x=0;x<width; x++){
            for (int y=0; y<height; y++){
                hasil[x][y] = eksekusi(extendedGrayLevelMatrix,x,y);
            }
        }
        return hasil;
    }

    public boolean eksekusi(ExtendedGrayLevelMatrix extendedGrayLevelMatrix, int x, int y, int threshold){
        float sum = 0;
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                sum += (extendedGrayLevelMatrix.getPixel(x + dx, y + dy)) * matriksKonvolusi[dx + 1][dy + 1];
            }
        }
        if (sum>threshold)
            return true;
        else
            return false;
    }

    public int eksekusi(ExtendedGrayLevelMatrix extendedGrayLevelMatrix, int x, int y) {
        int sum = 0;
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                sum += (extendedGrayLevelMatrix.getPixel(x + dx, y + dy)) * matriksKonvolusi[dx + 1][dy + 1];
            }
        }
        return sum;
    }

    public float eksekusiR(ExtendedBitmap extendedBitmap, int x, int y) {
        float sumR = 0;
        for (int dy = -1; dy <= 1; dy++) {
            for (int dx = -1; dx <= 1; dx++) {
                sumR += Color.red(extendedBitmap.getPixel(x + dx, y + dy)) * matriksKonvolusi[dx + 1][dy + 1];
            }
        }
        return sumR;
    }
    public float eksekusiG(ExtendedBitmap extendedBitmap, int x, int y) {
        float sumG = 0;
        for (int dy = -1; dy <= 1; dy++) {
            for (int dx = -1; dx <= 1; dx++) {
                sumG += Color.green(extendedBitmap.getPixel(x + dx, y + dy)) * matriksKonvolusi[dx + 1][dy + 1];
            }
        }
        return sumG;
    }
    public float eksekusiB(ExtendedBitmap extendedBitmap, int x, int y) {
        float sumB = 0;
        for (int dy = -1; dy <= 1; dy++) {
            for (int dx = -1; dx <= 1; dx++) {
                sumB += Color.blue(extendedBitmap.getPixel(x + dx, y + dy)) * matriksKonvolusi[dx + 1][dy + 1];
            }
        }
        return sumB;
    }
    public float eksekusiA(ExtendedBitmap extendedBitmap, int x, int y) {
        float sumA = 0;
        for (int dy = -1; dy <= 1; dy++) {
            for (int dx = -1; dx <= 1; dx++) {
                sumA += Color.alpha(extendedBitmap.getPixel(x + dx, y + dy)) * matriksKonvolusi[dx + 1][dy + 1];
            }
        }
        return sumA;
    }
    public int eksekusi(ExtendedBitmap extendedBitmap,int x, int y){
        int sumR = (int) eksekusiR(extendedBitmap,x,y);
        int sumG = (int) eksekusiR(extendedBitmap,x,y);
        int sumB = (int) eksekusiR(extendedBitmap,x,y);
        int sumA = (int) eksekusiR(extendedBitmap,x,y);
        int newR = sumR<256?(sumR>=0?(int)sumR:0):255;
        int newG = sumG<256?(sumG>=0?(int)sumG:0):255;
        int newB = sumB<256?(sumB>=0?(int)sumB:0):255;
        int newA = sumA<256?(sumA>=0?(int)sumA:0):255;
        return Color.argb(newA,newR,newG,newB);
    }

    public MatriksKonvolusi hasilPutarSerongKiri(){
        float matriksHasil [][] = new float[3][3];
        matriksHasil[1][1] = matriksKonvolusi[1][1];
        matriksHasil[0][0] = matriksKonvolusi[0][1];
        matriksHasil[0][1] = matriksKonvolusi[0][2];
        matriksHasil[0][2] = matriksKonvolusi[1][2];
        matriksHasil[1][2] = matriksKonvolusi[2][2];
        matriksHasil[2][2] = matriksKonvolusi[2][1];
        matriksHasil[2][1] = matriksKonvolusi[2][0];
        matriksHasil[2][0] = matriksKonvolusi[1][0];
        matriksHasil[1][0] = matriksKonvolusi[0][0];
        return new MatriksKonvolusi(matriksHasil);
    }

    public MatriksKonvolusi hasilPutarKiri(){
        float matriksHasil [][] = new float[3][3];
        matriksHasil[1][1] = matriksKonvolusi[1][1];
        matriksHasil[0][0] = matriksKonvolusi[0][2];
        matriksHasil[0][1] = matriksKonvolusi[1][2];
        matriksHasil[0][2] = matriksKonvolusi[2][2];
        matriksHasil[1][2] = matriksKonvolusi[2][1];
        matriksHasil[2][2] = matriksKonvolusi[2][0];
        matriksHasil[2][1] = matriksKonvolusi[1][0];
        matriksHasil[2][0] = matriksKonvolusi[0][0];
        matriksHasil[1][0] = matriksKonvolusi[0][1];
        return new MatriksKonvolusi(matriksHasil);
    }
}
