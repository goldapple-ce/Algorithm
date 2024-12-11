import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int V, E, M, S;
    static int mMaxDist, sMaxDist;
    static int[] mDists, sDists;
    static List<Road>[] roads;
    static final int INF = 1_000_000_000;
    static int[] mV, sV;
    static int answer = INF;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        Arrays.fill(mDists, INF);
        Arrays.fill(sDists, INF);

        dijkstra(mDists, mV);
        dijkstra(sDists, sV);
        // System.out.println(Arrays.toString(mDists));
        // System.out.println(Arrays.toString(sDists));
        for(int v = 1; v <= V; v++){
            if(mDists[v] != 0 && sDists[v] != 0 && mDists[v] <= mMaxDist && sDists[v] <= sMaxDist){
                answer = Math.min(answer, mDists[v]+sDists[v]);
            }
        }
    }

    static void dijkstra(int[] dists, int[] sIdxs){
        PriorityQueue<Road> queue = new PriorityQueue<>();
        for(int sIdx : sIdxs){
            queue.offer(new Road(sIdx, 0));
            dists[sIdx] = 0;
        }

        while(!queue.isEmpty()){
            Road now = queue.poll();

            if(dists[now.idx] < now.dist){
                continue;
            }

            for(Road to : roads[now.idx]){
                int nDist = now.dist + to.dist;

                if(dists[to.idx] > nDist){
                    dists[to.idx] = nDist;
                    queue.offer(new Road(to.idx, nDist));
                }
            }
        }

    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        roads = new ArrayList[V+1];
        
        for(int v = 0; v <= V; v++){
            roads[v] = new ArrayList<>();
        }

        for(int e = 0; e < E; e++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            roads[from].add(new Road(to, dist));
            roads[to].add(new Road(from, dist));
        }

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        mMaxDist = Integer.parseInt(st.nextToken());

        mV = new int[M];
        mDists = new int[V+1];

        st = new StringTokenizer(br.readLine());
        for(int m = 0; m < M; m++){
            mV[m] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        sMaxDist = Integer.parseInt(st.nextToken());

        sV = new int[S];
        sDists = new int[V+1];

        st = new StringTokenizer(br.readLine());
        for(int s = 0; s < S; s++){
            sV[s] = Integer.parseInt(st.nextToken());
        }
    }
    
    static void print() throws Exception {
        System.out.println(answer == INF ? -1 : answer);
    }

    static class Road implements Comparable<Road>{
        int idx, dist;

        public Road(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }

        @Override
        public int compareTo(Road o) {
            return this.dist - o.dist;
        }

    }
}