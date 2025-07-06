import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int K, N, R;
    static List<Road>[] roads;
    static int[][] dists;
    static int answer = -1;

    static final int INF = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        PriorityQueue<Road> queue = new PriorityQueue<>();
        queue.offer(new Road(1, 0, 0));
        dists[1][0] = 0;

        while(!queue.isEmpty()){
            Road now = queue.poll();
            // System.out.println(now);

            if(now.idx == N){
                answer = now.dist;
                return;
            }
            if(dists[now.idx][now.cost] < now.dist) continue;

            for(Road to : roads[now.idx]){
                int nDist = now.dist + to.dist;
                int nCost = now.cost + to.cost;

                if(nCost <= K &&  dists[to.idx][nCost] > nDist){
                    dists[to.idx][nCost] = nDist;
                    queue.offer(new Road(to.idx, nDist, nCost));
                }
            }
        }


    }

    static void input() throws Exception {
        K = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());
        R = Integer.parseInt(br.readLine());

        roads = new List[N+1];
        dists = new int[N+1][K+1];

        for(int n = 0; n <= N; n++){
            roads[n] = new ArrayList<>();
            Arrays.fill(dists[n], INF);
        }

        for(int r = 0; r < R; r++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            roads[from].add(new Road(to, dist, cost));
        }
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