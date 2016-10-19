package nizami_13512501.UTS.LetterDetect;

import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.util.Pair;

/**
 * Created by nim_13512501 on 18/10/16.
 */
public class IdealLetterMap {
    Pair<IdealLetter, Character>[] listIdealLetter;

    public IdealLetterMap(Pair<IdealLetter,Character> [] listDetector){
        this.listIdealLetter = listDetector;
    }

    public char detect(boolean[][] skeleton){
        char chosenCharacter = '*' ;
        double maxError = Double.MAX_VALUE;
        for (Pair<IdealLetter,Character> p : listIdealLetter){
            double error = p.first.cropAndCalculateError(skeleton);
            if (error<maxError){
                maxError = error;
                chosenCharacter = p.second;
            }
        }
        return chosenCharacter;
    }



    //constants
    public static final IdealLetter idealLetterA = new IdealLetter(new boolean[][]{
            {false,false,false,true,true,true,true},
            {false,true,true,true,false,false,false},
            {true,false,false,true,false,false,false},
            {false,true,true,true,false,false,false},
            {false,false,false,true,true,true,true},
    });
    public static final IdealLetter idealLetterB = new IdealLetter(new boolean[][]{
            {true,true,true,true,true,true,true},
            {true,false,false,true,false,false,true},
            {true,false,false,true,false,false,true},
            {true,true,false,true,false,true,true},
            {false,true,true,true,true,true,false}
    });
    public static final IdealLetter idealLetterC = new IdealLetter(new boolean[][]{
            {false,true,true,true,true,true,false},
            {true,true,false,false,false,true,true},
            {true,false,false,false,false,false,true},
            {true,false,false,false,false,false,true},
            {true,false,false,false,false,false,true},
    });
    public static final IdealLetter idealLetterD = new IdealLetter(new boolean[][]{
            {true,true,true,true,true,true,true},
            {true,true,false,false,false,true,true},
            {true,false,false,false,false,false,true},
            {true,false,false,false,false,false,true},
            {false,true,true,true,true,true,false},
    });
    public static final IdealLetter idealLetterE = new IdealLetter(new boolean[][]{
            {true,true,true,true,true,true,true},
            {true,false,false,true,false,false,true},
            {true,false,false,true,false,false,true},
            {true,false,false,true,false,false,true},
            {true,false,false,true,false,false,true},
    });
    public static final IdealLetter idealLetterF = new IdealLetter(new boolean[][]{
            {true,true,true,true,true,true,true},
            {true,false,false,true,false,false,false},
            {true,false,false,true,false,false,false},
            {true,false,false,true,false,false,false},
            {true,false,false,true,false,false,false},
    });
    public static final IdealLetter idealLetterG = new IdealLetter(new boolean[][]{
            {false,true,true,true,true,true,},
            {true,true,false,false,false,true,true},
            {true,false,false,false,false,false,true},
            {true,true,false,true,false,false,true},
            {false,true,false,true,true,true,true},
    });
    public static final IdealLetter idealLetterH = new IdealLetter(new boolean[][]{
            {true,true,true,true,true,true,true},
            {false,false,false,true,false,false,false},
            {false,false,false,true,false,false,false},
            {false,false,false,true,false,false,false},
            {false,false,false,true,false,false,false},
            {false,false,false,true,false,false,false},
            {true,true,true,true,true,true,true}
    });
    public static final IdealLetter idealLetterI = new IdealLetter(new boolean[][]{
            {false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false},
            {true,true,true,true,true,true,true},
            {false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false}
    });
    public static final IdealLetter idealLetterJ = new IdealLetter(new boolean[][]{
            {false, false, false, false, true, true, false},
            {false, false, false, false, false, true, true},
            {false, false, false, false, false, false, true},
            {true, false, false, false, false, true, true},
            {true,true,true,true,true,true,false},
    });
    public static final IdealLetter idealLetterK = new IdealLetter(new boolean[][]{
            {true,true,true,true,true,true,true},
            {false,false,false,true,false,false,false},
            {false,false,true,false,true,false,false},
            {false,true,false,false,false,true,false},
            {true,false,false,false,false,false,true},
    });
    public static final IdealLetter idealLetterL = new IdealLetter(new boolean[][]{
            {true,true,true,true,true,true,true},
            {false,false,false,false,false,false,true},
            {false,false,false,false,false,false,true},
            {false,false,false,false,false,false,true},
            {false,false,false,false,false,false,true},
    });
    public static final IdealLetter idealLetterM = new IdealLetter(new boolean[][]{
            {true,true,true,true,true,true,true},
            {false,true,false,false,false,false,false},
            {false,false,true,false,false,false,false},
            {false,false,false,true,true,false,false},
            {false,false,true,false,false,false,false},
            {false,true,false,false,false,false,false},
            {true,true,true,true,true,true,true},
    });
    public static final IdealLetter idealLetterN = new IdealLetter(new boolean[][]{
            {true,true,true,true,true,true,true,true,true,true,true},
            {false,true,false,false,false,false,false,false,false,false,false},
            {false,false,true,false,false,false,false,false,false,false,false},
            {false,false,false,true,false,false,false,false,false,false,false},
            {false,false,false,false,true,false,false,false,false,false,false},
            {false,false,false,false,false,true,false,false,false,false,false},
            {false,false,false,false,false,false,true,false,false,false,false},
            {false,false,false,false,false,false,false,true,false,false,false},
            {false,false,false,false,false,false,false,false,true,false,false},
            {false,false,false,false,false,false,false,false,false,true,false},
            {true,true,true,true,true,true,true,true,true,true,true},
    });
    public static final IdealLetter idealLetterN2 = new IdealLetter(new boolean[][]{
            {true,true,true,true,true,true,true,true,true,true,true},
            {false,false,true,false,false,false,false,false,false,false,false},
            {false,false,false,true,false,false,false,false,false,false,false},
            {false,false,false,false,true,false,false,false,false,false,false},
            {false,false,false,false,false,true,false,false,false,false,false},
            {false,false,false,false,false,false,true,false,false,false,false},
            {false,false,false,false,false,false,false,true,false,false,false},
            {false,false,false,false,false,false,false,false,true,false,false},
            {true,true,true,true,true,true,true,true,true,true,true},
    });
    public static final IdealLetter idealLetterO = new IdealLetter(new boolean[][]{
            {false,true,true,true,true,true,false},
            {true,true,false,false,false,true,true},
            {true,false,false,false,false,false,true},
            {true,true,false,false,false,true,true},
            {false,true,true,true,true,true,false}
    });
    public static final IdealLetter idealLetterP = new IdealLetter(new boolean[][]{
            {true,true,true,true,true,true,true,true,true},
            {true,false,false,false,true,false,false,false,false},
            {true,false,false,false,true,false,false,false,false},
            {true,true,false,true,true,false,false,false,false},
            {false,true,true,true,false,false,false,false,false}
    });
    public static final IdealLetter idealLetterR = new IdealLetter(new boolean[][]{
            {true,true,true,true,true,true,true,true,true},
            {true,false,false,false,true,false,false,false,false},
            {true,false,false,false,true,true,true,false,false},
            {true,true,false,true,true,false,true,true,false},
            {false,true,true,true,false,false,false,true,true}
    });
    public static final IdealLetter idealLetterS = new IdealLetter(new boolean[][]{
            {false,true,true,false,false,true,true},
            {true,true,false,true,false,false,true},
            {true,false,false,true,false,false,true},
            {true,false,false,true,false,true,true},
            {true,true,false,false,true,true,false},
    });
    public static final IdealLetter idealLetterS2 = new IdealLetter(new boolean[][]{
            {false,false,false,false,false,false,true},
            {false,true,true,true,false,false,true},
            {true,false,false,true,false,false,true},
            {true,false,false,false,false,true,false},
            {true,false,false,false,false,false,false},
    });
    public static final IdealLetter idealLetterT = new IdealLetter(new boolean[][]{
            {true, false, false, false, false, false, false},
            {true, false, false, false, false, false, false},
            {true, true, true, true, true, true, true},
            {true, false, false, false, false, false, false},
            {true, false, false, false, false, false, false}
    });
    public static final IdealLetter idealLetterU = new IdealLetter(new boolean[][]{
            {true,true,true,true,true,true,false},
            {false,false,false,false,false,true,true},
            {false,false,false,false,false,false,true},
            {false,false,false,false,false,true,true},
            {true,true,true,true,true,true,false}
    });
    public static final IdealLetter idealLetterW = new IdealLetter(new boolean[][]{
            {true,true,true,true,true,true,false},
            {false,false,false,false,false,true,true},
            {false,false,false,false,false,false,true},
            {false,false,false,false,false,true,true},
            {true,true,true,true,true,true,false},
            {false,false,false,false,false,true,true},
            {false,false,false,false,false,false,true},
            {false,false,false,false,false,true,true},
            {true,true,true,true,true,true,false}
    });
    public static final IdealLetter idealLetterY = new IdealLetter(new boolean[][]{
            {true,true,false,false,false,false,false},
            {false,true,true,true,false,false,false},
            {false,false,false,true,true,true,true},
            {false,true,true,true,false,false,false},
            {true,true,false,false,false,false,false}
    });
    public static final IdealLetter idealLetterZ = new IdealLetter(new boolean[][]{
            {true,false,false,false,false,true,true},
            {true,false,false,false,true,false,true},
            {true,false,false,true,false,false,true},
            {true,false,true,false,false,false,true},
            {true,true,false,false,false,false,true},
    });

    public static final Pair<IdealLetter,Character> [] capitalListIdealLetter = new Pair[]{
            new Pair<>(idealLetterA, 'A'),
            new Pair<>(idealLetterB, 'B'),
            new Pair<>(idealLetterC, 'C'),
            new Pair<>(idealLetterD, 'D'),
            new Pair<>(idealLetterE, 'E'),
            new Pair<>(idealLetterF, 'F'),
            new Pair<>(idealLetterG, 'G'),
            new Pair<>(idealLetterH, 'H'),
            new Pair<>(idealLetterI, 'I'),
            new Pair<>(idealLetterJ, 'J'),
            new Pair<>(idealLetterK, 'K'),
            new Pair<>(idealLetterL, 'L'),
            new Pair<>(idealLetterM, 'M'),
            new Pair<>(idealLetterN, 'N'),
            new Pair<>(idealLetterN2, 'N'),
            new Pair<>(idealLetterO, 'O'),
            new Pair<>(idealLetterP, 'P'),
            new Pair<>(idealLetterR, 'R'),
            new Pair<>(idealLetterS, 'S'),
            new Pair<>(idealLetterS2, 'S'),
            new Pair<>(idealLetterT, 'T'),
            new Pair<>(idealLetterU, 'U'),
            new Pair<>(idealLetterW, 'W'),
            new Pair<>(idealLetterY, 'Y'),
            new Pair<>(idealLetterZ, 'Z')
    };
    public static final IdealLetterMap capitalMap = new IdealLetterMap(capitalListIdealLetter);

    public static final IdealLetter idealLetter1 = new IdealLetter(new boolean[][]{
            {false,false,false,false,false,false,false},
            {false,true,false,false,false,false,false},
            {true,true,true,true,true,true,true},
            {false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false}
    });
    public static final IdealLetter idealLetter2 = new IdealLetter(new boolean[][]{
            {true,false,false,false,false,false,false,true},
            {true,false,false,false,false,true,true,true},
            {true,true,false,false,false,true,false,true},
            {false,true,false,false,true,false,false,true},
            {false,true,true,true,true,false,false,true},
    });
    public static final IdealLetter idealLetter3 = new IdealLetter(new boolean[][]{
            {true,false,false,false,true,false,false,false,true},
            {true,false,false,false,true,false,false,false,true},
            {true,false,false,false,true,false,false,false,true},
            {true,true,false,true,true,true,false,true,true},
            {false,true,true,true,false,true,true,true,false},
    });
    public static final IdealLetter idealLetter4 = new IdealLetter(new boolean[][]{
            {false,false,false,false,true,false,false,false,false},
            {false,false,false,true,true,false,false,false,false},
            {false,false,true,false,true,false,false,false,false},
            {false,true,false,false,true,false,false,false,false},
            {true,true,true,true,true,true,true,true,true}
    });
    public static final IdealLetter idealLetter5 = new IdealLetter(new boolean[][]{
            {true,false,false,false,true,true,false},
            {true,false,false,true,false,false,true},
            {true,false,false,true,false,false,true},
            {true,false,true,true,false,false,true},
            {true,true,true,false,false,false,true},
    });
    public static final IdealLetter idealLetter6 = new IdealLetter(new boolean[][]{
            {true,false,false,false,true,true,false},
            {true,false,false,true,false,false,true},
            {true,false,false,true,false,false,true},
            {false,true,false,true,false,true,true},
            {false,false,true,true,true,true,false},
    });
    public static final IdealLetter idealLetter7 = new IdealLetter(new boolean[][]{
            {true,false,false,false,false,false,false},
            {true,true,false,false,false,false,false},
            {true,false,true,false,false,false,false},
            {true,false,false,true,false,false,false},
            {true,false,false,false,true,false,false},
            {true,false,false,false,false,true,false},
            {true,false,false,false,false,false,true}
    });
    public static final IdealLetter idealLetter8 = new IdealLetter(new boolean[][]{
            {false,true,true,false,true,true,false},
            {true,false,false,true,false,false,true},
            {true,false,false,true,false,false,true},
            {true,false,false,true,false,false,true},
            {false,true,true,false,true,true,false},
    });
    public static final IdealLetter idealLetter9 = new IdealLetter(new boolean[][]{
            {false,true,true,true,true,false,false},
            {true,true,false,true,false,true,false},
            {true,false,false,true,false,false,true},
            {true,false,false,true,false,false,true},
            {false,true,true,false,false,false,true}
    });

    public static final Pair<IdealLetter,Character>[] numberListIdealLetter = new Pair[]{
            new Pair<IdealLetter,Character>(idealLetter1,'1'),
            new Pair<IdealLetter,Character>(idealLetter2,'2'),
            new Pair<IdealLetter,Character>(idealLetter3,'3'),
            new Pair<IdealLetter,Character>(idealLetter4,'4'),
            new Pair<IdealLetter,Character>(idealLetter5,'5'),
            new Pair<IdealLetter,Character>(idealLetter6,'6'),
            new Pair<IdealLetter,Character>(idealLetter7,'7'),
            new Pair<IdealLetter,Character>(idealLetter8,'8'),
            new Pair<IdealLetter,Character>(idealLetter9,'9'),
    };
    public static final IdealLetterMap numberMap = new IdealLetterMap(numberListIdealLetter);

    public static final Pair[] numberAndCapitalListIdealLetter = new Pair[]{
            new Pair<>(idealLetter1, '1'),
            new Pair<>(idealLetter2, '2'),
            new Pair<>(idealLetter3, '3'),
            new Pair<>(idealLetter4, '4'),
            new Pair<>(idealLetter5, '5'),
            new Pair<>(idealLetter6, '6'),
            new Pair<>(idealLetter7, '7'),
            new Pair<>(idealLetter8, '8'),
            new Pair<>(idealLetter9, '9'),
            new Pair<>(idealLetterA, 'A'),
            new Pair<>(idealLetterB, 'B'),
            new Pair<>(idealLetterC, 'C'),
            new Pair<>(idealLetterD, 'D'),
            new Pair<>(idealLetterE, 'E'),
            new Pair<>(idealLetterF, 'F'),
            new Pair<>(idealLetterG, 'G'),
            new Pair<>(idealLetterH, 'H'),
            new Pair<>(idealLetterI, 'I'),
            new Pair<>(idealLetterJ, 'J'),
            new Pair<>(idealLetterK, 'K'),
            new Pair<>(idealLetterL, 'L'),
            new Pair<>(idealLetterM, 'M'),
            new Pair<>(idealLetterN, 'N'),
            new Pair<>(idealLetterN2, 'N'),
            new Pair<>(idealLetterO, 'O'),
            new Pair<>(idealLetterP, 'P'),
            new Pair<>(idealLetterR, 'R'),
            new Pair<>(idealLetterS, 'S'),
            new Pair<>(idealLetterS2, 'S'),
            new Pair<>(idealLetterT, 'T'),
            new Pair<>(idealLetterU, 'U'),
            new Pair<>(idealLetterW, 'W'),
            new Pair<>(idealLetterY, 'Y'),
            new Pair<>(idealLetterZ, 'Z')
    };
    public static final IdealLetterMap numberAndCapitalMap = new IdealLetterMap(numberAndCapitalListIdealLetter);
}
