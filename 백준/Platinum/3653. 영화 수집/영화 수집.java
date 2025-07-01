import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, M;
    static int[] indexes;
    static int[] segment;
    static int[] queries;

    static int TOTAL, SIZE;

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++){
            input();
            run();
        }
        print();
    }

    static int query(int start, int end, int now, int left, int right){
        if(left <= start && end <= right) return segment[now];
        if(left > end || start > right) return 0;

        int mid = (start + end) / 2;
        return query(start, mid, now * 2, left, right) + query(mid + 1, end, now * 2 + 1, left, right);
    }

    static void update(int start , int end, int now, int target, int val){
        segment[now] += val;
        if(start == end) return;

        int mid = (start + end) / 2;
        if(target <= mid) update(start, mid, now * 2, target, val);
        else update(mid + 1, end, now * 2 + 1, target, val);
    }

    static int init(int start, int end, int now){
        // System.out.println(String.format("start : %d, end : %d, now : %d", start, end, now));
        if(start == end){
            return segment[now] = (start <= M) ?0 : 1;
        }

        int mid = (start + end) / 2;
        return segment[now] = init(start, mid, now * 2) + init(mid + 1, end, now * 2 +1);
    }
    
    static void run() throws Exception {
        for(int m = 0; m < M; m++){
            int q = queries[m];
            int target = indexes[q];
            sb.append(query(1, TOTAL, 1, 1, target-1) + " ");
            update(1, TOTAL, 1, M - m, 1);  
            update(1, TOTAL, 1, target, -1);       
            indexes[q] = M - m;
        }
        sb.append('\n');
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        TOTAL = N + M;
        SIZE = (int) Math.pow(2, Math.ceil(Math.log(TOTAL)/Math.log(2))+1);

        indexes = new int[N + 1];
        segment = new int[SIZE];
        queries = new int[M];

        st = new StringTokenizer(br.readLine());
        for(int m = 0; m < M; m++){
            queries[m] = Integer.parseInt(st.nextToken());
        }

        for(int n = 1; n <= N; n++) indexes[n] = n + M;
        init(1, TOTAL, 1);
    }
    
    static void print() throws Exception {
        System.out.println(sb);
    }
}