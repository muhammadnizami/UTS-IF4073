package nizami_13512501.UTS.LetterDetect;

/**
 * Created by nim_13512501 on 19/10/16.
 */
public class DetectSpace {
    public static boolean isSpace(boolean [][] image, int threshold){
        int numTrue = 0;
        for (boolean [] ir : image)
            for (boolean i : ir)
                if (i) numTrue ++;
        return numTrue < threshold;
    }
}
