package ArrayAndStringType.Count_Elt_Greater_CurrAvg;

import java.util.*;

public class countEltGreaterCurrAvg {
    
}

class Result {

    /*
     * Complete the 'countResponseTimeRegressions' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY responseTimes as parameter.
     */

    public static int countResponseTimeRegressions(List<Integer> responseTimes) {
        if (responseTimes.size() == 0 || responseTimes.size() == 1) return 0;
        
        int count = 0;
        long total = responseTimes.get(0);
        double prevAvg = total;
        for (int i = 1; i < responseTimes.size(); i++){
            int curr = responseTimes.get(i);
            
            if (curr > prevAvg){
                count++;
            }
            
            total += curr;
            prevAvg = (double)total / (i + 1);
        }
        return count;

    }

}
