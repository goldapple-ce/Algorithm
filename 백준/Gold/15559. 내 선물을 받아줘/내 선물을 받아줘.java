import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    // 북, 서, 남, 동 // N, W, S, E
    static int[][] dirType = {{-1,0}, {0,-1}, {1,0}, {0,1}};
    static int maxRow, maxCol;
    static int[][] map;
    static int[][] visited;
    static int answer;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        int idx = 0;
        for(int row = 0; row < maxRow; row++){
            for(int col = 0; col < maxCol; col++){
                if(visited[row][col] == 0){
                    
                    indexing(row, col,++idx);
                    answer++;
                    // System.out.println(answer);
                }
            }
        }
    }

    static int indexing(int row, int col,int idx){
        if(!inRange(row, col)){
            return idx;
        }
        if(visited[row][col] != 0){
            if(visited[row][col] == idx){
                return idx;
            }
            answer--;
            return visited[row][col];
        }
        // System.out.println(String.format("row : %d, col : %d", row,col));

        visited[row][col] = idx;

        return visited[row][col] = indexing(row + dirType[map[row][col]][0], col + dirType[map[row][col]][1],idx);
        // indexing(row + dirType[(map[row][col]+2)%4][0], col + dirType[(map[row][col]+2)%4][1]);
    }

    static boolean inRange(int row, int col){
        return 0 <= row && row < maxRow && 0 <= col && col < maxCol;
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        maxRow = Integer.parseInt(st.nextToken());
        maxCol = Integer.parseInt(st.nextToken());

        map = new int[maxRow][maxCol];
        visited = new int[maxRow][maxCol];

        for(int row = 0; row < maxRow; row++){
            String strRow = br.readLine();
            for(int col = 0; col < maxCol; col++){
                char val = strRow.charAt(col);
                if(val == 'N'){
                    map[row][col] = 0;
                }else if(val == 'W'){
                    map[row][col] = 1;
                }else if(val == 'S'){
                    map[row][col] = 2;
                }else{
                    map[row][col] = 3;
                }
            }
        }

    }
    
    static void print() throws Exception {
        // for(int[] row : visited){
        //     System.out.println(Arrays.toString(row));
        // }
        System.out.println(answer);
    }

    static class Position {
        int row, col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}