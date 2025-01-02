import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int[][] dirType = {{0,1},{0,-1},{1,0},{-1,0}};
    static int maxRow, maxCol;
    static Position[] A, B;
    static boolean[][] visited;
    static Position[][] path;
    static int answer1, answer2;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        visited = new boolean[maxRow][maxCol];
        path = new Position[maxRow][maxCol];
        visited[B[0].row][B[0].col] = true;
        visited[B[1].row][B[1].col] = true;
        int ACnt = bfs(A[0], A[1]);

        visited = new boolean[maxRow][maxCol];
        recordPath(A[0], A[1]);
        int BCnt = bfs(B[0], B[1]);

        if(BCnt == 0){
            answer1 = Integer.MAX_VALUE;
        }else{
            answer1 = ACnt + BCnt;
        }

        visited = new boolean[maxRow][maxCol];
        path = new Position[maxRow][maxCol];
        visited[A[0].row][A[0].col] = true;
        visited[A[1].row][A[1].col] = true;
        BCnt = bfs(B[0], B[1]);

        visited = new boolean[maxRow][maxCol];
        recordPath(B[0], B[1]);
        ACnt = bfs(A[0], A[1]);

        if(ACnt == 0){
            answer2 = Integer.MAX_VALUE;
        }else{
            answer2 = ACnt + BCnt;
        }
    }
        

    static int bfs(Position start, Position end){
        Deque<Position> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start.row][start.col] = true;

        while(!queue.isEmpty()){
            Position now = queue.poll();

            if(now.row == end.row && now.col == end.col){
                return now.cnt;
            }

            for(int[] dir : dirType){
                int nRow = now.row + dir[0];
                int nCol = now.col + dir[1];

                if(inRange(nRow, nCol) && !visited[nRow][nCol]){
                    visited[nRow][nCol] = true;
                    queue.offer(new Position(nRow, nCol, now.cnt+1));
                    path[nRow][nCol] = now;
                }
            }
        }

        return 0;
    }

    static void recordPath(Position start, Position end){
        Position now = end;

        while(true){
            visited[now.row][now.col] = true;
            if(now.row == start.row && now.col == start.col){
                return;
            }
            now = path[now.row][now.col];
        }
    }

    static boolean inRange(int row, int col){
        return 0 <= row && row < maxRow && 0 <= col && col < maxCol;
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        maxCol = Integer.parseInt(st.nextToken())+1;
        maxRow = Integer.parseInt(st.nextToken())+1;

        A = new Position[2];
        B = new Position[2];

        for(int i = 0; i < 2; i++){
            st = new StringTokenizer(br.readLine());
            int col = Integer.parseInt(st.nextToken());
            int row = Integer.parseInt(st.nextToken());
            A[i] = new Position(row, col, 0);
        }

        for(int i = 0; i < 2; i++){
            st = new StringTokenizer(br.readLine());
            int col = Integer.parseInt(st.nextToken());
            int row = Integer.parseInt(st.nextToken());
            B[i] = new Position(row, col, 0);
        }
    }
    
    static void print() throws Exception {
        if(answer1 == Integer.MAX_VALUE && answer2 == Integer.MAX_VALUE){
            System.out.println("IMPOSSIBLE");
        }else{
            System.out.println(Math.min(answer1, answer2));
        }
    }

    static class Position {
        int row, col;
        int cnt;

        public Position(int row, int col, int cnt) {
            this.row = row;
            this.col = col;
            this.cnt = cnt;
        }

        @Override
        public String toString() {
            return "[" + row + ", " + col + "]";
        }

    }
}