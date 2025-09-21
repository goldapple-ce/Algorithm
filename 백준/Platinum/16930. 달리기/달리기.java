import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int maxRow, maxCol;
    static int K;
    static char[][] map;
    static int[][] visited;
    static Position S, E;

    static int[][] dirType = {{-1,0},{1,0},{0,-1},{0,1}};
    static final int INF = 1_000_000_000;
    static int answer = -1;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        Deque<Position> queue = new ArrayDeque<>();
        queue.offer(new Position(S.row, S.col, 0,5));
        visited[S.row][S.col] = 0;

        while(!queue.isEmpty()){
            Position now = queue.poll();
            // System.out.println(now);
            if(now.row == E.row && now.col == E.col){
                answer = now.cnt;
                return;
            }

            for(int dir = 0; dir < 4; dir++){
                if(now.dir == dir) continue;
                int nCnt = now.cnt + 1;
                for(int k = 1; k <= K; k++){
                    int nRow = now.row + dirType[dir][0] * k, nCol = now.col + dirType[dir][1] * k;
                    if(!inRange(nRow, nCol) || map[nRow][nCol] == '#' || visited[nRow][nCol] < nCnt) break;
                    if(visited[nRow][nCol] == INF){
                        visited[nRow][nCol] = nCnt;
                        queue.offer(new Position(nRow, nCol, nCnt, k == K ?5 : dir));
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
        K = Integer.parseInt(st.nextToken());

        map = new char[maxRow][maxCol];
        visited = new int[maxRow][maxCol];

        for(int row = 0; row < maxRow; row++){
            map[row] = br.readLine().toCharArray();
            Arrays.fill(visited[row], INF);
        }

        st = new StringTokenizer(br.readLine());
        S = new Position(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1);
        E = new Position(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1);

    }
    
    static void print() throws Exception {
        // for(int[] row : visited){
        //     System.out.println(Arrays.toString(row));
        // }
        System.out.println(answer);
    }

    static class Position implements Comparable<Position>{
        int row, col;
        int cnt, dir;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public Position(int row, int col, int cnt, int dir) {
            this.row = row;
            this.col = col;
            this.cnt = cnt;
            this.dir = dir;
        }

        

        @Override
        public String toString() {
            return "Position [row=" + row + ", col=" + col + ", cnt=" + cnt + ", dir=" + dir + "]";
        }

        @Override
        public int compareTo(Main.Position o) {
            return this.cnt - o.cnt;
        }

    }
}