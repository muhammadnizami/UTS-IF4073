package nizami_13512501.UTS;

/**
 * Created by nim_13512501 on 05/10/16.
 */
public class ExtendedGrayLevelMatrix {
    private int [][] grayLevel;
    ExtendedGrayLevelMatrix(int [][] grayLevel){
        this.setGrayLevel(grayLevel);
    }

    int getPixel(int x, int y){
        int grayLevel_x = x;
        int grayLevel_y = y;
        if (grayLevel_x >= getGrayLevel().length)
            grayLevel_x = getGrayLevel().length-1;
        if (grayLevel_x < 0)
            grayLevel_x = 0;
        if (grayLevel_y >= getGrayLevel()[grayLevel_x].length)
            grayLevel_y = getGrayLevel()[grayLevel_x].length-1;
        if (grayLevel_y < 0)
            grayLevel_y = 0;
        return getGrayLevel()[grayLevel_x][grayLevel_y];
    }

    public int getWidth(){
        return getGrayLevel().length;
    }

    public int getHeight(){
        return getGrayLevel().length>0? getGrayLevel()[0].length:0;
    }

    public int[][] getGrayLevel() {
        return grayLevel;
    }

    public void setGrayLevel(int[][] grayLevel) {
        this.grayLevel = grayLevel;
    }
}
