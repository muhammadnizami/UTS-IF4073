package nizami_13512501.UTS;

/**
 * Created by nim_13512501 on 05/10/16.
 */
public class ShapeDetect {

    public static final DirectionAlphabetDFA DFAKotak = new DirectionAlphabetDFA(
           21,
            new int[] {4,5,9,10,14,15,19,20},
            new int[][]{
                    {-1,1,-1,6,-1,11,-1,16,-1},
                    {-1,1,-1,2,-1,-1,-1,-1,-1},
                    {-1,-1,-1,2,-1,3,-1,-1,-1},
                    {-1,-1,-1,-1,-1,3,-1,4,-1},
                    {-1,5,-1,-1,-1,-1,-1,4,-1},
                    {-1,5,-1,-1,-1,-1,-1,-1,-1},
                    {-1,-1,-1,6,-1,7,-1,-1,-1},
                    {-1,-1,-1,-1,-1,7,-1,8,-1},
                    {-1,9,-1,-1,-1,-1,-1,8,-1},
                    {-1,9,-1,10,-1,-1,-1,-1,-1},
                    {-1,1,-1,10,-1,-1,-1,-1,-1},
                    {-1,-1,-1,-1,-1,11,-1,12,-1},
                    {-1,13,-1,-1,-1,-1,-1,12,-1},
                    {-1,13,-1,14,-1,-1,-1,-1,-1},
                    {-1,-1,-1,14,-1,15,-1,-1,-1},
                    {-1,1,-1,-1,-1,-15,-1,-1,-1},
                    {-1,17,-1,-1,-1,-1,-1,16,-1},
                    {-1,17,-1,18,-1,-1,-1,-1,-1},
                    {-1,-1,-1,18,-1,19,-1,-1,-1},
                    {-1,-1,-1,-1,-1,19,-1,20,-1},
                    {-1,1,-1,-1,-1,-1,-1,-20,-1}
            }
    );

    public static final DirectionAlphabetDFA DFAAngka2 = new DirectionAlphabetDFA(
            23,
            new int[] {16,17},
            new int[][]{
                    {-1,-1,-1,1,-1,-1,-1,-1,-1},
                    {-1,-1,-1,1,-1,2,-1,-1,-1},
                    {-1,-1,-1,-1,-1,2,-1,3,-1},
                    {-1,-1,-1,-1,-1,5,4,3,-1},
                    {-1,-1,-1,-1,-1,5,-1,-1,-1},
                    {-1,-1,-1,7,6,5,-1,-1,-1},
                    {-1,-1,-1,7,6,-1,-1,-1,-1},
                    {-1,-1,-1,7,-1,8,-1,-1,-1},
                    {-1,-1,-1,-1,-1,8,-1,9,-1},
                    {-1,10,-1,-1,-1,-1,-1,9,-1},
                    {-1,10,-1,11,-1,-1,-1,-1,-1},
                    {-1,13,12,11,-1,-1,-1,-1,-1},
                    {-1,13,12,-1,-1,-1,-1,-1,-1},
                    {-1,13,-1,-1,-1,-1,-1,15,14},
                    {-1,-1,-1,-1,-1,-1,-1,15,14},
                    {-1,16,-1,-1,-1,-1,-1,15,-1},
                    {-1,16,-1,17,-1,-1,-1,-1,-1},
                    {-1,-1,-1,17,-1,-1,-1,-1,-1},
            }
    );

    public static final DirectionAlphabetDFA [] DFAs = new DirectionAlphabetDFA[]{
            DFAKotak,
            DFAAngka2
    };

    public static final String namaBentuk[] = {
            "kotak",
            "angka 2"
    };

    public static boolean isKotak(Integer[] chainCode){
        return DFAKotak.accepts(chainCode);
    }

    public static String getNamaBentuk(Integer[] chainCode){
        for (int i=0;i<DFAs.length && i< namaBentuk.length; i++){
            if (DFAs[i].accepts(chainCode))
                return namaBentuk[i];
        }
        return "unknown";
    }
}
