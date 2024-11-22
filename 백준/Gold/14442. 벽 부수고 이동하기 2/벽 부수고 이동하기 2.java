import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int maxRow, maxCol, K;
    static boolean[][] map;
    static boolean[][][] visited;
    static Deque<Position> queue = new ArrayDeque<>();
    static int[][] dirType = {{0,1},{0,-1},{1,0},{-1,0}};

    static int answer;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        queue.offer(new Position(1, 1, 1,0));
        visited[1][1][0] = true;
        while(!queue.isEmpty()){
            Position now = queue.poll();

            if(now.row == maxRow && now.col == maxCol){
                answer = now.cnt;
                return;
            }

            for(int[] dir : dirType){
                int nRow = now.row + dir[0];
                int nCol = now.col + dir[1];

                if(inRange(nRow, nCol) ){
                    if(!map[nRow][nCol] && !visited[nRow][nCol][now.k]){
                        visited[nRow][nCol][now.k] = true;
                        queue.offer(new Position(nRow, nCol, now.cnt+1,now.k));
                    }else if(now.k < K && !visited[nRow][nCol][now.k+1]){
                        visited[nRow][nCol][now.k+1] = true;
                        queue.offer(new Position(nRow, nCol, now.cnt+1, now.k+1));
                    }
                }
            }
        }
    }

    static boolean inRange(int row, int col){
        return 0 < row && row <= maxRow && 0 < col && col <= maxCol;
    }    
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        maxRow = Integer.parseInt(st.nextToken());
        maxCol = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new boolean[maxRow+1][maxCol+1];
        visited = new boolean[maxRow+1][maxCol+1][11];

        for(int row = 1; row <= maxRow; row++){
            String strRow = br.readLine();
            for(int col = 0; col < maxCol; col++){
                map[row][col+1] = strRow.charAt(col) != '0';
            }
        }
    }
    
    static void print() throws Exception {
        System.out.println(answer != 0 ? answer : -1);
    }

    static class Position {
        int row, col, cnt;
        int k;

        public Position(int row, int col, int cnt, int k) {
            this.row = row;
            this.col = col;
            this.cnt = cnt;
            this.k = k;
        }

        @Override
        public String toString() {
            return "Position [row=" + row + ", col=" + col + ", cnt=" + cnt + ", k=" + k + "]";
        }
        
    }
}