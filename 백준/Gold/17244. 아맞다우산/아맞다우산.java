import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int maxRow, maxCol, maxDepth;
    static char[][] map;
    static boolean[][][] visited;
    static Position start, end;
    static Deque<Position> queue;
    static int[][] dirType = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static int answer;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        queue.offer(start);
        visited[start.row][start.col][0] = true;

        while(!queue.isEmpty()){
            Position now = queue.poll();

            if(now.row == end.row && now.col == end.col && Integer.bitCount(now.depth) == maxDepth){
                answer = now.cnt;
                return;
            }

            for(int[] dir : dirType){
                int nRow = now.row + dir[0];
                int nCol = now.col + dir[1];

                if(inRange(nRow, nCol) && map[nRow][nCol] != '#' && !visited[nRow][nCol][now.depth]){
                    if(map[nRow][nCol] == '.'){
                        visited[nRow][nCol][now.depth] = true;
                        queue.offer(new Position(nRow, nCol,now.cnt+1,now.depth));
                    }else{
                        visited[nRow][nCol][now.depth | 1 << (map[nRow][nCol] - '0')] = true;
                        queue.offer(new Position(nRow, nCol, now.cnt+1, now.depth | 1 << (map[nRow][nCol] - '0')));
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
        maxCol = Integer.parseInt(st.nextToken());
        maxRow = Integer.parseInt(st.nextToken());

        map = new char[maxRow][maxCol];
        queue = new ArrayDeque<>();

        for(int row = 0; row < maxRow; row++){
            String strRow = br.readLine();
            for(int col = 0; col < maxCol; col++){
                map[row][col] = strRow.charAt(col);
                if(map[row][col] == 'S'){
                    start = new Position(row, col);
                    map[row][col] = '.';
                }else if(map[row][col] == 'E'){
                    end = new Position(row, col);
                    map[row][col] = '.';
                }else if(map[row][col] == 'X'){
                    map[row][col] = (char) ('0' + maxDepth++);
                }
            }
        }

        visited = new boolean[maxRow][maxCol][1 << maxDepth];
    }
    
    static void print() throws Exception {
        System.out.println(answer);
    }

    static class Position {
        int row, col;
        int cnt, depth;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
        
        public Position(int row, int col, int cnt, int depth) {
            this.row = row;
            this.col = col;
            this.cnt = cnt;
            this.depth = depth;
        }

        @Override
        public String toString() {
            return "Position [row=" + row + ", col=" + col + ", cnt=" + cnt + ", depth=" + depth + "]";
        }
        
    }
}