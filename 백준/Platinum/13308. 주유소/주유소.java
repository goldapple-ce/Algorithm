import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static final int INF = 1_000_000_000;

    static int N, M;
    static int[] prices;
    static List<Road>[] roads;
    static int[][] costs;
    static int answer = INF;

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
                int nCost = to.dist * now.price + now.dist;
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
        costs = new int[N+1][2501];

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
        int idx, dist;
        int price;

        public Road(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }

        public Road(int idx, int dist, int price) {
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
            return this.dist - o.dist;
        }
        
    }
}