

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N, M, V;
	static List<List<Integer>> graph;
	static boolean[] visited;
	public static void main(String[] args) throws Exception {
		input();

	}

	private static void input() throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>();
		for(int i=0;i<N+1;i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			graph.get(s).add(e);
			graph.get(e).add(s);
		}
		for(int i=0;i<N+1;i++) {
			Collections.sort(graph.get(i));
		}
		
		visited = new boolean[N+1];
		visited[V]=true;
		sb.append(V+" ");
		dfs(V);
		sb.append("\n");
		
		visited = new boolean[N+1];
		Deque<Integer> dq = new ArrayDeque<>();
		sb.append(V+" ");
		dq.add(V);
		visited[V]=true;
		
		while(!dq.isEmpty()) {
			int now = dq.poll();
			
			for(Integer node: graph.get(now)) {
				if(visited[node]) continue;
				
				visited[node]=true;
				dq.add(node);
				sb.append(node+" ");
			}
		}
		System.out.println(sb);
		
	}

	private static void dfs(int now) {
		for(Integer node: graph.get(now)) {
			if(!visited[node]) {
				visited[node]=true;
				sb.append(node+" ");
				dfs(node);
			}
		}
		
	}

}
