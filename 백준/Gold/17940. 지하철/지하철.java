import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, M;
    static int[] companies;
    static List<Line>[] lines;
    static int[] transfers, dists;

    static final int INF = 1_000_000_001;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        PriorityQueue<Line> queue = new PriorityQueue<>();
        queue.offer(new Line(0, 0, 0));
        transfers[0] = 0;

        while(!queue.isEmpty()){
            Line now = queue.poll();

            if(now.idx == M) return;
            if(transfers[now.idx] < now.transfer) continue;

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

        for(int from = 0;from < N; from++){
            st = new StringTokenizer(br.readLine());
            for(int to = 0; to < N; to++){
                int dist = Integer.parseInt(st.nextToken());
                if(dist != 0){
                    lines[from].add(new Line(to, dist, 0));
                }
            }
        }
    }
    
    static void print() throws Exception {
        System.out.println(transfers[M] +" "+dists[M]);
    }

    static class Line implements Comparable<Line>{
        int idx, dist;
        int transfer;

        public Line(int idx, int dist, int transfer) {
            this.idx = idx;
            this.dist = dist;
            this.transfer = transfer;
        }

        @Override
        public int compareTo(Main.Line o) {
            if(this.transfer == o.transfer){
                return this.dist - o.dist;
            }
            return this.transfer - o.transfer;
        }
        
    }
}