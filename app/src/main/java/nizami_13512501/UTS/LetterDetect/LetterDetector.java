package nizami_13512501.UTS.LetterDetect;

/**
 * Created by nim_13512501 on 18/10/16.
 */
public abstract class LetterDetector {
    abstract public boolean detect(boolean [][] skeleton);

    /**helper methods yang mungkin dibutuhkan berbagainya*/
    public int scanNumVerticalLines(boolean [][] skeleton, int y){
        boolean lastVal = false;
        int num = 0;
        for (int x=0;x<skeleton.length;x++){
            boolean curVal = skeleton[x][y];
            if (lastVal && !curVal)
                num++;
            lastVal = skeleton[x][y];
        }
        if (lastVal)
            num++;
        return num;
    }
}
