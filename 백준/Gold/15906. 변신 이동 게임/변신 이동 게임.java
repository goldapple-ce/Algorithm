import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, T, R, C;
    static char[][] map;
    static int[][] dirType = {{0,1},{1,0},{0,-1},{-1,0}}; // 동, 남, 서, 북
    static int[][][] dists;
    static int answer;

    static final int INF = 1_000_000_000;
    
    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        PriorityQueue<Position> queue = new PriorityQueue<>();
        queue.offer(new Position(0, 0, 0, 0));
        dists[0][0][0] = 0;

        while(!queue.isEmpty()){
            Position now = queue.poll();
            // System.out.println(now);

            if(now.row == R && now.col == C){
                answer = now.dist;
                return;
            }
            if(dists[now.row][now.col][now.trans] < now.dist) continue;
            
            for(int[] dir : dirType){
                int nRow = now.row + dir[0];
                int nCol = now.col + dir[1];
                int nDist = dists[now.row][now.col][now.trans] + 1;

                if(inRange(nRow, nCol) && dists[nRow][nCol][0] > nDist){
                    dists[nRow][nCol][0] = nDist;
                    queue.offer(new Position(nRow, nCol, nDist, 0));
                }
            }

            for(int[] dir : dirType){
                int nRow = now.row + dir[0];
                int nCol = now.col + dir[1];
                int nDist = now.dist + (now.trans == 1 ?0 : T) + 1;

                while(inRange(nRow, nCol)){
                    if(map[nRow][nCol] == '#') break;

                    nRow += dir[0];
                    nCol += dir[1];
                }

                if(inRange(nRow, nCol) && dists[nRow][nCol][1] > nDist){
                    dists[nRow][nCol][1] = nDist;
                    queue.offer(new Position(nRow, nCol, nDist, 1));
                }
            }
        }
    }

    static boolean inRange(int row, int col){
        return 0 <= row && row < N && 0 <= col && col < N;
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken())-1;
        C = Integer.parseInt(st.nextToken())-1;

        map = new char[N][N];
        dists = new int[N][N][2];

        for(int row = 0; row < N; row++){
            for(int col = 0; col < N; col++){
                for(int t = 0; t < 2; t++){
                    dists[row][col][t] = INF;
                }
            }
        }

        for(int row = 0; row < N; row++){
            map[row] = br.readLine().toCharArray();
        }
    }
    
    static void print() throws Exception {
        System.out.println(answer);
    }

    static class Position implements Comparable<Position>{
        int row, col;
        int dist, trans;

        public Position(int row, int col, int dist, int trans){
            this.row = row;
            this.col = col;
            this.dist = dist;
            this.trans = trans;
        }

        @Override
        public String toString() {
            return "Position [row=" + row + ", col=" + col + ", dist=" + dist + ", trans=" + trans + "]";
        }

        @Override
        public int compareTo(Main.Position o) {
            return this.dist - o.dist;
        }
    }
}