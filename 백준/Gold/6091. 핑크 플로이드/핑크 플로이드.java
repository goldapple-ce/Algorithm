import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N;
    static int[] parent;
    static PriorityQueue<Node> queue = new PriorityQueue<>();
    static List<Integer>[] answer;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        while(!queue.isEmpty()){
            Node now = queue.poll();

            if(find(now.from) != find(now.to)){
                // System.out.println(now);
                union(now.from, now.to);
                answer[now.from].add(now.to);
                answer[now.to].add(now.from);
            }
        }
    }

    static int find(int x){
        if(x == parent[x]){
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y){
        x = find(x);
        y = find(y);

        if(x < y){
            parent[y] = x;
        }else{
            parent[x] = y;
        }
    }
    
    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());

        parent = new int[N+1];
        answer = new List[N+1];

        for(int n = 0; n <= N; n++){
            answer[n] = new ArrayList<>();
            parent[n] = n;
        }

        for(int from = 1; from < N; from++){
            st = new StringTokenizer(br.readLine());

            for(int to = from+1; to <= N; to++){
                int dist = Integer.parseInt(st.nextToken());
                queue.offer(new Node(from, to, dist));
                queue.offer(new Node(to, from, dist));
            }
        }
    }
    
    static void print() throws Exception {
        // System.out.println(Arrays.toString(parent));
        
        for(int n = 1; n <= N; n++){
            List<Integer> a = answer[n];
            sb.append(a.size()).append(' ');
            a.sort(null);
            for(int v : a){
                sb.append(v).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    static class Node implements Comparable<Node>{
        int from, to;
        int dist;

        public Node(int from, int to, int dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }

        @Override
        public String toString() {
            return "Node [from=" + from + ", to=" + to + ", dist=" + dist + "]";
        }

        @Override
        public int compareTo(Main.Node o) {
            return this.dist - o.dist;
        }
    }
}