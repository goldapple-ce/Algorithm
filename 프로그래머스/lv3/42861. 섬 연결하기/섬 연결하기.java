import java.util.Arrays;

class Solution {
    int[] dp;
    public int solution(int n, int[][] costs) {
        int answer = 0;
        dp = new int[n];
        for(int i = 0; i < n; i++) dp[i] = i;
        Arrays.sort(costs,(o1,o2) -> o1[2] - o2[2]);

        for(int[] cost : costs){
            int start= cost[0], end=cost[1], dist=cost[2];
            if(find(start) != find(end)){
                union(start,end);
                answer += dist;
            }
        }
        
        return answer;
    }
    public int find(int x){
        if(dp[x] == x) return dp[x];
        return find(dp[x]);
    }
    
    public void union(int x, int y){
        int start = find(x);
        int end = find(y);
        dp[start] = end;
    }
}