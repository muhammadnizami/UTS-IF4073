package nizami_13512501.UTS.LetterDetect;

/**
 * Created by nim_13512501 on 18/10/16.
 */
public class LetterDetectorA extends LetterDetector {
    public boolean detect(boolean [][] skeleton){
        boolean topCondition = false;
        boolean topConditionFail = false;
        for (int i=0;i<skeleton[0].length/2 && ! topCondition && ! topConditionFail;i++){
            int n = scanNumVerticalLines(skeleton,i);
            if (n==1)
                topCondition = true;
            else if (n>=1)
                topConditionFail = true;
        }
        if (!topCondition)
            return false;

        boolean bottomCondition = false;
        for (int i=skeleton[0].length-1; i >= skeleton[0].length/2 && !bottomCondition;i--){
            if (scanNumVerticalLines(skeleton,i)==2)
                bottomCondition = true;
        }
        return topCondition && bottomCondition;
    }
}
