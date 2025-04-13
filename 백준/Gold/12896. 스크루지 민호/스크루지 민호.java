import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N;
    static List<Integer>[] roads;
    static int[][] costs;
    static int maxDepth, depthIdx;
    static int answer = 200_001;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        dfs(0, 1, 0,0);

        maxDepth = 0;
        dfs(0, depthIdx,0,0);
        dfs(0, depthIdx, 0,1);

        for(int n = 1; n <= N; n++){
            answer = Math.min(answer, Math.max(costs[n][0], costs[n][1]));
        }
    }

    static void dfs(int pre, int now, int depth, int type){
        if(maxDepth < depth){
            maxDepth = depth;
            depthIdx = now;
        }

        costs[now][type] = depth;

        for(int to : roads[now]){
            if(pre != to){
                dfs(now, to, depth+1,type);
            }
        }
    }
    
    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());

        roads = new List[N+1];
        costs = new int[N+1][2];

        for(int n = 0; n <= N; n++){
            roads[n] = new ArrayList<>();
        }

        for(int n = 0; n < N-1; n++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            roads[from].add(to);
            roads[to].add(from);
        }
    }
    
    static void print() throws Exception {
        System.out.println(answer);
    }
}