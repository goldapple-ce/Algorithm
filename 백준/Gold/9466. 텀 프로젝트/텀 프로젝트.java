import java.io.*;
import java.util.*;
 
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N;
    static int[] students;
    static boolean[] visited, done;
    static int answer;

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for(int test = 0; test < T; test++){
            input();
            run();
        }
        print();
    }
    
    static void run() throws Exception {
        for(int n = 1; n <= N; n++){
            // System.out.println("start : "+n);
            if(!done[n]){
                dfs(n);
            }
        }
        sb.append(answer).append('\n');
    }

    static void dfs(int now){
        // System.out.println(now);
        // if(done[now])
        //     return;
        if(visited[now]){
            done[now] = true;
            answer--;
        }else{
            visited[now] = true;
        }

        if(!done[students[now]]){
            dfs(students[now]);
        }
        done[now] = true;
        visited[now] = false;
    }
    
    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        students = new int[N+1];
        visited = new boolean[N+1];
        done = new boolean[N+1];
        answer = N;

        st = new StringTokenizer(br.readLine());
        for(int n = 1; n <= N; n++){
            students[n] = Integer.parseInt(st.nextToken());
        }
    }
    
    static void print() throws Exception {
        System.out.println(sb);
    }
}