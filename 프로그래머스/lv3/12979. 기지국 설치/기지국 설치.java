class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0, state = 0, now =1;
        
        while(now <= n){
            if(state < stations.length && stations[state]-w <= now){
                now = stations[state++] + w +1;
            }else{
                now += 2*w +1;
                answer++;
            }
        }

        return answer;
    }
}