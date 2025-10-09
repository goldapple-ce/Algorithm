import java.io.*;
import java.util.*;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, P;
    static int[] countries;
    static Road[] roads;
    static int[] parent;
    static int answer = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        for(Road road : roads){
            if(find(road.from) != find(road.to)){
                // System.out.println(String.format("union %d, %d. cost : %d", from, to, road.cost));
                union(road.from, road.to);
                answer += road.cost;
            }
        }
    }

    static int find(int x){
        if(parent[x] == x) return x;
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
        P = Integer.parseInt(st.nextToken());

        countries = new int[N+1];
        roads = new Road[P];
        parent = new int[N+1];

        for(int n = 0; n <= N; n++){
            parent[n] = n;
        }

        for(int n = 1; n <= N; n++){
            countries[n] = Integer.parseInt(br.readLine());
            answer = Math.min(answer, countries[n]);
        }

        for(int p = 0; p < P; p++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            roads[p] = new Road(from, to, cost*2 + countries[from] + countries[to]);
        }
        Arrays.sort(roads);
    }
    
    static void print() throws Exception {
        System.out.println(answer);
    }

    static class Road implements Comparable<Road>{
        int from, to;
        int cost;

        public Road(int from, int to, int cost){
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