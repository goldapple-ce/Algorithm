import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, K;
    static int[] tempers;

    static long answer;
    static int maxVal;
    static int[] tree;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        for(int k = 0; k < K-1; k++){
            update(0,maxVal,1,tempers[k],1);
        }

        for(int k = K-1; k < N; k++){
            update(0,maxVal,1,tempers[k],1);
            answer += query(0, maxVal, 1, (K+1)/2);
            update(0, maxVal, 1, tempers[k-K+1], -1);
        }
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        tempers = new int[N];

        for(int n = 0; n < N; n++){
            tempers[n] = Integer.parseInt(br.readLine());
            maxVal = Math.max(maxVal, tempers[n]);
        }

        tree = new int[(int) (Math.pow(2,Math.ceil(Math.log(maxVal+1)/Math.log(2))+1))];
    }
    
    static void print() throws Exception {
        System.out.println(answer);
    }

    static int query(int start, int end, int now, int target){
        // System.out.println(String.format("start : %d, end : %d, now : %d", start, end, now));

        if(start == end) return start;

        int mid = (start + end)/2;

        if(target <= tree[now*2]){
            return query(start, mid, now*2, target);
        }else{
            return query(mid+1, end, now*2+1, target-tree[now*2]);
        }
    }

    static void update(int start, int end, int now, int pivot, int val){
        // System.out.println(String.format("start : %d, end : %d, now : %d", start, end, now));
        tree[now] += val;
        if(start == end) return;

        int mid = (start + end) / 2;
        if(mid >= pivot){
            update(start, mid, 2*now, pivot, val);
        }else{
            update(mid+1, end, 2*now+1, pivot, val);
        }
    }
}