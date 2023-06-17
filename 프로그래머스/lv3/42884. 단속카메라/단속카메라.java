import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0,idx = 0;
        Arrays.sort(routes,(o1,o2)->{
           return o1[1] - o2[1];
        });
        
        while(idx < routes.length){
            int end = routes[idx++][1];
            while(idx < routes.length && inRange(routes[idx],end)){
                idx++;
            }
            answer++;
        }
        return answer;
    }
    
    public boolean inRange(int[] range, int target){
        return range[0] <= target && target <= range[1]; 
    }
    
}