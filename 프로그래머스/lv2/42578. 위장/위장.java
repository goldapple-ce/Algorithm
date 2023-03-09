import java.util.Collection;
import java.util.HashMap;
class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        HashMap<String,Integer> dict = new HashMap<String,Integer>();
        for(String[] clothe : clothes){
            dict.put(clothe[1],dict.getOrDefault(clothe[1],1)+1);
        }
        Collection<Integer> values = dict.values();
        for(int value:values){
            answer *= value;
        }
        
        return answer-1;
    }
}