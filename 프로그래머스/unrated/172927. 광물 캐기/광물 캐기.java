import java.util.HashMap;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        int maxLength = Math.min(minerals.length%5 == 0 ?minerals.length/5 : minerals.length/5+1,Arrays.stream(picks).sum());
        int[][] fatigues = new int[maxLength][5];
        
        HashMap<String,Integer> dict = new HashMap<>();
        dict.put("diamond",25);
        dict.put("iron",5);
        dict.put("stone",1);
        
        for(int i = 0; i < Math.min(minerals.length,maxLength*5);i++){
            fatigues[i/5][i%5] = dict.get(minerals[i]);
        }
        
        Arrays.sort(fatigues,new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return Arrays.stream(o2).sum() - Arrays.stream(o1).sum();
            }
        });
        
        for(int i = 0, cnt = 0; i < 3; i++){
            for(int j = 0 ; j < picks[i] && cnt < maxLength;j++, cnt++){
                for(int k = 0; k < 5; k++){
                    answer+= Math.ceil(fatigues[cnt][k]/Math.pow(5,2-i));
                }
            }
        }
        
        return answer;
    }
    
}