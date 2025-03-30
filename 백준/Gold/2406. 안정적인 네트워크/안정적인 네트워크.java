import java.util.*;
import java.io.*;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, M;
    static int[][] mapOfCost;
    static int[] parent;
    static List<Line> answerLine = new ArrayList<>();
    static int[] answer = new int[2];

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        List<Line> lines = new ArrayList<>();

        for(int from = 2; from < N; from++){
            for(int to = from+1; to <= N; to++){
                lines.add(new Line(from, to, mapOfCost[from][to]));
            }
        }

        lines.sort(null);

        for(Line line : lines){
            if(find(line.from) != find(line.to)){
                union(line.from, line.to);

                if(line.cost != 0){
                    answer[0] += line.cost;
                    answer[1]++;

                    answerLine.add(line);
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
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        mapOfCost = new int[N+1][N+1];
        Line[] connected = new Line[M];
        parent = new int[N+1];

        for(int n = 0; n <= N; n++){
            parent[n] = n;
        }

        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            connected[m] = new Line(from, to, 0);
        }

        for(int from = 1; from <= N; from++){
            st = new StringTokenizer(br.readLine());
            for(int to = 1; to <= N; to++){
                mapOfCost[from][to] = Integer.parseInt(st.nextToken());
            }
        }

        for(Line connect : connected){
            mapOfCost[connect.from][connect.to] = mapOfCost[connect.to][connect.from] = 0;
        }
    }
    
    static void print() throws Exception {
        sb.append(answer[0] + " "+ answer[1]);

        sb.append('\n');
        for(Line line : answerLine){
            sb.append(line.from + " "+ line.to)
            .append('\n');
        }
        System.out.println(sb);
    }

    static class Line implements Comparable<Line>{
        int from, to;
        int cost;

        public Line(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Line [from=" + from + ", to=" + to + ", cost=" + cost + "]";
        }

        @Override
        public int compareTo(Main.Line o) {
            return this.cost - o.cost;
        }
    }
}