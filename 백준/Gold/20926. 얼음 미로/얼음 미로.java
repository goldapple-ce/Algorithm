import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int maxRow, maxCol;
    static char[][] map;
    static Position start;
    static int[][] dirType = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static int[][] mapOfCnt;
    static int answer = -1;

    static final int INF = 500_000_000;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        PriorityQueue<Position> queue = new PriorityQueue<>();
        queue.offer(start);
        while(!queue.isEmpty()){
            Position now = queue.poll();
            // System.out.println(now);

            if(map[now.row][now.col] == 'E'){
                answer = now.cnt;
                return;
            }

            for(int[] dir: dirType){
                Position next = new Position(now.row, now.col, now.cnt);
                if(move(next, dir) && mapOfCnt[next.row][next.col] > next.cnt){
                    mapOfCnt[next.row][next.col] = next.cnt;
                    queue.offer(next);
                }
            }
        }
    }

    static boolean move(Position pos, int[] dir){
        int row = pos.row, col = pos.col;
        int cnt = pos.cnt;

        while(true){
            int nRow = row + dir[0];
            int nCol = col + dir[1];

            if(!inRange(nRow, nCol) || map[nRow][nCol] == 'H'|| map[nRow][nCol] == 'T'){
                return false;
            }

            if(map[nRow][nCol] == 'R'){
                break;
            }

            row = nRow;
            col = nCol;

            if(map[row][col] == 'E'){
                break;
            }
            cnt += map[row][col] - '0';
        }

        pos.row = row;
        pos.col = col;
        pos.cnt = cnt;
        
        return true;
    }

    static boolean inRange(int row, int col){
        return 0 <= row && row < maxRow && 0 <= col && col < maxCol;
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        maxCol = Integer.parseInt(st.nextToken());
        maxRow = Integer.parseInt(st.nextToken());

        map = new char[maxRow][maxCol];
        mapOfCnt = new int[maxRow][maxCol];

        for(int row = 0; row < maxRow; row++){
            Arrays.fill(mapOfCnt[row], INF);
        }

        for(int row = 0; row < maxRow; row++){
            String strRow = br.readLine();
            for(int col = 0; col < maxCol; col++){
                map[row][col] = strRow.charAt(col);
                if(map[row][col] == 'T'){
                    start = new Position(row, col, 0);
                }
            }
        }
    }
    
    static void print() throws Exception {
        // for(int[] row : mapOfCnt){
        //     System.out.println(Arrays.toString(row));
        // }
        System.out.println(answer);
    }

    static class Position implements Comparable<Position>{
        int row, col;
        int cnt;

        public Position(int row, int col, int cnt){
            this.row = row;
            this.col = col;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Main.Position o) {
            return this.cnt - o.cnt;
        }

        @Override
        public String toString() {
            return "Position [row=" + row + ", col=" + col + ", cnt=" + cnt + "]";
        }
    }
}