import java.util.*;
import java.io.*;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, X;
    static int[] needs;
    static int answer = 1;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        int left = 1, right = N;

        while(left <= right){
            int mid = (left+right)/2;

            if(decentralize(mid) <= X){
                answer = mid;
                right = mid-1;
            }else{
                left = mid+1;
            }
        }
    }

    static int decentralize(int cnt){
        int maxTime = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>(cnt);
        for(int c = 0; c < cnt; c++){
            queue.offer(0);
        }

        for(int need : needs){
            int n = queue.poll() + need;
            maxTime = Math.max(maxTime, n);
            queue.offer(n);
        }

        return maxTime;
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        needs = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int n = 0; n < N; n++){
            needs[n] = Integer.parseInt(st.nextToken());
        }
    }
    
    static void print() throws Exception {
        System.out.println(answer);
    }
}