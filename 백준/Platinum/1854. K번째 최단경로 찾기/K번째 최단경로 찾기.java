import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, M, K;
    static List<Road>[] roads;
    static List<Integer>[] records;

    static final int INF = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        PriorityQueue<Road> queue = new PriorityQueue<>();
        queue.offer(new Road(1, 0));

        while(!queue.isEmpty()){
            Road now = queue.poll();

            if(records[now.idx].size() >= K){
                continue;
            }

            records[now.idx].add(now.dist);

            for(Road to : roads[now.idx]){
                queue.offer(new Road(to.idx, now.dist + to.dist));
            }
        }
    }

    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        roads = new List[N+1];
        records = new List[N+1];

        for(int n = 0; n <= N; n++){
            roads[n] = new ArrayList<>();
            records[n] = new ArrayList<>();
        }

        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            roads[from].add(new Road(to, dist));
        }
    }
    
    static void print() throws Exception {
        for(int n = 1; n <= N; n++){
            List<Integer> record = records[n];
            if(record.size() < K){
                sb.append(-1);
            }else{
                sb.append(record.get(K-1));
            }

            sb.append('\n');
        }
        System.out.println(sb);
    }

    static class Road implements Comparable<Road>{
        int idx, dist;
        
        public Road(int idx, int dist){
            this.idx = idx;
            this.dist = dist;
        }

        @Override
        public String toString() {
            return "Road [idx=" + idx + ", dist=" + dist + "]";
        }

        @Override
        public int compareTo(Main.Road o) {
            return this.dist - o.dist;
        }
        
    }
}