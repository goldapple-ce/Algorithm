

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int N,M;
	static List<List<Integer>> graph;
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[] degree = new int[N+1];
		
		
		graph = new ArrayList<>();
		for(int i=0;i<N+1;i++)
			graph.add(new ArrayList<>());
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			degree[b]++;
			graph.get(a).add(b);
		}
		
		Deque<Integer> dq = new ArrayDeque<>();
		boolean visited[] = new boolean[N+1];
		List<Integer> answer = new ArrayList<Integer>();
		
		for(int i=1;i<N+1;i++) {
			if(degree[i]==0) {
				dq.add(i);
				visited[i]=true;
				answer.add(i);
			}
		}
		
		while(!dq.isEmpty()) {
			int now = dq.poll();
			sb.append(now+" ");
			
			for(int node: graph.get(now)) {
				if(--degree[node]==0) {
					dq.add(node);
				}
			}
		}
		System.out.println(sb);
		
	}

}
