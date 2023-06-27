class Solution {
    public long solution(int n, int[] times) {
        long answer = 0, minTime = 1, maxTime = 1;
        for(int time : times) maxTime = Math.max(maxTime,time);
        maxTime *= n;
        
        while(minTime <= maxTime){
            long midTime = (minTime+maxTime)/2;
            long people = 0;
            
            for(int time:times){
                people += midTime/time;
                if(people >= n) break;
            }
            
            if(people >= n){
                answer = midTime;
                maxTime = midTime-1;
            }else{
                minTime = midTime+1;
            }
        }

        return answer;
    }
}