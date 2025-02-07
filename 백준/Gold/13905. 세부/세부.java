import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, M;
    static int S, E;
    static Road[] roads;
    static int[] parent;
    static int answer;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        for(Road now : roads){
            union(now.from, now.to);
            answer = now.weight;
            if(find(S) == find(E)){
                break;
            }
        }
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

    static int find(int x){
        if(x == parent[x]){
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        roads = new Road[M];
        parent = new int[N+1];

        for(int n = 1; n <= N; n++){
            parent[n] = n;
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            
            roads[m] = new Road(from, to, weight);
        }

        Arrays.sort(roads);
    }
    
    static void print() throws Exception {
        System.out.println(parent[S] != parent[E] ? 0 : answer);
    }

    static class Road implements Comparable<Road>{
        int from, to;
        int weight;

        public Road(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Main.Road o) {
            return o.weight - this.weight;
        }

        @Override
        public String toString() {
            return "Road [from=" + from + ", to=" + to + ", weight=" + weight + "]";
        }
        
    }
}