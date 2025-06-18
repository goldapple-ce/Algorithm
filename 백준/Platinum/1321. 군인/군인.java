import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, M;
    static int SIZE;
    static int[] segment;
    static int[][] queries;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        for(int[] query : queries){
            int oper = query[0], target = query[1];
            if(oper == 1) update(1, N, 1, target, query[2]);
            else sb.append(query(1, N, 1, target)).append('\n');
        }
    }

    static int query(int start, int end, int now, int target){
        // System.out.println(String.format("start : %d, end : %d, now : %d, target : %d", start, end,now,target));
        if(start == end) return start;

        int mid = (start + end) / 2;
        
        if(target <= segment[now * 2]) return query(start, mid, now * 2, target);
        else return query(mid+1, end, now * 2 + 1, target - segment[now * 2]);
    }

    static void update(int start, int end, int now, int target, int val){
        segment[now] += val;
        if(start == end) return;

        int mid = (start + end) / 2;
        if(target <= mid) update(start, mid, now * 2, target, val);
        else update(mid+1, end, now * 2 + 1, target, val);
    }
    
    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        SIZE = (int) Math.pow(2, Math.ceil(Math.log(N) / Math.log(2)) + 1);
        segment = new int[SIZE];

        st = new StringTokenizer(br.readLine());
        for(int n = 1; n <= N; n++){
            int val = Integer.parseInt(st.nextToken());
            update(1, N, 1, n, val);
        }

        M = Integer.parseInt(br.readLine());
        queries = new int[M][];

        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int oper = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());

            if(oper == 1){
                int val = Integer.parseInt(st.nextToken());
                queries[m] = new int[]{oper, target, val};
            }else{
                queries[m] = new int[]{oper,target};
            }
        }
    }
    
    static void print() throws Exception {
        System.out.println(sb);
    }
}