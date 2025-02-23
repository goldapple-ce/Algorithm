import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int[] sum;
    static int N, K;
    static int MIN, MAX;
    static Line[] lines;
    static int[] answer = new int[2];

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        for(Line line : lines){
            sum[line.start+1]++;
            sum[line.end+1]--;
        }

        int cnt = 0;

        for(int i = 1; i <= MAX; i++){
            cnt += sum[i];
            sum[i] = sum[i-1] + cnt;
        }
        twoPoint();
    }

    static void twoPoint(){
        int left = 0, right = 1;

        while(right < MAX){
            int value = sum[right] - sum[left];
            if(value == K){
                answer[0] = left;
                answer[1] = right;
                return;
            }else if(value > K){
                left++;
            }else{
                right++;
            }
        }
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        lines = new Line[N];

        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            lines[n] = new Line(a, b);

            MIN = Math.min(MIN, a);
            MAX = Math.max(MAX, b);
        }
        MAX += 1;

        sum = new int[MAX+1];
    }
    
    static void print() throws Exception {
        System.out.println(answer[0] + " "+ answer[1]);
    }

    static class Line {
        int start, end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
        
    }
}