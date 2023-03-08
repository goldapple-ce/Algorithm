import java.util.Arrays;
class Solution {
    public int solution(int[] d, int budget) {
        int answer = 0,sum = 0,idx =0;
        Arrays.sort(d);
        while(idx < d.length && sum+d[idx]<=budget){
            sum += d[idx++];
            answer++;
        }
        
        return answer;
    }
}