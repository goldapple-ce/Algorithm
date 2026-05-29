import java.util.*;
import java.io.*;

class Solution {
    int[][] map;
    int[][] dirType = {{0,1},{0,-1},{1,0},{-1,0}};
    boolean[][] visited;
    int[] cnts;
    int maxRow, maxCol;
    int answer = 0;
    
    public int solution(int[][] land) {
        map = land;
        maxRow = land.length;
        maxCol = land[0].length;
        visited = new boolean[maxRow][maxCol];
        cnts = new int[maxCol];
        
        for(int row = 0; row < maxRow; row++){
            for(int col = 0; col < maxCol; col++){
                if(land[row][col] == 1 && !visited[row][col]){
                    dfs(new Position(row,col));
                }
            }
        }
        for(int cnt : cnts){
            answer = Math.max(answer, cnt);
        }
        return answer;
    }
    
    void dfs(Position start){
        boolean[] vis = new boolean[maxCol];
        int cnt = 0;
        
        Deque<Position> queue = new ArrayDeque<>();
        queue.offer(start);
        
        while(!queue.isEmpty()){
            Position now = queue.poll();
            
            if(visited[now.row][now.col]) continue;
            visited[now.row][now.col] = true;
            vis[now.col] = true;
            cnt++;
            
            for(int[] dir : dirType){
                int nRow = now.row + dir[0];
                int nCol = now.col + dir[1];
                
                if(inRange(nRow, nCol) && map[nRow][nCol] == 1 && !visited[nRow][nCol]){
                    queue.offer(new Position(nRow, nCol));
                }
            }
        
        }          
        
        for(int col = 0; col < maxCol; col++){
            if(vis[col]){
                cnts[col] += cnt;
            }
        }
    }
    
    boolean inRange(int row, int col){
        return 0 <= row && row < maxRow && 0 <= col && col < maxCol;
    }
    
    class Position{
        int row, col;
        
        public Position(int row, int col){
            this.row = row;
            this.col = col;
        }
        
        @Override
        public String toString(){
            return String.format("row=%d,col=%d",this.row, this.col);
        }
    }
}