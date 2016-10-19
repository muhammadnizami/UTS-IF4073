package nizami_13512501.UTS;

import java.util.Map;

/**
 * Created by nim_13512501 on 15/10/16.
 */
public class Otsu {

    public static int sum(int [] arr){
        int sum = 0;
        for (int x : arr){
            sum+=x;
        }
        return sum;
    }

    public static int sum(Map<Integer, Integer> hist){
        int sum = 0;
        for (Integer x : hist.values()){
            sum+=x;
        }
        return sum;
    }

    public static long dot(Map<Integer, Integer> hist){
        long dot = 0;
        for (Map.Entry<Integer,Integer> x : hist.entrySet()){
            dot+=x.getKey()*x.getValue();
        }
        return dot;
    }

    public static int otsu(Map<Integer, Integer> hist){
        int total= sum(hist);
        int sumB = 0;
        long wB = 0;
        double maximum = 0.0;
        long sum1 = dot(hist);
        int level = 0;
        for(Map.Entry<Integer,Integer> entry : hist.entrySet()){
            wB += entry.getValue();
            if (wB == 0)
                continue;
            long wF = total - wB;
            if (wF == 0)
                break;
            sumB += (entry.getKey()-1) * entry.getValue();
            double mB = sumB / wB;
            double mF = (sum1 - sumB) / wF;
            double between = wB * wF * (mB - mF) * (mB - mF);
            if (between >= maximum ){
                level = entry.getKey();
                maximum = between;
            }
        }
        return level;
    }

    public static int otsu(int[][] grayLevel){
        return otsu(Histogram.hitungKemunculan(grayLevel));
    }
}
