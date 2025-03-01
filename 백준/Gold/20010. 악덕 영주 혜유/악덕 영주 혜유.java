import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, K;
    static Road[] roads;
    static int[] parent;
    static int totalCost, maxDist;
    static int[][] dists;
    static final int INF = 1_000_000_001;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        for(Road road : roads){
            if(find(road.from) != find(road.to)){
                union(road.from, road.to);
                dists[road.from][road.to] = road.cost;
                dists[road.to][road.from] = road.cost;
                totalCost += road.cost;
            }
        }

        for(int mid = 0; mid < N; mid++){
            for(int from = 0; from < N; from++){
                for(int to = 0; to < N; to++){
                    dists[from][to] = Math.min(dists[from][to], dists[from][mid] + dists[mid][to]);
                }
            }            
        }

        for(int from = 0; from < N; from++){
            for(int to = 0; to < N; to++){
                maxDist = Math.max(maxDist, dists[from][to]);                
            }
        }
    }

    static void union(int x, int y){
        x = find(x);
        y = find(y);

        if(x <= y){
            parent[y] = x;
        }else{
            parent[x] = y;
        }
    }

    static int find(int x){
        if(parent[x] == x){
            return x;
        }
        return parent[x] = find(parent[x]);
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        roads = new Road[K];
        parent = new int[N];
        dists = new int[N][N];

        for(int n = 0; n < N; n++){
            parent[n] = n;
            Arrays.fill(dists[n], INF);
            dists[n][n] = 0;
        }

        for(int k = 0; k < K; k++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            roads[k] = new Road(from, to, cost);
        }
        Arrays.sort(roads);
    
    }
    
    static void print() throws Exception {
        // for(Road road : roads){
        //     System.out.println(road);
        // }
        // for(int[] dist : dists){
        //     System.out.println(Arrays.toString(dist));
        // }
        // System.out.println(Arrays.toString(parent));
        System.out.println(totalCost);
        System.out.println(maxDist);
    }

    static class Road implements Comparable<Road>{
        int from, to;
        int cost;

        public Road(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Road o) {
            return this.cost - o.cost;
        }

        @Override
        public String toString() {
            return "Road [from=" + from + ", to=" + to + ", cost=" + cost + "]";
        }

    }
}