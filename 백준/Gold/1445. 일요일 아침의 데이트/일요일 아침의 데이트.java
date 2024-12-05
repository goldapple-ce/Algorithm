import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int maxRow, maxCol;
    static boolean[][] visited;
    static char[][] map;
    static int[][] nMap, gMap;
    static int[][] dirType = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static PriorityQueue<Position> queue;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        while(!queue.isEmpty()){
            Position now = queue.poll();

            if(map[now.row][now.col] == 'F'){
                sb.append(now.gCnt).append(' ').append(now.nCnt);
                return;
            }

            for(int[] dir : dirType){
                int nRow = now.row + dir[0];
                int nCol = now.col + dir[1];

                if(inRange(nRow, nCol) && !visited[nRow][nCol]){
                    visited[nRow][nCol] = true;
                    int nGcnt = now.gCnt + gMap[nRow][nCol];
                    int nNcnt = now.nCnt + nMap[nRow][nCol];

                    queue.offer(new Position(nRow, nCol, nGcnt, nNcnt));
                }
            }
        }
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        maxRow = Integer.parseInt(st.nextToken());
        maxCol = Integer.parseInt(st.nextToken());

        map = new char[maxRow][maxCol];
        nMap = new int[maxRow][maxCol];
        gMap = new int[maxRow][maxCol];
        visited = new boolean[maxRow][maxCol];

        Deque<Position> gQueue = new ArrayDeque<>();
        queue = new PriorityQueue<>();

        for(int row = 0; row < maxRow; row++){
            String strRow = br.readLine();
            for(int col = 0; col < maxCol; col++){
                map[row][col] = strRow.charAt(col);
                
                if(map[row][col] == 'S'){
                    queue.offer(new Position(row, col,0,0));
                    visited[row][col] = true;
                }else if(map[row][col] == 'g'){
                    gQueue.add(new Position(row, col));
                }
            }
        }

        while(!gQueue.isEmpty()){
            Position g = gQueue.poll();

            gMap[g.row][g.col] = 1;

            for(int[] dir : dirType){
                int nRow = g.row + dir[0];
                int nCol = g.col + dir[1];

                if(inRange(nRow, nCol) && map[nRow][nCol] == '.'){
                    nMap[nRow][nCol] = 1;
                }
            }
        }

    }

    static boolean inRange(int row, int col){
        return 0 <= row && row < maxRow && 0 <= col && col < maxCol;
    }
    
    static void print() throws Exception {
        System.out.println(sb);
    }

    static class Position implements Comparable<Position>{
        int row, col;
        int nCnt, gCnt;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public Position(int row, int col, int gCnt, int nCnt) {
            this.row = row;
            this.col = col;
            this.gCnt = gCnt;
            this.nCnt = nCnt;
        }

        @Override
        public int compareTo(Position o) {
            if(this.gCnt == o.gCnt){
                return this.nCnt - o.nCnt;
            }
            return this.gCnt - o.gCnt;
        }
        
    }
}