import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, M;
    static Set<Integer> set = new HashSet<>();

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
    }
    
    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for(int n = 0; n < N; n++){
            set.add(Integer.parseInt(st.nextToken()));
        }

        M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for(int m  = 0; m < M; m++){
            int num = Integer.parseInt(st.nextToken());
            sb.append(set.contains(num) ?1 :0).append(' ');
        }
    }
    
    static void print() throws Exception {
        System.out.println(sb);
    }
}