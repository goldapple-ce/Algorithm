import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int W, B;
    static String[] words;
    static char[][][] maps;
    static char[][] map;
    static boolean[][] visited;

    static int[] scoreType = {0, 0, 0, 1, 1, 2, 3, 5, 11};
    static int[][] dirType = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        for(int b = 0; b < B; b++){
            map = maps[b];
            int score, totalCnt;
            String answer = "";
            score = totalCnt = 0;

            for(int wordIdx = 0; wordIdx < W; wordIdx++){
                visited = new boolean[4][4];
                String word = words[wordIdx];

                if(subRun(word)){
                    totalCnt++;
                    score += scoreType[word.length()];
                    answer = word;
                }
            }

            sb.append(score + " " + answer + " "+ totalCnt).append('\n');
        }
    }

    static boolean subRun(String word){
        for(int row = 0; row < 4; row++){
            for(int col = 0; col < 4; col++){
                if(word.charAt(0) == map[row][col]){
                    visited[row][col] = true;
                    if(dfs(word, new Position(row, col), 0)){
                        return true;
                    };
                    visited[row][col] = false;
                }
            }
        }

        return false;
    }

    static boolean dfs(String word, Position now, int depth){
        if(word.length()-1 == depth){
            return true;
        }

        for(int[] dir : dirType){
            int nRow = now.row + dir[0];
            int nCol = now.col + dir[1];

            if(inRange(nRow, nCol) && word.charAt(depth + 1) == map[nRow][nCol] && !visited[nRow][nCol]){
                visited[nRow][nCol] = true;
                if(dfs(word, new Position(nRow, nCol), depth +1)) return true;
                visited[nRow][nCol] = false;
            }
        }

        return false;
    }

    static boolean inRange(int row, int col){
        return 0 <= row && row < 4 && 0 <= col && col < 4;
    }
    
    static void input() throws Exception {
        W = Integer.parseInt(br.readLine());

        words = new String[W];
        for(int w = 0; w < W; w++){
            words[w] = br.readLine();
        }
        br.readLine();

        B = Integer.parseInt(br.readLine());
        maps = new char[B][4][4];

        for(int b = 0; b < B; b++){
            for(int i = 0; i < 4; i++){
                maps[b][i] = br.readLine().toCharArray();
            }
            if(b != B-1) br.readLine();
        }

        Arrays.sort(words, (o1,o2) -> {
            if(o1.length() == o2.length()){
                return o2.compareTo(o1);
            }
            return o1.length() - o2.length();
        });
        
        // System.out.println(Arrays.toString(words));
    }
    
    static void print() throws Exception {
        System.out.println(sb);
    }

    static class Position {
        int row, col;

        @Override
        public String toString() {
            return "Position [row=" + row + ", col=" + col + "]";
        }

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}