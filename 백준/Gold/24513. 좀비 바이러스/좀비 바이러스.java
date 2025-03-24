import java.util.*;
import java.io.*;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int maxRow, maxCol;
    static int[][] map, mapOfCnt;
    static Deque<Virus> queue = new ArrayDeque<>();
    static int[][] dirType = {{0,1},{0,-1},{1,0},{-1,0}};
    static int[] answer = new int[3];

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        while(!queue.isEmpty()){
            Virus now = queue.poll();

            if(map[now.row][now.col] == 3){
                continue;
            }

            for(int[] dir : dirType){
                int nRow = now.row + dir[0];
                int nCol = now.col + dir[1];

                if(inRange(nRow, nCol)){
                    if(map[nRow][nCol] == 0){
                        map[nRow][nCol] = now.type;
                        mapOfCnt[nRow][nCol] = now.cnt + 1;
                        queue.offer(new Virus(nRow, nCol, now.type, now.cnt+1));
                    }else if((map[nRow][nCol] == 1 && now.type == 2 && mapOfCnt[nRow][nCol] == now.cnt+1) 
                    || (map[nRow][nCol] == 2 && now.type == 1 && mapOfCnt[nRow][nCol] == now.cnt +1)){
                        map[nRow][nCol] = 3;
                    }
                }
            }
        }

        for(int row = 0; row < maxRow; row++){
            for(int col = 0; col < maxCol; col++){
                if(map[row][col] > 0){
                    answer[map[row][col]-1]++;
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
        mapOfCnt = new int[maxRow][maxCol];

        for(int row = 0; row < maxRow; row++){
            st = new StringTokenizer(br.readLine());
            for(int col = 0; col < maxCol; col++){
                map[row][col] = Integer.parseInt(st.nextToken());
                if(map[row][col] > 0){
                    queue.offer(new Virus(row, col, map[row][col],0));
                }
            }
        }
    }
    
    static void print() throws Exception {
        System.out.println(answer[0] + " "+ answer[1] + " " +answer[2]);
    }

    static class Virus{
        int row, col;
        int type, cnt;

        public Virus(int row, int col, int type, int cnt) {
            this.row = row;
            this.col = col;
            this.type = type;
            this.cnt = cnt;
        }

        @Override
        public String toString() {
            return "Virus [row=" + row + ", col=" + col + ", type=" + type + ", cnt=" + cnt + "]";
        }

        
    }
}