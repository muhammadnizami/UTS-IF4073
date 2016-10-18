package nizami_13512501.UTS.LetterDetect;

import android.support.v4.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nim_13512501 on 18/10/16.
 */
public class LetterDetectorMap {
    Pair<LetterDetector, Character> [] listDetector;

    public LetterDetectorMap(Pair<LetterDetector,Character> [] listDetector){
        this.listDetector = listDetector;
    }

    public char detect(boolean[][] skeleton){
        for (Pair<LetterDetector,Character> pair : listDetector){
            if (pair.first.detect(skeleton))
                return pair.second;
        }
        return '*';
    }

    public static final Pair<LetterDetector,Character> [] capitalDetectorListDetector = new Pair[]{new Pair<LetterDetector, Character>(new LetterDetectorA(), 'A')};
    public static final LetterDetectorMap capitalDetector = new LetterDetectorMap(capitalDetectorListDetector);
}
