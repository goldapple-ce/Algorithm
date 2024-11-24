import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, M;
    static int[][] students;
    static int[] selected;
    static final int INF = 1_000_000_001;
    static int answer = INF;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        for(int n = 0; n < N; n++){
            Arrays.sort(students[n]);
            selected[n] = 0;
        }

        while(true){
            int maxValue = 0, minValue = INF;
            int minIdx = 0;
            for(int n = 0; n < N; n++){
                if(maxValue < students[n][selected[n]]){
                    maxValue = students[n][selected[n]];
                }
                if(minValue > students[n][selected[n]]){
                    minValue = students[n][selected[n]];
                    minIdx = n;
                }
            }
            answer = Math.min(answer, maxValue - minValue);
            if(++selected[minIdx] == M) break;
        }
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        students = new int[N][M];
        selected = new int[N];

        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            for(int m = 0; m < M; m++){
                students[n][m] = Integer.parseInt(st.nextToken());
            }
        }
    }
    
    static void print() throws Exception {
        System.out.println(answer);
    }
}