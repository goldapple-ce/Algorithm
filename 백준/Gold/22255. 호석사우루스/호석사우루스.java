import java.util.*;
import java.io.*;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int maxRow, maxCol;
    static Position S, E;
    static int[][] map;
    static int[][][] costs;
    static int answer = -1;

    static int[][][] dirType = {{{0,1},{0,-1},{1,0},{-1,0}},{{1,0},{-1,0}},{{0,-1},{0,1}}};
    static final int INF = 1_000_000;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        PriorityQueue<Position> queue = new PriorityQueue<>();
        queue.offer(S);

        while(!queue.isEmpty()){
            Position now = queue.poll();

            if(costs[now.cnt%3][now.row][now.col] < now.cost){
                continue;
            }
            // System.out.println(now);
            
            if(now.row == E.row && now.col == E.col){
                answer = now.cost;
                return;
            }

            for(int[] dir : dirType[now.cnt % 3]){
                int nRow = now.row + dir[0];
                int nCol = now.col + dir[1];

                if(inRange(nRow, nCol) && map[nRow][nCol] != -1){
                    int nCost = now.cost + map[nRow][nCol];
                    int nCnt = (now.cnt+1)%3;
                    // System.out.println(String.format("ready nRow :%d, nCol %d", nRow,nCol));

                    if(costs[nCnt][nRow][nCol] > nCost){
                        // System.out.println(String.format("insert nRow :%d, nCol %d", nRow,nCol));
                        costs[nCnt][nRow][nCol] = nCost;
                        queue.offer(new Position(nRow, nCol, nCost, now.cnt+1));
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
        costs = new int[3][maxRow][maxCol];

        st = new StringTokenizer(br.readLine());
        int sRow = Integer.parseInt(st.nextToken())-1;
        int sCol = Integer.parseInt(st.nextToken())-1;
        int eRow = Integer.parseInt(st.nextToken())-1;
        int eCol = Integer.parseInt(st.nextToken())-1;

        S = new Position(sRow, sCol,0,1);
        E = new Position(eRow, eCol,0,1);

        for(int row = 0; row < maxRow; row++){
            st = new StringTokenizer(br.readLine());
            for(int col = 0; col < maxCol; col++){
                map[row][col] = Integer.parseInt(st.nextToken());
                costs[0][row][col] = INF;
                costs[1][row][col] = INF;
                costs[2][row][col] = INF;
            }
        }
    }
    
    static void print() throws Exception {
        System.out.println(answer);
    }

    static class Position implements Comparable<Position>{
        int row, col;
        int cost, cnt;

        public Position(int row, int col, int cost, int cnt) {
            this.row = row;
            this.col = col;
            this.cost = cost;
            this.cnt = cnt;
        }

        @Override
        public String toString() {
            return "Position [row=" + row + ", col=" + col + ", cost=" + cost + ", cnt=" + cnt + "]";
        }

        @Override
        public int compareTo(Main.Position o) {
            return this.cost - o.cost;
        }
    }
}