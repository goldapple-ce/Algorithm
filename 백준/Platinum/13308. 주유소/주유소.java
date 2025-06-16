import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static final int INF = 1_000_000_000;

    static int N, M;
    static int[] stations;
    static List<Road>[] roads;
    static int[][] costs;
    static int answer = INF;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        for(int n = 1; n < N; n++){
            dijkstra(n,costs[n]);
        }

        for(int n = 2; n < N; n++){
            answer = Math.min(answer,costs[1][n] + costs[n][N]);
        }
    }

    static void dijkstra(int s, int[] costs){
        Arrays.fill(costs, INF);

        PriorityQueue<Road> queue = new PriorityQueue<>();
        queue.offer(new Road(s, 0, stations[s]));
        costs[s] = 0;

        while(!queue.isEmpty()){
            Road now = queue.poll();

            if(now.dist > costs[now.idx]) continue;

            for(Road to : roads[now.idx]){
                int nCost = to.dist * now.cost + now.dist;

                if(costs[to.idx] > nCost){
                    costs[to.idx] = nCost;
                    queue.offer(new Road(to.idx, nCost,Math.min(now.cost, stations[to.idx])));
                }
            }
        }
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        stations = new int[N+1];
        roads = new List[N+1];
        costs = new int[N+1][N+1];

        for(int n = 0; n <= N; n++)
            roads[n] = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int n = 1; n <= N; n++)
            stations[n] = Integer.parseInt(st.nextToken());
        
        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            roads[from].add(new Road(to, dist,0));
            roads[to].add(new Road(from, dist,0));
        }

    }
    
    static void print() throws Exception {
        System.out.println(answer);
    }

    static class Road implements Comparable<Road>{
        int idx, dist;
        int cost;

        public Road(int idx, int dist, int cost) {
            this.idx = idx;
            this.dist = dist;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Road [idx=" + idx + ", dist=" + dist + ", cost=" + cost + "]";
        }

        @Override
        public int compareTo(Main.Road o) {
            return this.dist - o.dist;
        }
        
    }
}