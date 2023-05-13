import java.util.HashMap;
import java.util.HashSet;

class Solution {
    public int solution(String dirs) {
        HashMap<Character,Position> dirType = new HashMap<>();
        dirType.put('U',new Position(-1,0));
        dirType.put('D',new Position(1,0));
        dirType.put('L',new Position(0,-1));
        dirType.put('R',new Position(0,1));
        
        Position now = new Position(0,0);
        HashSet<String> channel = new HashSet<>();
        
        for(int i = 0; i < dirs.length(); i++){
            Position pos = dirType.get(dirs.charAt(i));
            int nRow = now.row + pos.row;
            int nCol = now.col + pos.col;
            if(-5<= nRow && nRow <= 5
              && -5<= nCol && nCol <= 5){
                channel.add(now.row + "," + now.col + "," + nRow + "," + nCol);
                channel.add(nRow + "," + nCol + "," + now.row + "," + now.col);
                now.row = nRow;
                now.col = nCol;
                
            }
        }
        
        return channel.size()/2;
    }
}

class Position{
    int row,col;
    
    public Position(int row, int col){
        this.row = row;
        this.col = col;
    }
}