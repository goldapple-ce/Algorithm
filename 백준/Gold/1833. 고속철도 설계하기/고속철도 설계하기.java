import java.util.*;
import java.io.*;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N;
    static int[][] map;
    static List<Road> roads = new ArrayList<>();
    static int[] parent;

    static int answer;
    static List<Road> answerRoad = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        for(Road road : roads){
            if(road.cost < 0){
                union(road.from, road.to);
                answer += -road.cost;
            }else if(find(road.from) != find(road.to)){
                union(road.from, road.to);
                answer += road.cost;
                if(road.cost > 0){
                    answerRoad.add(road);
                }
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
        if(parent[x] == x){
            return x;
        }
        return parent[x] = find(parent[x]);
    }
    
    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        parent = new int[N];

        for(int n = 0; n < N; n++){
            parent[n] = n;
        }

        for(int from = 0; from < N; from++){
            st = new StringTokenizer(br.readLine());
            for(int to  = 0; to < N; to++){
                map[from][to] = Integer.parseInt(st.nextToken());
            }
        }

        for(int from = 0; from < N-1; from++){
            for(int to  = from+1; to < N; to++){
                roads.add(new Road(from, to, map[from][to]));
            }
        }
        roads.sort(null);
    }
    
    static void print() throws Exception {
        sb.append(answer + " "+ answerRoad.size());
        for(Road road : answerRoad){
            sb.append("\n"+(road.from+1) + " " + (road.to+1));
        }
        System.out.println(sb);
    }

    static class Road implements Comparable<Road>{
        int from, to;
        int cost;

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