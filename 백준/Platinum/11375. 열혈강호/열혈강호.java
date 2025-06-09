import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, M;
    static List<Integer>[] links;
    static int[] tasks;
    static boolean[] visited;
    static int answer;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        for(int n = 1; n <= N; n++){
            Arrays.fill(visited, false);
            if(dfs(n)) answer++;
        }
    }

    static boolean dfs(int now){
        for(int to : links[now]){
            if(visited[to]) continue;
            visited[to] = true;
            if(tasks[to] == 0 || dfs(tasks[to])){
                tasks[to] = now;
                return true;
            }
        }

        return false;
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        links = new List[N+1];
        tasks = new int[M+1];
        visited = new boolean[M+1];

        for(int n = 0; n <= N; n++){
            links[n] = new ArrayList<>();
        }

        for(int n = 1; n <= N; n++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            for(int i = 0; i < num; i++){
                links[n].add(Integer.parseInt(st.nextToken()));
            }
        }
    }
    
    static void print() throws Exception {
        System.out.println(answer);
    }
}