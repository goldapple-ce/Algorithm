import java.util.*;

class Solution {
    public int solution(int[][] board, int[][] skills) {
        int answer = 0;
        int maxRow = board.length, maxCol = board[0].length;
        int[][] damages = new int[maxRow+1][maxCol+1];
        
        for(int[] skill : skills){
            int sRow = skill[1], sCol = skill[2];
            int fRow = skill[3]+1, fCol = skill[4]+1;
            int damage = (skill[0]==1) ?-1*skill[5] :skill[5];
            
            damages[sRow][sCol] += damage;
            damages[sRow][fCol] += -1*damage;
            damages[fRow][sCol] += -1*damage;
            damages[fRow][fCol] += damage;
        }
        
        for(int row = 1; row < maxRow; row++){
            for(int col = 0; col < maxCol; col++){
                damages[row][col] += damages[row-1][col];
            }
        }
        for(int col = 1; col < maxRow; col++){
            for(int row = 0; row < maxCol; row++){
                damages[row][col] += damages[row][col-1];
            }
        }
        
        for(int row = 0; row < maxRow; row++){
            for(int col = 0; col < maxCol; col++){
                if(board[row][col] + damages[row][col] > 0) answer++;
            }
        }
        
        return answer;
    }
}