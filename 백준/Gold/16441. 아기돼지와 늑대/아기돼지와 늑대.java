import java.util.*;
import java.io.*;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int maxRow, maxCol;
    static char[][] map;
    static boolean[][][] visited;
    static List<Position> wolves = new ArrayList<>();

    static int[][] dirType = {{0,1},{1,0},{0,-1},{-1,0}};   //우, 하, 좌, 상

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        Deque<Position> queue = new ArrayDeque<>(wolves);

        while(!queue.isEmpty()){
            Position now = queue.poll();
            
            for(int d = 0; d < 4; d++){
                int[] dir = dirType[d];

                int nRow = now.row + dir[0];
                int nCol = now.col + dir[1];

                if(inRange(nRow, nCol) && map[nRow][nCol] != '#'){
                    if(map[nRow][nCol] == '+' && !visited[d%2][nRow][nCol]){
                        visited[d%2][nRow][nCol] = true;
                        while(inRange(nRow, nCol) && map[nRow][nCol] == '+'){
                            nRow += dir[0];
                            nCol += dir[1];
                        }

                        if(map[nRow][nCol] == '#'){
                            nRow -= dir[0];
                            nCol -= dir[1];
                        }
                        if(!visited[2][nRow][nCol]){
                            visited[2][nRow][nCol] = true;
                            queue.offer(new Position(nRow, nCol, 0));
                        }
                    }else if(map[nRow][nCol] != '+' && !visited[2][nRow][nCol]){
                        visited[2][nRow][nCol] = true;
                        queue.offer(new Position(nRow, nCol, 0));
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

        map = new char[maxRow][maxCol];
        visited = new boolean[3][maxRow][maxCol];

        for(int row = 0; row < maxRow; row++){
            String strRow = br.readLine();
            for(int col = 0; col < maxCol; col++){
                map[row][col] = strRow.charAt(col);

                if(map[row][col] == 'W'){
                    wolves.add(new Position(row, col,0));
                    visited[2][row][col] = true;
                }
            }
        }
    }
    
    static void print() throws Exception {
        for(int row = 0; row < maxRow; row++){
            for(int col = 0; col < maxCol; col++){
                if(map[row][col] == '.' && !visited[2][row][col]){
                    sb.append('P');
                }else{
                    sb.append(map[row][col]);
                }
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    static class Position {
        int row, col;
        int dir;

        public Position(int row, int col, int dir){
            this.row = row;
            this.col = col;
            this.dir = dir;
        }

        @Override
        public String toString() {
            return "Position [row=" + row + ", col=" + col + ", dir=" + dir + "]";
        }
    }
}