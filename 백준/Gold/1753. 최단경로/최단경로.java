

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int V, E, start;
	static List<List<Edge>> graph;
	static int answer[];

	static class Edge implements Comparable<Edge>{
		int w;
		int weight;
		
		public Edge(int w, int weight) {
			super();
			this.w = w;
			this.weight = weight;
		}


		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight,o.weight);
		}


		@Override
		public String toString() {
			return "Edge [w=" + w + ", weight=" + weight + "]";
		}
		
		
	}
	public static void main(String[] args) throws Exception {
		input();
		dijkstra();
		print();
		
	}

	private static void print() {
		for(int i=1;i<V+1;i++) {
			System.out.println(answer[i]==Integer.MAX_VALUE? "INF": answer[i]);
		}
		
	}

	private static void input() throws Exception {
		
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		start  = Integer.parseInt(br.readLine());
		
		answer = new int[V+1];
		graph = new ArrayList<>();
		for(int i=0;i<V+1;i++) {
			graph.add(new ArrayList<>());
			answer[i]= Integer.MAX_VALUE;
		}
		
		for(int i=0;i<E;i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			graph.get(s).add(new Edge(e,weight));
		}
		
	}
	
	private static void dijkstra() {
		boolean visited[] = new boolean[V+1];
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(start,0));
		answer[start] = 0;
		
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			
			if(visited[now.w]) continue;
			
			visited[now.w]=true;
			
			for(Edge e: graph.get(now.w)) {
				if(visited[e.w]) continue;
				if(answer[now.w]+e.weight < answer[e.w]) {
					answer[e.w] = answer[now.w]+e.weight;
					pq.add(new Edge(e.w,answer[e.w]));
				}
			}
		}
	}

}
