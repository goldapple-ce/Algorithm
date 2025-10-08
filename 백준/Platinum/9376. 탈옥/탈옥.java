import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int maxRow, maxCol;
    static char[][] map;
    static int[][][] costs;
    static List<Position> S;
    static int answer;

    static final int INF = 500_000_000;
    static final int[][] dirType = {{0,1},{0,-1},{1,0},{-1,0}};

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++){
            input();
            run();
        }
        print();
    }
    
    static void run() throws Exception {
        for(int s = 0; s < 3; s++){
            dijkstra(s);
        }

        for(int row = 1; row < maxRow-1; row++){
            for(int col = 1; col < maxCol - 1; col++){
                int sum = costs[0][row][col] + costs[1][row][col] + costs[2][row][col];
                if(map[row][col] == '#') sum -= 2;
                answer = Math.min(answer, sum);
            }
        }
        sb.append(answer).append('\n');
    }

    static void dijkstra(int s){
        PriorityQueue<Position> queue = new PriorityQueue<>();
        Position start = S.get(s);
        queue.offer(start);

        while(!queue.isEmpty()){
            Position now = queue.poll();
            costs[s][now.row][now.col] = now.cost;

            for(int[] dir : dirType){
                int nRow = now.row + dir[0];
                int nCol = now.col + dir[1];
                if(!inRange(nRow, nCol) || map[nRow][nCol] == '*') continue;

                int nCost = now.cost + (map[nRow][nCol] == '#' ?1 :0);

                if(costs[s][nRow][nCol] > nCost){
                    costs[s][nRow][nCol] = nCost;
                    queue.offer(new Position(nRow, nCol, nCost));
                }
            }
        }

        // for(int[] row : costs[s]){
        //     for(int col = 0; col < maxCol; col++){
        //         System.out.print(row[col] == INF ?"I " : row[col] + " ");
        //     }
        //     System.out.println();
        // }System.out.println();
    }

    static boolean inRange(int row, int col){
        return 0 <= row && row < maxRow && 0 <= col && col < maxCol;
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        maxRow = Integer.parseInt(st.nextToken()) + 2;
        maxCol = Integer.parseInt(st.nextToken()) + 2;

        map = new char[maxRow][maxCol];
        costs = new int[3][maxRow][maxCol];
        answer = INF;

        for(int s = 0; s < 3; s++){
            for(int row = 0; row < maxRow; row++){
                Arrays.fill(costs[s][row], INF);
            }
        }

        S = new ArrayList<>();
        S.add(new Position(0, 0, 0));

        for(int row = 1; row < maxRow-1; row++){
            String strRow = br.readLine();
            for(int col = 1; col < maxCol-1; col++){
                map[row][col] = strRow.charAt(col-1);
                if(map[row][col] == '$'){
                    S.add(new Position(row, col, 0));
                }
            }
        }
    }
    
    static void print() throws Exception {
        System.out.println(sb);
    }

    static class Position implements Comparable<Position>{
        int row, col;
        int cost;

        public Position(int row, int col, int cost) {
            this.row = row;
            this.col = col;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Position [row=" + row + ", col=" + col + ", cost=" + cost + "]";
        }

        @Override
        public int compareTo(Main.Position o) {
            return this.cost - o.cost;
        }
    }
}