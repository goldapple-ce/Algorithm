import java.util.Arrays;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n];
        
        for(int i = 0; i<n;i++){
            if(!visited[i]){
                dfs(i,visited,computers);
                answer++;
            }
        }

        return answer;
    }
    
    public void dfs(int row,boolean[] visited,int[][] computers){
        visited[row] = true;
        
        for(int i = 0; i<computers.length; i++){
            if(computers[row][i] == 1 && !visited[i]){
                dfs(i,visited,computers);
            }
        }
    }
}