import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, M;
    static List<Road>[] roads;
    static int S, E;
    static int[] dists, inDegree;
    static List<Integer>[] paths;
    static int[] answer = new int[2];

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(S);

        while(!queue.isEmpty()){
            int now = queue.poll();

            for(Road to : roads[now]){
                int nDist = dists[now] + to.dist;

                if(nDist == dists[to.idx]){
                    paths[to.idx].add(now);
                }else if(dists[to.idx] < nDist){
                    dists[to.idx] = nDist;
                    paths[to.idx].clear();
                    paths[to.idx].add(now);
                }
                
                if(--inDegree[to.idx] == 0){
                    queue.offer(to.idx);
                }
            }
        }
        answer[0] = dists[E];

        boolean[] visited = new boolean[N+1];
        queue.offer(E);
        while(!queue.isEmpty()){
            int now = queue.poll();

            for(int to : paths[now]){
                answer[1]++;
                if(!visited[to]){
                    visited[to] = true;
                    queue.offer(to);
                }
            }
        }

    }
    
    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        dists = new int[N+1];
        inDegree = new int[N+1];

        roads = new List[N+1];
        paths = new List[N+1];
        for(int n = 0; n <= N; n++){
            roads[n] = new ArrayList<>();
            paths[n] = new ArrayList<>();
        }

        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            inDegree[to]++;
            roads[from].add(new Road(to, dist));
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
    }
    
    static void print() throws Exception {
        System.out.println(answer[0] + "\n" + answer[1]);
    }

    static class Road {
        int idx, dist;

        public Road(int idx, int dist){
            this.idx = idx;
            this.dist = dist;
        }

        @Override
        public String toString() {
            return "Road [idx=" + idx + ", dist=" + dist + "]";
        }

    }
}