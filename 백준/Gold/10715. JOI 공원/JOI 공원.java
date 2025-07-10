import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, M, C;
    static List<Road>[] roads;
    static long[] dists;
    static boolean[] visited;
    static long totalDist;
    static long answer;

    static final long INF = 1_000_000_000_000L;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        answer = totalDist;
        dijsktra(1);
    }

    static void dijsktra(int s){
        PriorityQueue<Road> queue = new PriorityQueue<>();
        queue.offer(new Road(s, 0));
        dists[s] = 0;

        while(!queue.isEmpty()) {
            Road now = queue.poll();

            if(visited[now.idx] || dists[now.idx] < now.dist) continue;
            visited[now.idx] = true;

            for(Road to : roads[now.idx]){
                if(visited[to.idx]){
                    totalDist -= to.dist;
                }
            }

            answer = Math.min(answer, totalDist + C * now.dist);

            for(Road to : roads[now.idx]){
                long nDist = now.dist + to.dist;

                if(dists[to.idx] > nDist){
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
        C = Integer.parseInt(st.nextToken());

        roads = new List[N+1];
        dists = new long[N+1];
        visited = new boolean[N+1];

        for(int n = 0; n <= N; n++){
            roads[n] = new ArrayList<>();
            dists[n] = INF;
        }

        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            roads[from].add(new Road(to, dist));
            roads[to].add(new Road(from, dist));
            totalDist += dist;
        }
    }
    
    static void print() throws Exception {
        System.out.println(answer);
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