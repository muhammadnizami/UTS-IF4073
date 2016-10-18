package nizami_13512501.UTS;

/**
 * Created by nim_13512501 on 28/09/16.
 */
public class EdgeDerajat2 extends MatriksKonvolusiBerskala{

    MatriksKonvolusi matriksKonvolusiArah [] = new MatriksKonvolusi[8];

    public EdgeDerajat2(float[][] matriksKonvolusi) {
        super(matriksKonvolusi);
        hitungMatriksKonvolusiArah();
    }

    public EdgeDerajat2(float[] floatArrayConstants) {
        super(floatArrayConstants);
        hitungMatriksKonvolusiArah();
    }

    //helper for constructors
    public void hitungMatriksKonvolusiArah(){
        MatriksKonvolusi matriksKonvolusiTemp = this;
        for (int i=0;i<8;i++){
            matriksKonvolusiTemp = matriksKonvolusiTemp.hasilPutarSerongKiri();
            matriksKonvolusiArah[i] = matriksKonvolusiTemp;
        }
    }

    public boolean eksekusi(ExtendedGrayLevelMatrix extendedGrayLevelMatrix, int x, int y, int threshold){
        for (int i=0;i<8;i++){
            boolean hasilIterasiIni = matriksKonvolusiArah[i].eksekusi(extendedGrayLevelMatrix, x, y, threshold);
            if (hasilIterasiIni)
                return true;
        }
        return false;
    }

    public int eksekusi(ExtendedGrayLevelMatrix extendedGrayLevelMatrix, int x, int y){
        int max = Integer.MIN_VALUE;
        for (int i=0;i<8;i++){
            int hasilIterasiIni = matriksKonvolusiArah[i].eksekusi(extendedGrayLevelMatrix, x, y);
            if (hasilIterasiIni > max)
                max = hasilIterasiIni;
        }
        return max;
    }

    public float eksekusiR(ExtendedBitmap extendedBitmap, int x, int y) {
        float hasil = -Float.MAX_VALUE;
        for (int i=0;i<8;i++){
            float hasilIterasiIni = matriksKonvolusiArah[i].eksekusiR(extendedBitmap, x, y);
            if (hasilIterasiIni > hasil)
                hasil=hasilIterasiIni;
        }
        return hasil;
    }
    public float eksekusiG(ExtendedBitmap extendedBitmap, int x, int y) {
        float hasil = -Float.MAX_VALUE;
        for (int i=0;i<8;i++){
            float hasilIterasiIni = matriksKonvolusiArah[i].eksekusiG(extendedBitmap, x, y);
            if (hasilIterasiIni > hasil)
                hasil=hasilIterasiIni;
        }
        return hasil;
    }
    public float eksekusiB(ExtendedBitmap extendedBitmap, int x, int y) {
        float hasil = -Float.MAX_VALUE;
        for (int i=0;i<8;i++){
            float hasilIterasiIni = matriksKonvolusiArah[i].eksekusiB(extendedBitmap, x, y);
            if (hasilIterasiIni > hasil)
                hasil=hasilIterasiIni;
        }
        return hasil;
    }
    public float eksekusiA(ExtendedBitmap extendedBitmap, int x, int y) {
        float hasil = -Float.MAX_VALUE;
        for (int i=0;i<8;i++){
            float hasilIterasiIni = matriksKonvolusiArah[i].eksekusiA(extendedBitmap, x, y);
            if (hasilIterasiIni > hasil)
                hasil=hasilIterasiIni;
        }
        return hasil;
    }
}
