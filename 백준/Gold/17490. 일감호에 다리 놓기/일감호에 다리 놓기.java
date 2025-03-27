import java.util.*;
import java.io.*;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, M;
    static long K;
    static int[] parent;
    static List<Bridge> bridges = new ArrayList<>();
    static long needs;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        if(M <= 1) return;
        for(Bridge bridge : bridges){
            if(find(bridge.from) != find(bridge.to)){
                union(bridge.from, bridge.to);
                needs += bridge.dist;
            }
        }
    }

    static int find(int x){
        if(parent[x] == x){
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y){
        x = find(x);
        y = find(y);

        if(x > y){
            parent[x] = y;
        }else{
            parent[y] = x;
        }
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Long.parseLong(st.nextToken());

        parent = new int[N+1];

        for(int n = 0; n <= N; n++){
            parent[n] = n;
        }

        st = new StringTokenizer(br.readLine());
        for(int n = 1; n <= N; n++){
            int dist = Integer.parseInt(st.nextToken());
            bridges.add(new Bridge(0, n, dist));
        }

        boolean[] disconnected = new boolean[N+1];

        for(int m = 0;  m < M; m++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            if(from > to){
                int tmp = from;
                from = to;
                to = tmp;
            }
            if(from == 1 && to == N) disconnected[to] = true;
            else disconnected[from] = true;
        }

        for(int n = 1; n <= N; n++){
            if(!disconnected[n]){
                bridges.add(new Bridge(n, n == N ? 1 :n+1, 0));
            }
        }
        bridges.sort(null);
    }
    
    static void print() throws Exception {
        System.out.println(K >= needs ?"YES" :"NO");
    }

    static class Bridge implements Comparable<Bridge>{
        int from, to;
        int dist;

        public Bridge(int from, int to, int dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }

        @Override
        public String toString() {
            return "Bridge [from=" + from + ", to=" + to + ", dist=" + dist + "]";
        }

        @Override
        public int compareTo(Main.Bridge o) {
            return this.dist - o.dist;
        }
        
    }
}