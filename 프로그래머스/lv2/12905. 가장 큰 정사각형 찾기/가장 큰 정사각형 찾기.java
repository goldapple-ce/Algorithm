class Solution
{
    public int solution(int [][]board)
    {
        int answer = 0;
        int maxRow=board.length, maxCol = board[0].length;
        
        for(int row = 0; row < maxRow; row++){
            for(int col = 0; col < maxCol; col++){
                int up = (col > 0) ? board[row][col - 1] : 0;
                int left = (row > 0) ? board[row - 1][col] : 0;
                int diagonal = (row > 0 && col > 0) ? board[row - 1][col - 1] : 0;
                if(board[row][col]==1){
                    board[row][col] = Math.min(up,Math.min(left,diagonal))+1;
                    answer = Math.max(answer,board[row][col]);
                }
            }
        }

        return answer*answer;
    }
}