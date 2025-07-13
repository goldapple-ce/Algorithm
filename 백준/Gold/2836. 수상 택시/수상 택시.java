import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, M;
    static List<Road> roads;
    static long answer;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        int from = 0, to = 0;

        for(int n = 0; n < N; n++){
            if(roads.get(n).from > to){
                answer += (to - from) * 2;
                from = roads.get(n).from;
                to = roads.get(n).to;
            }else{
                to = Math.max(to, roads.get(n).to);
            }
        }
        answer += (to -from) * 2;
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        roads = new ArrayList<>();
        answer = M;

        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            if(from > to) roads.add(new Road(to, from));
        }
        
        N = roads.size();
        Collections.sort(roads);
    }
    
    static void print() throws Exception {
        System.out.println(answer);
    }

    static class Road implements Comparable<Road>{
        int from, to;

        public Road(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "Road [from=" + from + ", to=" + to + "]";
        }

        @Override
        public int compareTo(Main.Road o) {
            if(this.from == o.from) 
                return this.to - o.to;
            return this.from - o.from;
        }
    }
}