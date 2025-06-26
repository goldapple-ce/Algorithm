import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, K;
    static List<Road>[] roads;
    static int[] depth;
    static int[][] parent, minDists, maxDists;
    static int H;
    static int[][] quries;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        findParent(1, 0, 1);
        fillParent();
        for(int[] query : quries){
            int[] res = lca(query[0],query[1]);
            sb.append(res[0] + " " + res[1]).append('\n');
        }
    }

    static int[] lca(int x, int y){
        int minDist = 1_000_000_000, maxDist = -1;

        if(depth[x] > depth[y]){
            int tmp = x;
            x = y;
            y = tmp;
        }

        for(int h = H-1; h >= 0; h--){
            if(Math.pow(2, h) <= depth[y] - depth[x]){
                minDist = Math.min(minDist, minDists[y][h]);
                maxDist = Math.max(maxDist, maxDists[y][h]);
                y = parent[y][h];
            }
        }

        if(x == y) return new int[]{minDist, maxDist};

        for(int h = H-1; h >= 0; h--){
            if(parent[x][h] != parent[y][h]){
                minDist = Math.min(minDist, Math.min(minDists[x][h], minDists[y][h]));
                maxDist = Math.max(maxDist, Math.max(maxDists[x][h], maxDists[y][h]));

                x = parent[x][h];
                y = parent[y][h];
            }
        }

        minDist = Math.min(minDist, Math.min(minDists[x][0], minDists[y][0]));
        maxDist = Math.max(maxDist, Math.max(maxDists[x][0], maxDists[y][0]));
        return new int[]{minDist,maxDist};
    }


    static void fillParent(){
        for(int h = 1; h < H; h++){
            for(int n = 1; n <= N; n++){
                parent[n][h] = parent[parent[n][h-1]][h-1];
                maxDists[n][h] = Math.max(maxDists[n][h-1], maxDists[parent[n][h-1]][h-1]);
                minDists[n][h] = Math.min(minDists[n][h-1], minDists[parent[n][h-1]][h-1]);
            }
        }
    }

    static void findParent(int now, int par, int h){
        depth[now] = h;
        
        for(Road to : roads[now]){
            int toIdx = to.idx;
            if(toIdx != par){
                findParent(toIdx, now, h+1);
                parent[toIdx][0] = now;
                minDists[toIdx][0] = maxDists[toIdx][0] = to.dist;
            }
        }
    }
    
    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());

        H = (int) (Math.ceil(Math.log(N)/Math.log(2))) + 1;
        parent = new int[N+1][H];
        minDists = new int[N+1][H];
        maxDists = new int[N+1][H];
        depth = new int[N+1];
        roads = new List[N+1];

        for(int n = 0; n <= N; n++){
            roads[n] = new ArrayList<>();
        }

        for(int n = 0; n < N-1; n++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            roads[from].add(new Road(to, dist));
            roads[to].add(new Road(from, dist));
        }

        K = Integer.parseInt(br.readLine());
        quries = new int[K][2];
        for(int k = 0; k < K; k++){
            st = new StringTokenizer(br.readLine());

            quries[k][0] = Integer.parseInt(st.nextToken());
            quries[k][1] = Integer.parseInt(st.nextToken());
        }
    }
    
    static void print() throws Exception {
        System.out.println(sb);
    }

    static class Road {
        int idx, dist;

        public Road(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }

        @Override
        public String toString() {
            return "Road [idx=" + idx + ", dist=" + dist + "]";
        }
    }
}