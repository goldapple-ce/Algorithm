import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N;
    static int[][] abilities;
    static int[] segment;
    static int SIZE;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        compactValue();
        for(int n = 1; n <= N; n++){
            sb.append(n -query(1, N, 1, 1, abilities[n][1])).append('\n');
            update(1, N, 1, abilities[n][1]);
        }
    }

    static void compactValue(){
        Arrays.sort(abilities, (o1,o2) -> {
            return o1[1] - o2[1];
        });

        for(int n = 1; n <= N; n++){
            abilities[n][1] = n;
        }

        Arrays.sort(abilities, (o1,o2)-> {
            return o1[0] - o2[0];
        });
    }

    static int query(int start, int end, int now, int left, int right){
        if(left > end || right < start) return 0;
        if(left <= start && end <= right) return segment[now];

        int mid = (start + end) / 2;
        return query(start, mid, now * 2, left, right) + query(mid + 1, end, now * 2 + 1, left, right);
    }

    static void update(int start, int end, int now, int target){
        segment[now]++;
        if(start == end) return;

        int mid = (start + end) / 2;
        if(target <= mid)update(start, mid, now * 2, target);
        else update(mid + 1, end, now * 2 + 1, target);
    }
    
    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        SIZE = (int) Math.pow(2, Math.ceil(Math.log(N)/Math.log(2))+1);

        abilities = new int[N+1][2];
        segment = new int[SIZE];

        for(int n = 1; n <= N; n++){
            abilities[n][0] = n;
            abilities[n][1] = Integer.parseInt(br.readLine());
        }
    }
    
    static void print() throws Exception {
        System.out.println(sb);
    }
}