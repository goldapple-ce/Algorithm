import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int maxRow, maxCol, T, D;
    static int[][] map;
    static int[][][][] dist;
    static int[][] dirType = {{0,1},{0,-1},{1,0},{-1,0}};
    static final int INF = 1_000_000_000;
    static int answer;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        for(int row = 0; row < maxRow; row++){
            for(int col = 0; col < maxCol; col++){
                dijkstra(row, col);
            }
        }

        for(int row = 0; row < maxRow; row++){
            for(int col = 0; col < maxCol; col++){
                if(dist[0][0][row][col] + dist[row][col][0][0] <= D){
                    answer = Math.max(answer, map[row][col]);
                }
            }
        }
    }

    static void dijkstra(int sRow, int sCol){
        PriorityQueue<Position> queue = new PriorityQueue<>();
        queue.offer(new Position(sRow, sCol, 0));
        dist[sRow][sCol][sRow][sCol] = 0;

        while(!queue.isEmpty()){
            Position now = queue.poll();

            for(int[] dir : dirType){
                int nRow = now.row + dir[0];
                int nCol = now.col + dir[1];

                if(inRange(nRow, nCol) && Math.abs(map[now.row][now.col] - map[nRow][nCol]) <= T){
                    int nTime = now.time;

                    if(map[now.row][now.col] < map[nRow][nCol])
                        nTime += Math.pow(map[nRow][nCol] - map[now.row][now.col],2);
                    else
                        nTime += 1;
    
                    if(nTime < dist[sRow][sCol][nRow][nCol] && nTime <= D){
                        dist[sRow][sCol][nRow][nCol] = nTime;
                        queue.offer(new Position(nRow, nCol, nTime));
                    }
                }

            }
        }
    }

    static boolean inRange(int row, int col){
        return 0 <= row && row < maxRow && 0 <= col && col < maxCol;
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());

        maxRow = Integer.parseInt(st.nextToken());
        maxCol = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[maxRow][maxCol];
        dist = new int[maxRow][maxCol][maxRow][maxCol];

        for(int sRow = 0; sRow < maxRow; sRow++){
            for(int sCol = 0; sCol < maxCol; sCol++){
                for(int eRow = 0; eRow < maxRow; eRow++){
                    for(int eCol = 0; eCol < maxCol; eCol++){
                        dist[sRow][sCol][eRow][eCol] = INF;
                    }
                }
            }
        }

        for(int row = 0; row < maxRow; row++){
            String strRow = br.readLine();
            for(int col = 0; col < maxCol; col++){
                char val = strRow.charAt(col);
                if('A' <= val && val <= 'Z')
                    map[row][col] = val - 65;
                else
                    map[row][col] = val - 71;
            }
        }

    }
    
    static void print() throws Exception {
        System.out.println(answer);
    }

    static class Position implements Comparable<Position>{
        int row, col;
        int time;

        public Position(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }

        @Override
        public int compareTo(Position o) {
            return this.time - o.time;
        }
    }
}