import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int K, N, M;
    static List<Road>[] roads;
    static int[][] dists;
    static int S, E;
    static int answer = -1;

    static final int INF = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        PriorityQueue<Road> queue = new PriorityQueue<>();
        queue.offer(new Road(S, 0, 0));
        dists[S][0] = 0;

        while(!queue.isEmpty()){
            Road now = queue.poll();
            // System.out.println(now);

            if(now.idx == E){
                answer = now.dist;
                return;
            }

            if(dists[now.idx][now.cost] < now.dist){
                continue;
            }

            for(Road to : roads[now.idx]){
                int nDist = now.dist + to.dist;
                int nCost = now.cost + to.cost;

                if(nCost < K && dists[to.idx][nCost] > nDist){
                    dists[to.idx][nCost] = nDist;
                    queue.offer(new Road(to.idx, nDist, nCost));
                }
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        roads = new List[N+1];
        dists = new int[N+1][K];

        for(int n = 0; n <= N; n++){
            roads[n] = new ArrayList<>();
            Arrays.fill(dists[n], INF);
        }

        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            roads[from].add(new Road(to, dist, cost));
            roads[to].add(new Road(from, dist, cost));
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
    }
    
    static void print() throws Exception {
        System.out.println(answer);
    }

    static class Road implements Comparable<Road>{
        int idx;
        int dist, cost;

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