import java.util.*;
import java.io.*;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int maxRow, maxCol;
    static int K;
    static int[][] map;
    static boolean[][] visited;
    static int[][] dirType = {{0,1},{0,-1},{1,0},{-1,0}};

    static int answer;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        int min = 0, max = 1000001;

        while(min <= max){
            int mid = (min + max) / 2;
            int k = 0;
            visited = new boolean[maxRow][maxCol];

            for(int row = 0; row < maxRow; row++){
                if(!visited[row][0] && map[row][0] <= mid){
                    k += bfs(row, 0, mid);
                }
                if(!visited[row][maxCol - 1] && map[row][maxCol - 1] <= mid){
                    k += bfs(row, maxCol-1, mid);
                }
            }
            for(int col = 0; col < maxCol; col++){
                if(!visited[0][col] && map[0][col] <= mid){
                    k += bfs(0, col, mid);
                }
            }
            // System.out.println(String.format("d : %d, k : %d", mid, k));

            if(k >= K){
                answer = mid;
                max = mid - 1;
            }else{
                min = mid + 1;
            }
        }
    }

    static int bfs(int sRow, int sCol, int d){
        Deque<Position> queue = new ArrayDeque<>();
        int cnt = 1;
        queue.offer(new Position(sRow, sCol));
        visited[sRow][sCol] = true;

        while(!queue.isEmpty()){
            Position now = queue.poll();

            for(int[] dir : dirType){
                int nRow = now.row + dir[0];
                int nCol = now.col + dir[1];

                if(inRange(nRow, nCol) && map[nRow][nCol] <= d && !visited[nRow][nCol]){
                    visited[nRow][nCol] = true;
                    queue.offer(new Position(nRow, nCol));
                    cnt++;
                }
            }
        }

        return cnt;
    }

    static boolean inRange(int row, int col){
        return 0 <= row && row < maxRow && 0 <= col && col < maxCol;
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        maxRow = Integer.parseInt(st.nextToken());
        maxCol = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[maxRow][maxCol];

        for(int row = 0; row < maxRow; row++){
            st = new StringTokenizer(br.readLine());
            for(int col = 0; col < maxCol; col++){
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }
    }
    
    static void print() throws Exception {
        System.out.println(answer);
    }

    static class Position {
        int row, col;

        public Position(int row, int col){
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "Position [row=" + row + ", col=" + col + "]";
        }
        
    }
}