package nizami_13512501.UTS;

import android.graphics.Bitmap;

/**
 * Created by nim_13512501 on 14/09/16.
 */
public abstract class EkualisasiHistogram {
    /**
     *
     * @param gambar
     * @return sebuah objek gambar baru yang merupakan hasil ekualisasi
     */
    public Bitmap ekualisasiGambar(Bitmap gambar){
        int width = gambar.getWidth();
        int height = gambar.getHeight();
        Bitmap gambarTerekualisasi = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        return ekualisasiGambar(gambar,gambarTerekualisasi);
    }

    /**
     *
     * @param gambar
     * @param containerHasil berukuran sama dengan gambar, objek yang berbeda
     * @return objek containerHasil
     */
    public Bitmap ekualisasiGambar(Bitmap gambar, Bitmap containerHasil) {
        int height = gambar.getHeight();
        int width = gambar.getWidth();
        for (int x=0;x<width;x++){
            for (int y=0;y<height;y++){
                containerHasil.setPixel(x,y,lookup(gambar.getPixel(x,y)));
            }
        }
        return containerHasil;
    }

    /**
     * fungsi lookup
     * @param pixel
     * @return nilai baru untuk pixel
     */
    abstract public int lookup(int pixel);
}
