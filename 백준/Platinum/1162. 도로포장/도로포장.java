import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, M, K;
    static List<Road>[] roads;
    static long[][] dists;

    static final long INF = 100_000_000_000L;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        PriorityQueue<Road> queue = new PriorityQueue<>();
        queue.offer(new Road(1, 0,0));
        dists[0][1] = 0;

        while(!queue.isEmpty()) {
            Road now = queue.poll();
            
            if(dists[now.k][now.idx] < now.dist){
                continue;
            }

            // System.out.println(now);

            for(Road to : roads[now.idx]){
                long nDist = now.dist + to.dist;

                if(dists[now.k][to.idx] > nDist){
                    dists[now.k][to.idx] = nDist;
                    queue.offer(new Road(to.idx, nDist, now.k));
                }

                if(now.k < K && (dists[now.k+1][to.idx] > now.dist || dists[now.k +1][to.idx] > dists[now.k][to.idx])){
                    dists[now.k+1][to.idx] = Math.min(now.dist, dists[now.k][to.idx]);
                    queue.offer(new Road(to.idx, dists[now.k+1][to.idx], now.k +1));
                }
            }
        }

        // for(long[] row : dists){
        //     System.out.println(Arrays.toString(row));
        // }
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        roads = new List[N+1];
        dists = new long[K+1][N+1];

        for(int n = 0; n <= N; n++){
            roads[n] = new ArrayList<>();
            if(n != 1){
                for(int k = 0; k <= K; k++){
                    dists[k][n] = INF;
                }
            }
        }



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
        System.out.println(dists[K][N]);
    }

    static class Road implements Comparable<Road>{
        int idx;
        long dist;
        int k;

        public Road(int idx, long dist, int k){
            this.idx = idx;
            this.dist = dist;
            this.k = k;
        }

        @Override
        public String toString() {
            return "Road [idx=" + idx + ", dist=" + dist + ", k=" + k + "]";
        }

        @Override
        public int compareTo(Main.Road o) {
            return Long.compare(this.dist, o.dist);
        }
    }
}