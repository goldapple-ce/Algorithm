import java.io.*;
import java.util.*;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, M, K, Q;
    static int[] attractions;
    static List<Integer>[] grows;
    static int[][] questions;
    static int[] answer;
    // static int[] kids;


    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }

    static void run() throws Exception {
        // for(List<Integer> grow : grows)System.out.println(grow);

        for(int[] q : questions){
            int kid1 = q[0], kid2 = q[1], attraction = attractions[q[2]];
            // System.out.println(String.format("kid1 : %d, kid2 : %d, attr : %d", kid1,kid2,attraction));
            int a = 0;
            int left = 1, right = K;
            while(left <= right){
                int mid = (left + right) / 2;
                int kid1H = getHeight(grows[kid1], mid);
                int kid2H = getHeight(grows[kid2], mid);

                // System.out.println(String.format("day : %d, kid1H : %d, kid2H : %d", mid, kid1H, kid2H));
                if(kid1H + kid2H >= attraction){
                    a = mid;
                    right = mid -1;
                }else{
                    left = mid + 1;
                }
            }
            // System.out.println("a : " + a);
            if(a != 0) answer[a]++;
        }
        // System.out.println(Arrays.toString(answer));
    }

    static int getHeight(List<Integer> grow, int day){
        int answer = -1;
        int left = 0, right = grow.size();

        while(left < right){
            int mid = (left + right) / 2;

            if(grow.get(mid) <= day){
                answer = mid;
                left = mid+1;
            }else{
                right = mid;
            }
        }
        return answer+1;
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        grows = new List[N+1];
        attractions = new int[M+1];
        questions = new int[Q][3];
        answer = new int[K+1];

        for(int n = 0; n <= N; n++){
            grows[n] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for(int m = 1; m <= M; m++){
            attractions[m] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int k = 1; k <= K; k++){
            int kid = Integer.parseInt(st.nextToken());
            grows[kid].add(k);
        }

        for(int q = 0; q < Q; q++){
            st = new StringTokenizer(br.readLine());

            questions[q][0] = Integer.parseInt(st.nextToken());
            questions[q][1] = Integer.parseInt(st.nextToken());
            questions[q][2] = Integer.parseInt(st.nextToken());
        }
    }
    
    static void print() throws Exception {
        for(int k = 1; k <= K; k++){
            answer[k] += answer[k-1];
            System.out.println(answer[k]);
        }

    }

}