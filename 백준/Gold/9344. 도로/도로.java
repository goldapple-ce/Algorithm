import java.util.*;
import java.io.*;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, M, P, Q;
    static Road[] roads;
    static int[] parent;

    static int r = 1000010000;

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 0; test_case < T; test_case++){
            input();
            run();
            sb.append('\n');
        }
        print();
    }
    
    static void run() throws Exception {
        for(Road road : roads){
            if(find(road.from) != find(road.to)){
                union(road.from, road.to);
                if((road.from == P && road.to == Q) || (road.from == Q && road.to == P)){
                    sb.append("YES");
                    return;
                }
            }
        }

        sb.append("NO");
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
        P = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        roads = new Road[M];
        parent = new int[N+1];

        for(int n = 0; n <= N;n++){
            parent[n] = n;
        }

        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            roads[m] = new Road(from, to, cost);
        }

        Arrays.sort(roads);
    }
    
    static void print() throws Exception {
        System.out.println(sb);
    }

    static class Road implements Comparable<Road>{
        int from, to, cost;

        public Road(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Road [from=" + from + ", to=" + to + ", cost=" + cost + "]";
        }

        @Override
        public int compareTo(Main.Road o) {
            return this.cost - o.cost;
        }
        
    }
}