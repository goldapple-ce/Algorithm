import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, M, D, E;
    static int[] heights;
    static List<Road>[] roads;
    static long[][] dists;
    static final long INF = 1_000_000_000_001L;
    static long answer = -INF;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        dijkstra(1, dists[0]);
        dijkstra(N, dists[1]);

        // System.out.println(Arrays.toString(dists[0]));
        // System.out.println(Arrays.toString(dists[1]));

        for(int n = 0; n <= N; n++){
            if(dists[0][n] != INF && dists[1][n] != INF){
                long value = heights[n] * E - (dists[0][n] + dists[1][n]) * D;
                answer = Math.max(answer, value);
            }
        }

    }

    static void dijkstra(int start, long[] dists){
        PriorityQueue<Road> queue = new PriorityQueue<>();
        queue.offer(new Road(start, 0));
        dists[start] = 0;

        while(!queue.isEmpty()){
            Road now = queue.poll();

            if(dists[now.idx] < now.dist){
                continue;
            }

            for(Road to : roads[now.idx]){
                long nDist = now.dist + to.dist;

                if(dists[to.idx] > nDist && heights[now.idx] < heights[to.idx]){
                    dists[to.idx] = nDist;
                    queue.offer(new Road(to.idx, nDist));
                }
            }
        }
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());   // 지점수
        M = Integer.parseInt(st.nextToken());   // 경로수
        D = Integer.parseInt(st.nextToken());   // 체력 소모량
        E = Integer.parseInt(st.nextToken());   // 높이 비례 성취감

        dists = new long[2][N+1];
        heights = new int[N+1];
        roads = new List[N+1];

        Arrays.fill(dists[0], INF);
        Arrays.fill(dists[1], INF);

        for(int n = 0; n <= N;n++){
            roads[n] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());

        for(int n = 1; n <= N; n++){
            heights[n] = Integer.parseInt(st.nextToken());
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
        System.out.println(answer == -INF ? "Impossible" :answer);
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
        public int compareTo(Road o) {
            return Long.compare(this.dist, o.dist);
        }
        
    }
}