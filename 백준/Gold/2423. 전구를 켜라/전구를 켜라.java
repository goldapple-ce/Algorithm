import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int maxRow, maxCol;
    static boolean[][][] map;
    static int[][] dirType = {{-1,1},{1,1},{1,-1},{-1,-1}};
    static int answer = -1;
    static int[][] costs;
    static final int INF = 500_000_000;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        if((maxRow + maxCol) % 2 == 1){
            return;
        }

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(0, 0, 0));
        costs[0][0] = 0;

        while(!queue.isEmpty()){
            Node now = queue.poll();
            // System.out.println(now);

            if(now.row == maxRow && now.col == maxCol){
                answer = now.cost;
                return;
            }

            if(costs[now.row][now.col] < now.cost){
                continue;
            }

            for(int d = 0; d < 4; d++){
                int[] dir = dirType[d];

                int nRow = now.row + dir[0];
                int nCol = now.col + dir[1];
                int nCost = now.cost + (map[now.row][now.col][d] ?0 :1);

                if(inRange(nRow, nCol) && costs[nRow][nCol] > nCost){
                    costs[nRow][nCol] = nCost;
                    queue.offer(new Node(nRow, nCol, nCost));
                }
            }
        }
    }

    static boolean inRange(int row, int col){
        return 0 <= row && row <= maxRow && 0 <= col && col <= maxCol;
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        maxRow = Integer.parseInt(st.nextToken());
        maxCol = Integer.parseInt(st.nextToken());

        map = new boolean[maxRow+1][maxCol+1][4];
        costs = new int[maxRow+1][maxCol+1];

        for(int[] row : costs){
            Arrays.fill(row, INF);
        }

        for(int row = 0; row < maxRow; row++){
            String strRow = br.readLine();
            for(int col = 0; col < maxCol; col++){
                char value = strRow.charAt(col);
                if(value == '/'){
                    map[row+1][col][0] = true;
                    map[row][col+1][2] = true;
                }else{
                    map[row][col][1] = true;
                    map[row+1][col+1][3] = true;
                }
            }
        }

        // for(boolean[][] row : map){
        //     for(boolean[] value : row){
        //         System.out.print(Arrays.toString(value) +" ");
        //     }
        //     System.out.println();
        // }
    }
    
    static void print() throws Exception {
        System.out.println(answer == -1 ?"NO SOLUTION" :answer);
    }

    static class Node implements Comparable<Node>{
        int row, col;
        int cost;

        public Node(int row, int col, int cost) {
            this.row = row;
            this.col = col;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Node [row=" + row + ", col=" + col + ", cost=" + cost + "]";
        }

        @Override
        public int compareTo(Main.Node o) {
            return this.cost - o.cost;
        }
    }
}