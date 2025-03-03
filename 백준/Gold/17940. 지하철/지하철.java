import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, M;
    static int[] companies;
    static int[][] graph;
    static List<Line>[] lines;
    static int[] transfers, dists;

    static final int INF = 1_000_000_001;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        dijkstra(0);
    }

    static void dijkstra(int start){
        PriorityQueue<Line> queue = new PriorityQueue<>(new Comparator<Line>() {
            @Override
            public int compare(Main.Line o1, Main.Line o2) {
                if(o1.transfer == o2.transfer){
                    return o1.dist - o2.dist;
                }
                return o1.transfer - o2.transfer;
            }
        });

        queue.offer(new Line(start, 0, 0));
        transfers[start] = 0;

        while(!queue.isEmpty()){
            Line now = queue.poll();
            // System.out.println(now);

            if(transfers[now.idx] < now.transfer){
                continue;
            }

            for(Line to : lines[now.idx]){
                int nTransfer = transfers[now.idx] + (companies[now.idx] == companies[to.idx] ? 0 :1);
                int nDist = dists[now.idx] + to.dist;

                if(transfers[to.idx] > nTransfer || (transfers[to.idx] == nTransfer && dists[to.idx] > nDist)){
                    transfers[to.idx] = nTransfer;
                    dists[to.idx] = nDist;
                    queue.offer(new Line(to.idx, nDist, nTransfer));
                }
            }
        }
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        companies = new int[N];
        graph = new int[N][N];
        lines = new List[N];
        transfers = new int[N];
        dists = new int[N];

        for(int n = 0; n < N; n++){
            lines[n] = new ArrayList<>();
            transfers[n] = INF;
        }

        for(int n = 0; n < N; n++){
            companies[n] = Integer.parseInt(br.readLine());
        }

        for(int row = 0;row < N; row++){
            st = new StringTokenizer(br.readLine());
            for(int col = 0; col < N; col++){
                graph[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        for(int from = 0;from < N; from++){
            for(int to = 0; to < N; to++){
                if(graph[from][to] != 0){
                    lines[from].add(new Line(to, graph[from][to], 0));
                }
            }
        }
    }
    
    static void print() throws Exception {
        sb.append(transfers[M]).append(' ').append(dists[M]);
        System.out.println(sb);

    }

    static class Line {
        int idx, dist;
        int transfer;

        public Line(int idx, int dist, int transfer) {
            this.idx = idx;
            this.dist = dist;
            this.transfer = transfer;
        }
        @Override
        public String toString() {
            return "Line [idx=" + idx + ", dist=" + dist + ", transfer=" + transfer + "]";
        }
        
    }
}