import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int maxRow, maxCol;
    static int[][] map;
    static int[][] dirType = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int answer;
    static int[][][] levels;

    static final int INF = 1_000_000_001;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        dijkstra(new Position(0,0,map[0][0], false));
        answer = Math.min(levels[0][maxRow-1][maxCol-1], levels[1][maxRow-1][maxCol-1]);
    }

    static void dijkstra(Position start){
        PriorityQueue<Position> queue = new PriorityQueue<>();
        queue.offer(start);
        levels[0][start.row][start.col] = map[start.row][start.col];

        while(!queue.isEmpty()){
            Position now = queue.poll();

            // System.out.println(now);

            // if(levels[now.row][now.col] < now.level){
            //     continue;
            // }

            for(int[] dir : dirType){
                int nRow = now.row + dir[0];
                int nCol = now.col + dir[1];

                if(inRange(nRow, nCol)){
                    int nLevel = Math.max(now.level, map[nRow][nCol]);
                    if(levels[now.jumped ?1 :0][nRow][nCol] > nLevel){
                        levels[now.jumped ?1 :0][nRow][nCol] = nLevel;
                        queue.offer(new Position(nRow, nCol, nLevel, now.jumped));
                    }
                }

                if(!now.jumped){
                    nRow += dir[0];
                    nCol += dir[1];

                    if(inRange(nRow, nCol)){
                        int nLevel = Math.max(now.level, map[nRow][nCol]);
                        if(levels[1][nRow][nCol] > nLevel){
                            levels[1][nRow][nCol] = nLevel;
                            queue.offer(new Position(nRow, nCol, nLevel, true));
                        }
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

        map = new int[maxRow][maxCol];
        levels = new int[2][maxRow][maxCol];

        for(int i = 0; i < 2; i++){
            for(int row = 0; row < maxRow; row++){
                Arrays.fill(levels[i][row], INF);
            }
        }

        for(int row = 0; row < maxRow; row++){
            st = new StringTokenizer(br.readLine());
            for(int col = 0; col < maxCol; col++){
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }
    }
    
    static void print() throws Exception {
        // for(int[] row : levels){
        //     System.out.println(Arrays.toString(row));
        // }
        System.out.println(answer);
    }

    static class Position implements Comparable<Position>{
        int row, col;
        int level;
        boolean jumped;

        public Position(int row, int col, int level, boolean jumped) {
            this.row = row;
            this.col = col;
            this.level = level;
            this.jumped = jumped;
        }

        @Override
        public String toString() {
            return "Position [row=" + row + ", col=" + col + ", level=" + level + ", jumped=" + jumped + "]";
        }

        @Override
        public int compareTo(Main.Position o) {
            return this.level - o.level;
        }
        
    }
}