import java.util.*;
import java.io.*;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int maxRow, maxCol;
    static int[][] map, answer;
    static Position[][] dp;
    static int[][] dirType = {{1,1},{1,0},{1,-1},{0,1},{0,-1},{-1,1},{-1,0},{-1,-1}};

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        for(int row = 0; row < maxRow; row++){
            for(int col = 0; col < maxCol; col++){
                Position now = dfs(row, col);
                answer[now.row][now.col]++;
            }
        }
    }

    static Position dfs(int row, int col){
        if(dp[row][col] != null) return dp[row][col];

        int mRow = row, mCol = col;

        for(int[] dir : dirType){
            int nRow = row+ dir[0], nCol = col + dir[1];

            if(inRange(nRow, nCol) && map[mRow][mCol] > map[nRow][nCol]){
                mRow = nRow;
                mCol = nCol;
            }
        }

        if(map[mRow][mCol] == map[row][col]){
            return dp[row][col] = new Position(mRow, mCol);
        }

        return dp[row][col] = dfs(mRow, mCol);
    }

    static boolean inRange(int row, int col){
        return 0 <= row && row < maxRow && 0 <= col && col < maxCol;
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        maxRow = Integer.parseInt(st.nextToken());
        maxCol = Integer.parseInt(st.nextToken());

        map = new int[maxRow][maxCol];
        answer = new int[maxRow][maxCol];
        dp = new Position[maxRow][maxCol];

        for(int row = 0; row < maxRow; row++){
            st = new StringTokenizer(br.readLine());
            for(int col = 0; col < maxCol; col++){
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }
    }
    
    static void print() throws Exception {
        for(int row = 0; row < maxRow; row++){
            for(int col = 0; col < maxCol; col++){
                sb.append(answer[row][col]).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    static class Position {
        int row, col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "Position [row=" + row + ", col=" + col + "]";
        }
    }
}