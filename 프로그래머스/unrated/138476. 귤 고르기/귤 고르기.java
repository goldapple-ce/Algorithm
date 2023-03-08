import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

class Solution {
    public int solution(int k, int[] tangerines) {
        int answer = 0;
        HashMap<Integer,Integer> counter = new HashMap<Integer,Integer>();
        for(int tangerine : tangerines){
            counter.put(tangerine,counter.getOrDefault(tangerine,0)+1);
        }
        
        List<Map.Entry<Integer, Integer>> counterList = new ArrayList<>(counter.entrySet());
        counterList.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        for (Map.Entry<Integer, Integer> entry : counterList) {
            if(k<=0) break;
            answer++;
            k -= entry.getValue();
        }
        return answer;
    }
}