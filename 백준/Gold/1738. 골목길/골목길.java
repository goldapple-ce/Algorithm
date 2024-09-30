import java.io.*;
import java.util.*;
 
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, M;
    static List<Node> roads;
    static int[] dists;
    static int[] way;
    
    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        bellmanFord(1);
        isCycle();

    }
    static void isCycle(){
        for(Node now : roads){
            int nDist = dists[now.from] + now.dist;
            if(dists[now.from] != Integer.MIN_VALUE  && dists[now.to] < nDist && isCycleOnPath(now.to)){
                way[0] = -1;
                return;
            }
        }
    }

    static boolean isCycleOnPath(int start) {
        boolean[] visited = new boolean[N + 1];
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(start);
    
        while (!queue.isEmpty()) {
            int now = queue.poll();

            if (visited[now]){ 
                continue;
            }
            visited[now] = true;
        
            for (Node road : roads) {
                if (road.from == now && !visited[road.to]) {
                    queue.add(road.to);
                }
            }
        }
    
        return visited[N];
      }

    static void bellmanFord(int start){
        dists[start] = 0;

        for(int n = 0; n < N; n++){
            for(Node now : roads){
                int nDist = dists[now.from] + now.dist;
                if(dists[now.from] != Integer.MIN_VALUE && dists[now.to] < nDist){
                    way[now.to] = now.from;
                    dists[now.to] = nDist;
                }
            }
        }
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        roads = new ArrayList<>();
        dists = new int[N+1];
        way = new int[N+1];

        Arrays.fill(dists, Integer.MIN_VALUE);

        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            roads.add(new Node(from,to, dist));
        }
    }
    
    static void print() throws Exception {
        if(way[0] == -1 || dists[N] == Integer.MIN_VALUE){
            System.out.println(-1);
        }else{
            int idx = N;
            while(idx != 0){
                sb.insert(0,idx+" ");
                idx = way[idx];
            }
            System.out.println(sb);
        }
    }

    static class Node{
        int from, to, dist;

        public Node(int from, int to, int dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }

        @Override
        public String toString() {
            return "Node [from=" + from + ", to=" + to + ", dist=" + dist + "]";
        }

    }
}