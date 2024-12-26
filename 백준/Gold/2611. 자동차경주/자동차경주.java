import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, M;
    static List<Course>[] courses;
    static int[] dists;
    static int[] prev;
    static int[] indegree;
    static Deque<Integer> queue = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        queue.offer(1);

        while(!queue.isEmpty()){
            int now = queue.poll();

            for(Course to : courses[now]){
                int nDist = dists[now] + to.dist;

                if(nDist > dists[to.idx]){
                    dists[to.idx] = nDist;
                    prev[to.idx] = now;
                }

                if(--indegree[to.idx] == 0 && to.idx != 1){
                    queue.offer(to.idx);
                }
            }
        }
    }
    
    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        courses = new List[N+1];
        dists = new int[N+1];
        indegree = new int[N+1];
        prev = new int[N+1];

        for(int n = 0; n <= N; n++){
            courses[n] = new ArrayList<>();
        }

        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            courses[from].add(new Course(to, dist));
            indegree[to]++;
        }
    }
    
    static void print() throws Exception {
        sb.append(dists[1]).append('\n');
        Stack<Integer> stack = new Stack<>();
        int now = 1;
        stack.push(1);
        while(true){
            if(prev[now] == 1){
                stack.push(1);
                break;
            }

            stack.push(prev[now]);
            now = prev[now];
        }

        while(!stack.isEmpty()){
            sb.append(stack.pop()).append(' ');
        }

        System.out.println(sb);
    }

    static class Course {
        int idx, dist;

        public Course(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }

        @Override
        public String toString() {
            return "Course [idx=" + idx + ", dist=" + dist + "]";
        }
    }
}