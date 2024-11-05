import java.io.*;
import java.util.*;
 
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, K, W;
    static List<Integer>[] ways;
    static Queue<Integer> queue;
    static int[] wayscost;
    static int[] costs;
    static int answer;
    static int[] cntOfParent;

    static int INF = 100_000_001;

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++){
            input();
            run();
        }
        print();
    }
    
    static void run() throws Exception {
        for(int n = 1; n <= N; n++){
            costs[n] = wayscost[n];

            if(cntOfParent[n] == 0){
                queue.add(n);
            }
        }

        while(!queue.isEmpty()){
            int now = queue.poll();

            for(int to : ways[now]){
                costs[to] = Math.max(costs[to], costs[now] + wayscost[to]);

                if(--cntOfParent[to] == 0){
                    queue.offer(to);
                }
            }
        }
        sb.append(costs[W]).append('\n');
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        wayscost = new int[N+1];
        ways = new List[N+1];
        costs = new int[N+1];
        cntOfParent = new int[N+1];
        answer = 0;
        queue = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());
        for(int n = 1; n <= N; n++){
            wayscost[n] = Integer.parseInt(st.nextToken());
            ways[n] = new ArrayList<>();
        }

        for(int k = 0; k < K; k++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            ways[from].add(to);
            cntOfParent[to]++;
        }

        W = Integer.parseInt(br.readLine());
    }
    
    static void print() throws Exception {
        System.out.println(sb);
    }
}