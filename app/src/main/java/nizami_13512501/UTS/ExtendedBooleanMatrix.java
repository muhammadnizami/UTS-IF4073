package nizami_13512501.UTS;

/**
 * Created by nim_13512501 on 17/10/16.
 */
public class ExtendedBooleanMatrix {
    private boolean[][] booleen;
    ExtendedBooleanMatrix(boolean [][] booleen){
        this.setBooleen(booleen);
    }

    boolean getPixel(int x, int y){
        int booleen_x = x;
        int booleen_y = y;
        if (booleen_x >= getBooleen().length)
            booleen_x = getBooleen().length-1;
        if (booleen_x < 0)
            booleen_x = 0;
        if (booleen_y >= getBooleen()[booleen_x].length)
            booleen_y = getBooleen()[booleen_x].length-1;
        if (booleen_y < 0)
            booleen_y = 0;
        return getBooleen()[booleen_x][booleen_y];
    }

    public boolean getPixel(HilditchSkeletonization.XYPoint p){
        return getPixel(p.x,p.y);
    }

    public int getWidth(){
        return getBooleen().length;
    }

    public int getHeight(){
        return getBooleen().length>0? getBooleen()[0].length:0;
    }

    public boolean[][] getBooleen() {
        return booleen;
    }

    public void setBooleen(boolean[][] booleen) {
            this.booleen = booleen;
        }
}
