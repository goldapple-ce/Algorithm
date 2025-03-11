import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, M;
    static List<Integer>[] roads;
    static int[] visited;
    static int answer;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        for(int n = 1; n <= N; n++){
            if(visited[n] == -1 && !bfs(n)){
                return;
            }
        }
        int cnt1 = 0, cnt2 = 0;

        for(int n = 1; n <= N; n++){
            if(visited[n] == 0) cnt1++;
            else cnt2++;
        }
        answer = cnt1 * cnt2 *2;
    }

    static boolean bfs(int start){
        Deque<Position> queue = new ArrayDeque<>();
        queue.offer(new Position(start, 0));
        visited[start] = 0;

        while(!queue.isEmpty()){
            Position now = queue.poll();

            int nColor = 1 - now.color;

            for(int to : roads[now.idx]){
                if(visited[to] == -1){
                    visited[to] = nColor;
                    queue.offer(new Position(to, nColor));
                }else if(visited[to] == now.color){
                    return false;
                }
            }
        }
        return true;
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        roads = new List[N+1];
        visited = new int[N+1];

        for(int n = 0; n <= N; n++){
            roads[n] = new ArrayList<>();
            visited[n] = -1;
        }

        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            roads[from].add(to);
            roads[to].add(from);
        }
    }
    
    static void print() throws Exception {
        System.out.println(answer);
    }

    static class Position {
        int idx, color;

        public Position(int idx, int color) {
            this.idx = idx;
            this.color = color;
        }

        @Override
        public String toString() {
            return "Position [idx=" + idx + ", color=" + color + "]";
        }
        
    }
}