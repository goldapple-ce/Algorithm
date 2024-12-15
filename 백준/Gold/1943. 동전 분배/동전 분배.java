import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static Money[] moneys;
    static boolean[][] dp;
    static int N;
    static final int INF = 100_001;
    static int totalMoney;

    public static void main(String[] args) throws Exception {
        for(int test = 0; test < 3; test++){
            input();
            run();
        }
        print();
    }
    
    static void run() throws Exception {
        if(totalMoney % 2 == 1){
            sb.append(0).append('\n');
            return;
        }
        
        dp[0][0] = true;
        
        for(int n = 1; n <= N; n++){
            Money money = moneys[n-1];
            for(int i = 0; i <= totalMoney/2; i++){
                if(dp[n-1][i]){
                    for(int c = 0; c <= money.cnt; c++){
                        dp[n][i + money.value * c] = true;
                    }
                }
            }
        }
        sb.append(dp[N][totalMoney / 2] ? 1: 0).append('\n');
    }
    
    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());

        moneys = new Money[N];
        dp = new boolean[N+1][INF];
        totalMoney = 0;

        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            int value = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());

            moneys[n] = new Money(value, cnt);
            totalMoney += value * cnt;
        }

        Arrays.sort(moneys);
    }
    
    static void print() throws Exception {
        System.out.println(sb);
    }

    static class Money implements Comparable<Money>{
        int value, cnt;

        public Money(int value, int cnt) {
            this.value = value;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Main.Money o) {
            return this.value - o.value;
        }
    }
}