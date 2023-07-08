import java.util.*;

class Solution {
    int MOD = 20170805;

    public int solution(int m, int n, int[][] cityMap) {
        int answer = 0;
        int[][][] dp = new int[2][m+1][n+1];
        dp[0][1][1] = dp[1][1][1] = 1;
        for(int row = 1 ; row <= m ; row++){
              for(int col = 1 ; col <= n ; col++){
                  if(cityMap[row - 1][col - 1] == 0){
                      dp[0][row][col] += (dp[0][row - 1][col] + dp[1][row][col - 1]) % MOD;
                      dp[1][row][col] += (dp[0][row - 1][col] + dp[1][row][col - 1]) % MOD;
                  } else if(cityMap[row - 1][col - 1] == 1){
                      dp[0][row][col] = 0;
                      dp[1][row][col] = 0;
                  } else {
                      dp[0][row][col] = dp[0][row - 1][col];
                      dp[1][row][col] = dp[1][row][col - 1];
                  }

              }
          }
        
        
        return dp[0][m][n];
    }
    
    public void print(int[][][] map){
        for(int j = 0; j <2; j++){
            for(int i = 0; i < map[0].length; i++){
                System.out.println(Arrays.toString(map[j][i]));
            }
            System.out.println();
        }
    }
    
}