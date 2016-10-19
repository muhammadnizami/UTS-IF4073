package nizami_13512501.UTS;

import android.media.Image;

/**
 * Created by nim_13512501 on 18/10/16.
 */
public class CropFrame {

    private int x;
    private int y;
    private int width;
    private int height;

    public CropFrame(int x, int y, int width, int height){
        this.setX(x);
        this.setY(y);
        this.setWidth(width);
        this.setHeight(height);
    }

    public boolean[][] crop(boolean [][] image){
        boolean [][] retval = new boolean[width][height];
        for (int i=0;i<width;i++){
            for (int j=0;j<height;j++){
                retval[i][j] = image[i+x][j+y];
            }
        }
        return retval;
    }

    public int [][] crop(int[][] image){
        int [][] retval = new int[width][height];
        for (int i=0;i<width;i++){
            for (int j=0;j<height;j++){
                retval[i][j] = image[i+x][j+y];
            }
        }
        return retval;
    }

    public static CropFrame ImageToNotBlank(boolean [][] image){
        int min_x = image.length-1;
        for (int y=0;y<image[0].length;y++){
            for (int x=0;x<min_x;x++){
                if (image[x][y])
                    min_x=x;
            }
        }
        int min_y = image[0].length-1;
        for (int x=min_x;x<image.length;x++){
            for (int y=0;y<min_y;y++){
                if (image[x][y])
                    min_y=y;
            }
        }
        int max_x = min_x;
        for (int y=min_y;y<image[0].length;y++) {
            for (int x = image.length - 1; x > max_x; x--) {
                if (image[x][y])
                    max_x = x;
            }
        }
        int max_y = min_y;
        for (int x=min_x;x<=max_x;x++) {
            for (int y = image[0].length - 1; y > max_y; y--) {
                if (image[x][y])
                    max_y = y;
            }
        }
        CropFrame cropFrame = new CropFrame(min_x,min_y,0,0);
        cropFrame.setMaxX(max_x);
        cropFrame.setMaxY(max_y);
        return cropFrame;
    }

    public static boolean [][] cropImageToNotBlank(boolean [][] image){
        CropFrame cropFrame = ImageToNotBlank(image);
        return cropFrame.crop(image);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getMaxY(){
        return y+height-1;
    }
    public void setMaxY(int maxY){
        height = maxY-y+1;
    }

    public int getMaxX(){
        return x+width-1;
    }
    public void setMaxX(int maxX){
        width = maxX-x+1;
    }

    public void shiftX(int dx){
        setX(getX()+dx);
    }

    public void shiftY(int dy){
        setY(getY()+dy);
    }
}
