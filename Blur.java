package nizami_13512501.UTS;

import android.graphics.Bitmap;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nim_13512501 on 14/09/16.
 */
public class Blur {

    public static Bitmap doBlur(Bitmap in){
        int width = in.getWidth();
        int height = in.getHeight();
        Bitmap gambarBlur = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        List<Integer> neighborAndSelf = new ArrayList<Integer>(16);

        for (int y=0;y<height;y++) {
            for (int x = 0; x < width; x++) {
                neighborAndSelf.clear();

                neighborAndSelf.add(in.getPixel(x,y));

                if (x>0){
                    neighborAndSelf.add(in.getPixel(x-1,y));
                    if (y>0){
                        neighborAndSelf.add(in.getPixel(x-1,y-1));
                    }
                    if (y<height-1){
                        neighborAndSelf.add(in.getPixel(x-1,y+1));
                    }
                }
                if(x<width-1){
                    neighborAndSelf.add(in.getPixel(x+1,y));
                    if (y>0){
                        neighborAndSelf.add(in.getPixel(x+1,y-1));
                    }
                    if (y<height-1){
                        neighborAndSelf.add(in.getPixel(x+1,y+1));
                    }
                }
                if (y>0){
                    neighborAndSelf.add(in.getPixel(x,y-1));
                }
                if (y<height-1){
                    neighborAndSelf.add(in.getPixel(x,y+1));
                }

                //menghitung rata-rata
                int Rsum = 0;
                int Gsum = 0;
                int Bsum = 0;
                int Asum = 0;
                for(Integer i : neighborAndSelf){
                    Rsum += Color.red(i);
                    Gsum += Color.green(i);
                    Bsum += Color.blue(i);
                    Asum += Color.alpha(i);
                }
                int n = neighborAndSelf.size();
                int Ravg = Rsum/n;
                int Gavg = Gsum/n;
                int Bavg = Bsum/n;
                int Aavg = Asum/n;
                gambarBlur.setPixel(x,y,Color.argb(Aavg,Ravg,Gavg,Bavg));
            }
        }
        return gambarBlur;
    }
}
