import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, M;
    static long[] dists;
    static List<Road>[] roads;
    static final long INF = 1_000_000_000_001L;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        dijkstra(1);
    }

    static void dijkstra(int start){
        PriorityQueue<Road> queue = new PriorityQueue<>();
        queue.offer(new Road(start, 0));
        dists[start] = 0;

        while(!queue.isEmpty()){
            Road now = queue.poll();
            if(dists[now.idx] < now.dist){
                continue;
            }

            for(Road to : roads[now.idx]){
                long nDist = to.dist;
                if(to.dist < now.dist){
                    nDist += (long) Math.ceil(((double)now.dist-to.dist)/M) * M;
                }
                nDist++;

                if(nDist < dists[to.idx]){
                    dists[to.idx] = nDist;
                    queue.offer(new Road(to.idx, nDist));
                }
            }

        }

    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dists = new long[N+1];
        roads = new List[N+1];

        Arrays.fill(dists, INF);
        for(int n = 0; n <= N; n++){
            roads[n] = new ArrayList<>();
        }

        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = m;

            roads[from].add(new Road(to, dist));
            roads[to].add(new Road(from, dist));
        }   
    }
    
    static void print() throws Exception {
        System.out.println(dists[N]);
    }

    static class Road implements Comparable<Road>{
        int idx;
        long dist;

        public Road(int idx, long dist) {
            this.idx = idx;
            this.dist = dist;
        }

        @Override
        public String toString() {
            return "Road [idx=" + idx + ", dist=" + dist + "]";
        }

        @Override
        public int compareTo(Main.Road o) {
            return Long.compare(this.dist, o.dist);
        }
        
    }
}