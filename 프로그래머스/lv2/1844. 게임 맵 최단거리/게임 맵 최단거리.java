import java.util.LinkedList;
import java.util.Queue;
import java.util.HashMap;

class Solution {
    public int solution(int[][] maps) {
        int answer = 0;
        int maxRow = maps.length;
        int maxCol = maps[0].length;
        
        HashMap<Character,int[]> dirType = new HashMap<>();
        dirType.put('u',new int[] {-1,0});
        dirType.put('d',new int[] {1,0});
        dirType.put('l',new int[] {0,-1});
        dirType.put('r',new int[] {0,1});
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0,0});
        while(!queue.isEmpty()){
            int[] pos = queue.poll();
            int row = pos[0];
            int col = pos[1];
            for(int[] dir : dirType.values()){
                if(0 <= row+dir[0] && row+dir[0] < maxRow
                   && 0 <= col+dir[1] && col+dir[1] < maxCol
                   && maps[row+dir[0]][col+dir[1]] == 1){
                    maps[row+dir[0]][col+dir[1]] += maps[row][col];
                    queue.offer(new int[] {row+dir[0],col+dir[1]});
                }
            }
        }
        if(maps[maxRow-1][maxCol-1] == 1) return -1;
        return maps[maxRow-1][maxCol-1];
    }
}