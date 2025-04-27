import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, M, K;
    static int[] cards, nums;
    static int[] used;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        // System.out.println(nums);

        for(int card : cards){
            int idx = binarySearch(card);
            idx = find(idx);
            sb.append(nums[idx]).append('\n');
            union(idx, idx+1);
        }
    }

    static void union(int x, int y){
        x = find(x);
        y = find(y);

        if(x > y){
            used[y] = x;
        }else{
            used[x] = y;
        }
    }

    static int find(int x){
        if(used[x] == x){
            return x;
        }

        return used[x] = find(used[x]);
    }

    static int binarySearch(int target){
        int left = 0, right = M - 1;
        int res = 0;

        while(left <= right){
            int mid = (left + right) / 2;

            if(nums[mid] > target){
                res = mid;
                right = mid -1;
            }else{
                left = mid + 1;
            }
        }

        return res;
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        nums = new int[M];
        used = new int[M+1];
        cards = new int[K];

        st = new StringTokenizer(br.readLine());
        for(int m = 0;m < M; m++){
            nums[m] = Integer.parseInt(st.nextToken());
            used[m] = m;
        }
        used[M] = M;

        st = new StringTokenizer(br.readLine());
        for(int k = 0; k < K; k++){
            cards[k] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);
    }
    
    static void print() throws Exception {
        System.out.println(sb);
    }
}