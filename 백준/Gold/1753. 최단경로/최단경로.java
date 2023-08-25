
import java.util.*;
import java.io.*;

public class Main {
	static int V,E;
	static List<Vertex>[] graph;
	static boolean[] visited;
	static int[] dist;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		graph = new ArrayList[V+1];
		visited = new boolean[V+1];
		dist = new int[V+1];
		for (int v = 0; v <= V; v++) {
			dist[v] = Integer.MAX_VALUE;
			graph[v] = new ArrayList<>();
		}
		int K = Integer.parseInt(br.readLine());

		for (int e = 0; e < E; e++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			graph[from].add(new Vertex(to, weight));
		}
		dijkstra(K);
	}
	public static void dijkstra(int K) {
		dist[K] = 0;
		
		PriorityQueue<Vertex> queue = new PriorityQueue<>();
		queue.offer(new Vertex(K,0));
		
		while(!queue.isEmpty()) {
			int now = queue.poll().idx;
			
			if(visited[now]) continue;
			visited[now] = true;
			
			for(Vertex vertex : graph[now]) {
				if(dist[vertex.idx] > dist[now]+ vertex.weight) {
					dist[vertex.idx]= dist[now]+ vertex.weight; 
					queue.offer(new Vertex(vertex.idx,dist[vertex.idx]));
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i < dist.length; i++) {
			int d= dist[i];
			if(d == Integer.MAX_VALUE) sb.append("INF");
			else sb.append(d);
			sb.append('\n');
		}
		System.out.println(sb);
	}
}

class Vertex implements Comparable<Vertex> {
	int idx, weight;

	public Vertex(int to, int weight) {
		this.idx = to;
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Vertex [idx=" + idx + ", weight=" + weight + "]";
	}



	@Override
	public int compareTo(Vertex o) {
		return this.weight - o.weight;
	}

}