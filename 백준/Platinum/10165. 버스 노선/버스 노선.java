import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, M;
    static Route[] routes;
    static boolean[] answer;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        for(int m = 0; m < M;){
            Route route = routes[m];
            // System.out.println(route);
            m++;

            while(m < 2*M && route.to >= routes[m].to){
                answer[routes[m].idx] = false;
                m++;
            }
        }
    }
    
    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        routes = new Route[M*2];
        answer = new boolean[M];

        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            long from = Long.parseLong(st.nextToken());
            long to = Long.parseLong(st.nextToken());

            if(from > to) to += N;

            routes[m] = new Route(m, from, to);
            routes[m+M] = new Route(m, N+from, N+to);
        }

        Arrays.sort(routes);
        // for(Route route : routes) System.out.println(route);

        // System.out.println();
        Arrays.fill(answer, true);
    }
    
    static void print() throws Exception {
        for(int m = 0; m < M; m++){
            if(answer[m])
                sb.append(m+1).append(' ');
        }
        System.out.println(sb);
    }

    static class Route implements Comparable<Route>{
        int idx;
        long from, to;

        public Route(int idx, long from, long to) {
            this.idx = idx;
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "Route [idx=" + idx + ", from=" + from + ", to=" + to + "]";
        }
        @Override
        public int compareTo(Main.Route o) {
            if(this.from == o.from)
                return Long.compare(o.to, this.to);
            return Long.compare(this.from, o.from);
        }
        
    }
}