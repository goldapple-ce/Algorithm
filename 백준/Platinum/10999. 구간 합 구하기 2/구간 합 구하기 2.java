import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, M, K;
    static Question[] questions;
    static long[] tree, lazy;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        for(Question question : questions){
            int from = question.from;
            int to = question.to;

            if(question.type == 1){
                update(1, N, 1, from, to, question.val);
            }else{
                sb.append(query(1, N, 1, question.from, question.to)).append('\n');
            }
        }
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int size = (int) Math.pow(2, Math.ceil(Math.log(N)/Math.log(2))+1);
        tree = new long[size];
        lazy = new long[size];
        
        questions = new Question[M+K];

        for(int n = 0; n < N; n++){
            update(1, N, 1, n+1,n+1, Long.parseLong(br.readLine()));
        }

        for(int mk = 0; mk < M + K; mk++){
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            long val = type == 1 ?Long.parseLong(st.nextToken()) : 0;
            
            questions[mk] = new Question(type, from, to, val);
        }
    }

    static void propagate(int now, int start, int end){
        if(lazy[now] != 0){
            tree[now] += (end - start + 1) * lazy[now];
            if(start < end){
                lazy[now * 2] += lazy[now];
                lazy[now * 2 +1] += lazy[now];
            }
            lazy[now] = 0;
        }
    }

    static void update(int start, int end, int now, int left, int right, long val){
        // System.out.println(String.format("start : %d, end : %d, now : %d, left : %d, right : %d", start,end,now,left, right));
        propagate(now, start, end);
        
        if(left > end || right < start) return;
        if(left <= start && end <= right){
            lazy[now] += val;
            propagate(now, start, end);
            return;
        }

        int mid = (start + end) / 2;

        update(start, mid, now * 2, left, right, val);
        update(mid + 1, end, now * 2 + 1, left, right, val);
        tree[now] = tree[now * 2] + tree[now * 2 + 1];
    }

    static long query(int start, int end, int now, int left, int right){
        propagate(now, start, end);
        if(left > end || right < start) return 0;
        if(left <= start && end <= right) return tree[now];

        int mid = (start + end) / 2;
        return query(start, mid, now * 2, left, right) + query(mid+1, end, now * 2 + 1, left, right);
    }
    
    static void print() throws Exception {
        System.out.println(sb);
    }

    static class Question {
        int type, from, to;
        long val;

        public Question(int type, int from, int to, long val) {
            this.type = type;
            this.from = from;
            this.to = to;
            this.val = val;
        }
    }
}