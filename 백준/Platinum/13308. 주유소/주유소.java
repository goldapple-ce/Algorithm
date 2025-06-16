import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static final long INF = 100_000_000_000L;

    static int N, M;
    static int[] prices;
    static List<Road>[] roads;
    static long[][] costs;
    static long answer = INF;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        dijkstra(1);

        for(int n = 1; n <= 2500; n++){
            answer = Math.min(answer,costs[N][n]);
        }
    }

    static void dijkstra(int s){
        PriorityQueue<Road> queue = new PriorityQueue<>();
        queue.offer(new Road(s, 0, prices[s]));
        costs[s][prices[s]] = 0;

        while(!queue.isEmpty()){
            Road now = queue.poll();

            if(now.dist > costs[now.idx][now.price]) continue;

            for(Road to : roads[now.idx]){
                long nCost = to.dist * now.price + now.dist;
                int nPrice = Math.min(now.price, prices[to.idx]);

                if(costs[to.idx][nPrice] > nCost){
                    costs[to.idx][nPrice] = nCost;
                    queue.offer(new Road(to.idx, nCost, nPrice));
                }
            }
        }
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        prices = new int[N+1];
        roads = new List[N+1];
        costs = new long[N+1][2501];

        st = new StringTokenizer(br.readLine());
        for(int n = 1; n <= N; n++){
            roads[n] = new ArrayList<>();
            Arrays.fill(costs[n], INF);
            prices[n] = Integer.parseInt(st.nextToken());
        }
        
        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            roads[from].add(new Road(to, dist));
            roads[to].add(new Road(from, dist));
        }

    }
    
    static void print() throws Exception {
        System.out.println(answer);
    }

    static class Road implements Comparable<Road>{
        int idx,price;
        long dist;

        public Road(int idx, long dist) {
            this.idx = idx;
            this.dist = dist;
        }

        public Road(int idx, long dist, int price) {
            this.idx = idx;
            this.dist = dist;
            this.price = price;
        }

        @Override
        public String toString() {
            return "Road [idx=" + idx + ", dist=" + dist + ", price=" + price + "]";
        }

        @Override
        public int compareTo(Main.Road o) {
            return Long.compare(this.dist, o.dist);
        }
        
    }
}