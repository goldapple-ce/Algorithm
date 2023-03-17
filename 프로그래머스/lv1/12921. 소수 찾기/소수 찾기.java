import java.util.Arrays;
class Solution {
    public int solution(int n) {
        int answer = 0;
        boolean[] prime = new boolean[n+1];
        Arrays.fill(prime,true);
        for(int i = 2;i<n+1;i++){
            if(prime[i]){
                answer++;
                for(int j = (int)Math.pow(i,2);j<=n;j+=i) 
                    prime[j] = false;
            }
        }
        return answer;
    }
}