import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N;
    static int[] nums;
    static int[][] dp;

    static int answer;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        answer = dfs(0,0);
    }

    static int dfs(int type, int idx){
        if(idx < 0 || idx >= N) return Integer.MIN_VALUE;
        if(idx == N-1) return 0;
        if(dp[type][idx] != -1) return dp[type][idx];

        dp[type][idx] = Integer.MAX_VALUE;
        if(type == 0){
            dp[type][idx] = Math.max(dfs(type, idx + nums[idx]), dfs(type + 1, idx - nums[idx])) +1;
        }else if(type == 1){
            dp[type][idx] = Math.max(dfs(type, idx - nums[idx]), dfs(type +1, idx + nums[idx])) +1;
        }else{
            dp[type][idx] = dfs(type, idx + nums[idx])+1;
        }

        return dp[type][idx];
    }
    
    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());

        nums = new int[N];
        dp = new int[3][N];

        for(int type = 0; type < 3; type++){
            Arrays.fill(dp[type], -1);
        }

        st = new StringTokenizer(br.readLine());
        for(int n = 0; n < N; n++){
            nums[n] = Integer.parseInt(st.nextToken());
        }

    }
    
    static void print() throws Exception {
        System.out.println(answer > 0 ? answer : -1);
    }
}