import java.util.*;
import java.io.*;

public class Main {
    static final int INF = 100_000_000;
    static final int SIZE = 3;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, M;
    static int[][] costs, paths;
    static List<Link>[] links;
    static int[] starts = new int[SIZE];
    static int[] answer = new int[2];
    static int pivot;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        for(int s = 0; s < SIZE; s++){
            dijkstra(starts[s], costs[s], paths[s]);
        }
        findPivot();
    }

    static void findPivot(){
        int minSumCost = SIZE * INF;

        for(int n = 1; n <= N; n++){
            int sumCost = costs[0][n] + costs[1][n] + costs[2][n];
            if(sumCost < minSumCost){
                pivot = n;
                minSumCost = sumCost;
            }
        }

        answer[0] = minSumCost;
    }

    static void dijkstra(int s, int[] costs, int[] paths){
        PriorityQueue<Link> queue = new PriorityQueue<>();
        queue.offer(new Link(s, 0));
        costs[s] = 0;

        while(!queue.isEmpty()){
            Link now = queue.poll();

            if(costs[now.idx] < now.cost) 
                continue;

            for(Link to : links[now.idx]){
                int nCost = now.cost + to.cost;

                if(nCost < costs[to.idx]){
                    costs[to.idx] = nCost;
                    paths[to.idx] = now.idx;
                    queue.offer(new Link(to.idx, nCost));
                }
            }
        }
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        costs = new int[SIZE][N+1];
        paths = new int[SIZE][N+1];
        links = new List[N+1];

        for(int i = 0; i < SIZE; i++){
            Arrays.fill(costs[i], INF);
        }

        for(int n = 0; n <= N; n++){
            links[n] = new ArrayList<>();
        }

        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            links[from].add(new Link(to, cost));
            links[to].add(new Link(from, cost));
        }
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < SIZE; i++){
            starts[i] = Integer.parseInt(st.nextToken());
        }
    }
    
    static void print() throws Exception {
        for(int s = 0; s < SIZE; s++){
            int p = pivot;
            while(p != starts[s]){
                sb.append(p + " " + paths[s][p]).append('\n');
                p = paths[s][p];
                answer[1]++;
            }
        }

        sb.insert(0,answer[0] + " " + answer[1]+"\n");
        System.out.println(sb);
    }

    static class Link implements Comparable<Link>{
        int idx,cost;

        public Link(int idx, int cost){
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Link [idx=" + idx + ", cost=" + cost + "]";
        }

        @Override
        public int compareTo(Main.Link o) {
            return this.cost - o.cost;
        }
    }
}