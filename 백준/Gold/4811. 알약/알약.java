import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N;
    static long[][] dp;

    public static void main(String[] args) throws Exception {
        while(input()){
            run();
        }
        print();
    }
    
    static void run() throws Exception {
        sb.append(dfs(N,0)).append('\n');
    }

    static long dfs(int w, int h){
        if(w == 0){
            return 1;
        }
        // System.out.println(String.format("w : %d, h : %d", w,h));
        long cnt = 0;

        if(dp[w][h] == 0){
            if(h != 0)
                dp[w][h] += dfs(w,h-1);
            dp[w][h] += dfs(w-1,h+1);
        }
        
        cnt += dp[w][h];

        return cnt;
    }
    
    static boolean input() throws Exception {
        N = Integer.parseInt(br.readLine());
        
        if(N == 0){
            return false;
        }

        dp = new long[N+1][N+1];

        return true;
    }
    
    static void print() throws Exception {
        System.out.println(sb);
    }
}