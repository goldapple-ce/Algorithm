import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int[] prifixSum;
    static int N, K;
    static final int MAX = 1_000_001;
    static int[] answer = new int[2];

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        for(int i = 1; i <= MAX; i++){
            prifixSum[i] += prifixSum[i-1];
        }
        twoPoint();
    }

    static void twoPoint(){
        int left = 0, right = 0;
        int sum = 0;

        while(right < MAX){
            if(sum == K){
                answer[0] = left;
                answer[1] = right;
                return;
            }else if(sum > K){
                sum -= prifixSum[++left];
            }else{
                sum += prifixSum[++right];
            }
        }
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        prifixSum = new int[MAX+1];

        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            prifixSum[a+1]++;
            prifixSum[b+1]--;
        }

    }
    
    static void print() throws Exception {
        System.out.println(answer[0] + " "+ answer[1]);
    }
}