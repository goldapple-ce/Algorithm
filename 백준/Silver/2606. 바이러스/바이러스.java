import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static int M;
    static int ans = 0;
    static boolean[] visited;
    static List<LinkedList<Integer>> graph;
    
    public static void main(String[] args) throws IOException {
        // input
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        
        
        visited = new boolean[N + 1];
        graph = new LinkedList<>();
        
        for (int i = 0; i < N + 1; i++) {
            graph.add(new LinkedList<>());
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start_node = Integer.parseInt(st.nextToken());
            int end_node = Integer.parseInt(st.nextToken());
            graph.get(start_node).add(end_node);
            graph.get(end_node).add(start_node);
        }
        
        Stack<Integer> s= new Stack<Integer>();
        s.add(1);
        visited[1]=true;
        
        int answer=0;
        while(!s.isEmpty()) {
        	int now = s.pop();
        	
        	for(int i=0;i<graph.get(now).size();i++) {
        		int check = graph.get(now).get(i);
        		if(!visited[check]) {
        			visited[check]=true;
        			answer++;
        			s.add(check);
        			
        		}
        	}
        }
        System.out.println(answer);
    }

}
