import java.io.*;
import java.util.*;
 
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static int[] dists;
    static List<Road>[] roads;
    static int[] way;
    static int answer;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        int pivot = dijkstra(1);
        int dist = 0;

        int FROM, TO = N;
        
        while(TO != 1){
            FROM = way[TO];
            dist = Math.max(dist,dijkstra(1,FROM,TO));
            for(int i = 1; i <= N; i++){
                if(dists[i] == Integer.MAX_VALUE){
                    answer = -1;
                    return;
                }
            }
            TO = FROM;
        }
        answer = dist-pivot;
    }

    static int dijkstra(int start, int FROM, int TO){
        PriorityQueue<Road> queue = new PriorityQueue<>();
        Arrays.fill(dists, Integer.MAX_VALUE);
        queue.offer(new Road(start, 0));
        dists[start] = 0;

        while(!queue.isEmpty()){
            Road now = queue.poll();

            if(dists[now.idx] < now.dist){
                continue;
            }

            for(Road to : roads[now.idx]){
                if(now.idx == FROM && to.idx == TO){
                    continue;
                }

                int nDist = dists[now.idx] + to.dist;
                if(dists[to.idx] > nDist){
                    dists[to.idx] = nDist;
                    queue.offer(new Road(to.idx, nDist));
                }
            }
        }
        return dists[N];
    }
    static int dijkstra(int start){
        PriorityQueue<Road> queue = new PriorityQueue<>();
        queue.offer(new Road(start, 0));
        dists[start] = 0;

        while(!queue.isEmpty()){
            Road now = queue.poll();

            if(dists[now.idx] < now.dist){
                continue;
            }

            for(Road to : roads[now.idx]){
                int nDist = dists[now.idx] + to.dist;
                if(dists[to.idx] > nDist){
                    dists[to.idx] = nDist;
                    way[to.idx] = now.idx;
                    queue.offer(new Road(to.idx, nDist));
                }
            }
        }
        return dists[N];
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dists = new int[N+1];
        roads = new ArrayList[N+1];
        way = new int[N+1];

        for(int n = 1; n <= N; n++){
            dists[n] = Integer.MAX_VALUE;
            roads[n] = new ArrayList<>();
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

        public Road(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }

        @Override
        public String toString() {
            return "Road [idx=" + idx + ", dist=" + dist + "]";
        }

        @Override
        public int compareTo(Road o){
            return this.dist - o.dist;
        }
    }
}