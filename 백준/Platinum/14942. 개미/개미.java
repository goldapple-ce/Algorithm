import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N;
    static int[] ants;
    static int[][] costs, paths;
    static int[] answer;
    static List<Road>[] roads;
    static boolean[] visited;

    static final int MAX = 17;
        
    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        for(int depth = 2; depth <= MAX; depth++){
            for(int now = 1; now <= N; now++){
                paths[now][depth] = paths[paths[now][depth-1]][depth-1];
                costs[now][depth] = costs[now][depth-1] + costs[paths[now][depth-1]][depth-1];
            }
        }

        for(int n = 1; n <= N; n++){
            int now = n;

            while(now != 1){
                int depth = binarySearch(now, ants[n]);
                ants[n] -= costs[now][depth];
                if(depth == 0) break;
                now = paths[now][depth];
            }

            answer[n] = now;
        }
    }

    static int binarySearch(int idx, int energy){
        int ans = 0;
        int left = 0, right = MAX;

        while(left <= right){
            int mid = (left + right) / 2;

            if(costs[idx][mid] == energy){
                return mid;
            }else if(costs[idx][mid] < energy){
                ans = mid;
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        
        return ans;
    }

    static void dfs(int now){
        visited[now] = true;

        for(Road to : roads[now]){
            if(!visited[to.idx]){
                paths[to.idx][1] = now;
                costs[to.idx][1] = to.cost;
                dfs(to.idx);
            }
        }
    }
    
    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());

        ants = new int[N+1];
        costs = new int[N+1][MAX+1];
        paths = new int[N+1][MAX+1];
        roads = new List[N+1];
        visited = new boolean[N+1];
        answer = new int[N+1];

        for(int n = 1; n <= N; n++){
            ants[n] = Integer.parseInt(br.readLine());
            roads[n] = new ArrayList<>();
            paths[n][0] = n;
        }

        for(int n = 0; n < N-1; n++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            roads[from].add(new Road(to, cost));
            roads[to].add(new Road(from, cost));
        }

        dfs(1);

        paths[1][1] = 1;
        costs[1][1] = 0;
    }
    
    static void print() throws Exception {
        for(int n = 1; n <= N; n++){
            sb.append(answer[n]).append('\n');
        }
        System.out.println(sb);
    }

    static class Road implements Comparable<Road>{
        int idx;
        int cost;

        public Road(int idx, int cost){
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Main.Road o) {
            return this.cost - o.cost;
        }
        
    }
}