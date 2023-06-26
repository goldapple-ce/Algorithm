import java.util.*;

class Solution {
    static final int[][] dirType = {{1,0},{-1,0},{0,1},{0,-1}};
    
    public int solution(int[][] board) {
        for(int i = 0 ; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                if(board[i][j] != 1) board[i][j] = Integer.MAX_VALUE - 1000;
            }
        }
        dfs(new Position(0,0,0,-1),board);
        return board[board.length-1][board.length-1];
    }
    
    public void dfs(Position pos, int[][] board){
        if( pos.cost > board[pos.row][pos.col] + 500) return;
        
        board[pos.row][pos.col] = Math.min(board[pos.row][pos.col], pos.cost);
        
        for(int i = 0; i < 4; i++){
            int[] dir = dirType[i];
            int nRow = pos.row + dir[0], nCol = pos.col + dir[1];
            if(inRange(nRow,nCol, board.length) && board[nRow][nCol] != 1){
                if(pos.dir == -1) dfs(new Position(nRow,nCol,100,i),board);
                else dfs(new Position(nRow,nCol,pos.dir == i ?pos.cost +100 :pos.cost + 600,i),board);
            }
        }
    }
    
    public boolean inRange(int row, int col, int n){
        return 0 <= row && row < n 
            && 0 <= col && col < n;
    }
}

class Position{
    int row, col, cost, dir;
    
    public Position(int row, int col, int cost,int dir){
        this.row = row;
        this. col = col;
        this.cost = cost;
        this.dir = dir;
    }
    
    public String toString(){
        return "{row : "+row+",col : "+col+",cost : "+cost+", dir : "+dir+"}";
    }
}