import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int M, N;
    static long[] want;
    static long total, lack;
    static long answer;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        for(int n = 0; n < N; n++){
            long candle = Math.min(want[n], lack / (N - n));
            lack -= candle;
            answer += candle * candle;
        }
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        want = new long[N];

        for(int n = 0; n < N; n++){
            want[n] = Long.parseLong(br.readLine());
            total += want[n];
        }

        lack = total - M;
        Arrays.sort(want);
    }
    
    static void print() throws Exception {
        System.out.println(answer);
    }
}