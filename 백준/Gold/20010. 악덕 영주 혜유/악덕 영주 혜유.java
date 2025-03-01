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
    static int totalCost, maxCost;
    static List<Road>[] village;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        mst();
        bfs(bfs(0));
    }

    static int bfs(int start){
        Deque<Road> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N];
        int maxIdx = 0;

        queue.offer(new Road(start, 0));
        visited[start] = true;

        while(!queue.isEmpty()){
            Road now = queue.poll();

            if(maxCost < now.cost){
                maxCost = now.cost;
                maxIdx = now.to;
            }

            for(Road next : village[now.to]){
                if(!visited[next.to]){
                    visited[next.to] = true;
                    queue.offer(new Road(next.to, now.cost+next.cost));
                }
            }
        }
        
        return maxIdx;
    }

    static void mst(){
        int connectionCnt = 0;

        for(Road road : roads){
            int from = road.from, to = road.to;
            int cost = road.cost;

            if(find(from) != find(to)){
                union(from, to);
                village[from].add(new Road(to, cost));
                village[to].add(new Road(from, cost));
                totalCost += cost;

                if(++connectionCnt == N-1)
                    return;
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
        village = new List[N];

        for(int n = 0; n < N; n++){
            parent[n] = n;
            village[n] = new ArrayList<>();
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
        System.out.println(totalCost);
        System.out.println(maxCost);
    }

    static class Road implements Comparable<Road>{
        int from, to;
        int cost;

        public Road(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        public Road(int to, int cost){
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