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
    static int[] answer = new int[2];

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        while(!queue.isEmpty()){
            Position now = queue.poll();

            if(map[now.row][now.col] == 'F'){
                answer[0] = now.gCnt;
                answer[1] = now.nCnt;
                return;
            }

            for(int[] dir : dirType){
                int nRow = now.row + dir[0];
                int nCol = now.col + dir[1];

                if(inRange(nRow, nCol) && !visited[nRow][nCol]){
                    visited[nRow][nCol] = true;
                    queue.offer(new Position(nRow, nCol, now.gCnt + gMap[nRow][nCol], now.nCnt + nMap[nRow][nCol]));
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

        // for(char[] row : map){
        //     System.out.println(Arrays.toString(row));
        // }System.out.println();

        // for(int[] row : nMap){
        //     System.out.println(Arrays.toString(row));
        // }System.out.println();

        // for(int[] row : gMap){
        //     System.out.println(Arrays.toString(row));
        // }System.out.println();

    }

    static boolean inRange(int row, int col){
        return 0 <= row && row < maxRow && 0 <= col && col < maxCol;
    }
    
    static void print() throws Exception {
        System.out.println(answer[0] +" "+ answer[1]);
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