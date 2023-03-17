import java.util.Arrays;
class Solution {
    public int solution(int n) {
        int answer = 0;
        boolean[] prime = new boolean[n+1];
        Arrays.fill(prime,true);
        prime[0] = false;prime[1] = false;
        for(int i = 2;i<=Math.sqrt(n)+1;i++){
            if(prime[i]){
                for(int j = (int)Math.pow(i,2);j<=n;j+=i) 
                    prime[j] = false;
            }
        }
        for(int i = 2;i<=n;i++){
            if(prime[i]) answer++;
        }
        return answer;
    }
}