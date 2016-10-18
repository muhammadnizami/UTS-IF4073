package nizami_13512501.UTS;

import android.graphics.Color;

/**
 * Created by nim_13512501 on 14/09/16.
 */
public class EkualisasiHistogramSegitiga extends EkualisasiHistogram {
    private int puncak;

    //for computational purposes
    private float gradient1;
    private float gradient2;
    private static float YMAX = 128;
    private static float XMAX = 255;
    private static float VMAX = 255;
    private static float C = VMAX*2/(YMAX*VMAX);

    public EkualisasiHistogramSegitiga(int puncak){
        this.setPuncak(puncak);
    }

    public int getPuncak() {
        return puncak;
    }

    public void setPuncak(int puncak) {
        assert(puncak>=0 && puncak <= XMAX);
        this.puncak = puncak;
        gradient1 = getPuncak()>0?YMAX/getPuncak():YMAX;
        gradient2 = XMAX-getPuncak()>0?-YMAX/(XMAX-getPuncak()):-YMAX;
    }


    @Override
    public int lookup(int pixel){
        int r = Color.red(pixel);
        int g = Color.green(pixel);
        int b = Color.blue(pixel);
        int a = Color.alpha(pixel); //alpha akan tetap
        int rbaru = lookupOneColor(r);
        int gbaru = lookupOneColor(g);
        int bbaru = lookupOneColor(b);
        return Color.argb(a,rbaru,gbaru,bbaru);
    }

    public int lookupOneColor(int col){
        int ret = 0;
        if (col<puncak){
            ret = (int) (col*col*gradient1/2*C);
        }else{
            ret = (int) ((YMAX+(YMAX+YMAX+(col-puncak)*gradient2)/2*(col-puncak))*C);
        }
        if (ret > VMAX)
            ret = (int) VMAX;
        if (ret < 0)
            ret = 0;
        return ret;
    }
}
