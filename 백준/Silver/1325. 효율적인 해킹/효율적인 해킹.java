import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
//효율적인 해킹
	static ArrayList<ArrayList<Integer>> graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st= new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>();
		for(int i=0;i<N+1;i++) {
			graph.add(new ArrayList<>());
		}

		
		for(int i=0;i<M;i++) {
			st= new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			graph.get(s).add(e);
		}
		
		int[] count = new int[N+1];
		Deque<Integer> dq = new ArrayDeque<Integer>();
		
		for(int checkNode=1;checkNode<N+1;checkNode++) {
			boolean[] visited= new boolean[N+1];
			
			dq.add(checkNode);
			visited[checkNode]=true;
			count[checkNode]++;
			
			while(!dq.isEmpty()) {
				int now = dq.poll();
				
				for(int node: graph.get(now)) {
					if(!visited[node]) {
						dq.add(node);
						visited[node]=true;
						count[node]++;
					}
				}			
			}
			
			dq.clear();
		}
		
		int maxResult=0;
		for(int i=1;i<N+1;i++) 
			maxResult = Math.max(maxResult, count[i]);
		
		
		for(int i=1;i<N+1;i++) {
			if(maxResult==count[i])
				sb.append(i+" ");
		}
		System.out.println(sb);
	}

}
