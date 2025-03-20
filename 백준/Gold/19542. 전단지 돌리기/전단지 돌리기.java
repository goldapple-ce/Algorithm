import java.util.*;
import java.io.*;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, S, D;
    static List<Integer>[] roads;
    static boolean[] visited;
    static int answer;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        visited[S] = true;
        dfs(S);
    }

    static int dfs(int now){
        int res = 0;
        
        for(int to : roads[now]){
            if(!visited[to]){
                visited[to] = true;
                res = Math.max(res, dfs(to));
            }
        }

        if(res >= D) answer++;

        return res+1;
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        roads = new List[N+1];
        visited = new boolean[N+1];

        for(int n = 0; n <= N ; n++){
            roads[n] = new ArrayList<>();
        }

        for(int n = 0; n < N-1; n++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            roads[from].add(to);
            roads[to].add(from);
        }
    }
    
    static void print() throws Exception {
        System.out.println(Math.max(0,(answer-1)*2));
    }
}