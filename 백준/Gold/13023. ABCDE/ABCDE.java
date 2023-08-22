import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static List<List<Integer>> graph;
	static boolean visited[];
	static int answer;
	public static void dfs(int now, int cnt) {
		if(cnt==5) {
			answer =1;
			return;
		}
		
		visited[now]=true;
		for(int node: graph.get(now)) {
			if(visited[node]) continue;
			
			dfs(node,cnt+1);
			visited[node]=false;
		}
		
	}
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>();
		for(int i=0;i<N;i++)
			graph.add(new ArrayList<>());
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		
		for(int i=0;i<N;i++) {
			visited= new boolean[N];
			dfs(i,1);
			if(answer==1)break;
		}
		System.out.println(answer);
	}


}
