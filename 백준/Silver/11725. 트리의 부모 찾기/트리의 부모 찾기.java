import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static boolean[] visited;
	static int[] answer;
	static ArrayList<ArrayList<Integer>> graph;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());

		graph = new ArrayList<>();
		for (int i = 0; i < N + 1; i++) {
			graph.add(new ArrayList<>());
		}

		int n1 = 0;
		int n2 = 0;
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			n1 = Integer.parseInt(st.nextToken());
			n2 = Integer.parseInt(st.nextToken());

			graph.get(n1).add(n2);
			graph.get(n2).add(n1);
		}

		Deque<Integer> dq = new ArrayDeque<Integer>();
		visited  = new boolean[N + 1];
		answer = new int[N + 1];
		dq.add(1);
		visited[1] = true;

		while (!dq.isEmpty()) {
			int now = dq.pollFirst();
			
			for(int node: graph.get(now)) {
				if(!visited[node]) {
					visited[node]=true;
					dq.addLast(node);
					answer[node]=now;
				}
			}
		}
		
		for (int i = 2; i < N + 1; i++)
			sb.append(answer[i]+"\n");
		System.out.println(sb);
		
		br.close();
	}

}
