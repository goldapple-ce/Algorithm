import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int D, N, M;
    static int[] stones;
    static final int INF = 1_000_000_001;
    static int answer;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        int left = 0, right = D;

        while(left <= right){
            int mid = (left+right)/2;
            int point = 0, count = 0;

            for(int n = 1; n <= N; n++){
                if(mid <= stones[n] - stones[point]){
                    point = n;
                }else{
                    count++;
                }
            }

            if(count > M){
                right = mid - 1;
            }else{
                answer = mid;
                left = mid + 1;
            }
        }
    }

    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        D = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        stones = new int[N+1];

        for(int n = 1; n <= N; n++){
            stones[n] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(stones);
    }
    
    static void print() throws Exception {
        System.out.println(answer);
    }
}