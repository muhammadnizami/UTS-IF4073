package nizami_13512501.UTS;

import android.graphics.Bitmap;

/**
 * Created by nim_13512501 on 19/09/16.
 */
public class ExtendedBitmap{
    Bitmap bitmap;

    ExtendedBitmap(Bitmap bitmap){
        this.bitmap = bitmap;
    }

    int getPixel(int x, int y){
        int bitmap_x = x;
        int bitmap_y = y;
        if (bitmap_x >= bitmap.getWidth())
            bitmap_x = bitmap.getWidth()-1;
        if (bitmap_x < 0)
            bitmap_x = 0;
        if (bitmap_y >= bitmap.getHeight())
            bitmap_y = bitmap.getHeight()-1;
        if (bitmap_y < 0)
            bitmap_y = 0;
        return bitmap.getPixel(bitmap_x, bitmap_y);
    }

    public void setBitmap(Bitmap bitmap){
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap(){
        return this.bitmap;
    }

    public int getWidth(){
        return bitmap.getWidth();
    }

    public int getHeight(){
        return bitmap.getHeight();
    }
}
