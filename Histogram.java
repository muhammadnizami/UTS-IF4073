package nizami_13512501.UTS;

import android.graphics.Bitmap;
import android.graphics.Color;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nim_13512501 on 14/09/16.
 */
public class Histogram {
    public static final double R_WEIGHT = 0.2126;
    public static final double G_WEIGHT = 0.7152;
    public static final double B_WEIGHT = 0.0722;
    /**
     * @param gambar sebuah gambar bitmap
     * @return sebuah int[], dengan elemen ke-i menyatakan banyaknya kemunculan warna i dalam gambar grayscale yang dibentuk dari parameter yang diberikan
     */
    public static int [] hitungKemunculan(Bitmap gambar){
        int [] kemunculan= new int [256];
        for (int i=0;i<kemunculan.length;i++){
            kemunculan[i]=0;
        }

        int width = gambar.getWidth();
        int height = gambar.getHeight();

        for (int y=0;y<height;y++){
            for (int x=0;x<width;x++){
                int pixel = gambar.getPixel(x,y);
                int pixelGrayVal = (int) (R_WEIGHT*Color.red(pixel)+G_WEIGHT*Color.green(pixel)+B_WEIGHT*Color.blue(pixel));
                kemunculan[pixelGrayVal]++;
            }
        }

        return kemunculan;
    }

    public static Map<Integer,Integer> hitungKemunculan(int [][] gambarGrayScale){
        Map<Integer,Integer> kemunculan = new HashMap<Integer, Integer>();

        int width = gambarGrayScale.length;

        for (int x=0;x<width;x++){
            int height = gambarGrayScale[x].length;
            for (int y=0;y<height;y++){
                int pixelGrayVal = gambarGrayScale[x][y];
                if (kemunculan.containsKey(pixelGrayVal)){
                    int prevVal = kemunculan.get(pixelGrayVal);
                    kemunculan.put(pixelGrayVal,prevVal+1);
                }else{
                    kemunculan.put(pixelGrayVal,1);
                }
            }
        }

        return kemunculan;
    }

    /**
     * @param gambar sebuah gambar bitmap di mana untuk setiap pixel, nilai R == nilai G == nilai B
     * @return sebuah float[], dengan elemen ke-i menyatakan frekuensi warna i dalam gambar
     */
    public static float [] hitungFrekuensi(Bitmap gambar){
        int [] kemunculan = hitungKemunculan(gambar);
        int totalPixels = gambar.getWidth() * gambar.getHeight();
        float [] frekuensi = new float [256];
        for (int i=0;i<256;i++){
            frekuensi[i] = (float)kemunculan[i]/totalPixels;
        }
        return frekuensi;
    }

    public static final int HIST_WIDTH = 256;
    public static final int HIST_HEIGHT = 128;

    public static Bitmap buatHistogram(Bitmap gambar){
        Bitmap containerHistogram = Bitmap.createBitmap(HIST_WIDTH,HIST_HEIGHT, Bitmap.Config.ARGB_8888);
        return buatHistogram(gambar, containerHistogram);
    }

    public static Bitmap buatHistogram(Bitmap gambar, Bitmap containerHistogram){
        float [] frekuensi = hitungFrekuensi(gambar);
        float maxFrekuensi = maxFrekuensi(frekuensi);
        float pengaliTinggi = (float) Math.sqrt(1/maxFrekuensi);
        for (int x=0;x<HIST_WIDTH-1; x++){
            int tinggi = (int) (frekuensi[x]*HIST_HEIGHT*pengaliTinggi);
            for (int y = 0; y<HIST_HEIGHT-tinggi-1; y++){
                containerHistogram.setPixel(x,y,Color.WHITE);
            }
            for (int y=HIST_HEIGHT-tinggi;y<HIST_HEIGHT;y++){
                containerHistogram.setPixel(x,y,Color.BLACK);
            }
        }
        return containerHistogram;
    }

    public static float maxFrekuensi(float[] frekuensi){
        float max = 0;
        for (int i=0;i<frekuensi.length;i++){
            if (frekuensi[i]>max){
                max=frekuensi[i];
            }
        }
        return max;
    }
}
