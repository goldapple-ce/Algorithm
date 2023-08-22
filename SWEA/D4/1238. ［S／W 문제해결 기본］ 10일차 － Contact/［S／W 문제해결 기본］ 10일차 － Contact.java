
import java.util.*;
import java.io.*;

class Solution {

	public static void main(String args[]) throws Exception {
		// System.setIn(new FileInputStream("C:\\SSAFY\\workspace\\02_Java\\cote\\src\\cote\\swea\\input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		StringBuilder sb = new StringBuilder();

		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append('#').append(test_case).append(' ');
			int answer = 0;
			List<Integer>[] adjList = new ArrayList[101];
			for (int i = 0; i <= 100; i++)
				adjList[i] = new ArrayList<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int root = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());

			for (int n = 0; n < N / 2; n++) {
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				if (!adjList[s].contains(e))
					adjList[s].add(e);
			}

			sb.append(bfs(root, adjList)).append('\n');
		}
		System.out.println(sb);
	}

	public static int bfs(int root, List<Integer>[] adjList) {
		Deque<Node> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[101];
		Map<Integer, List<Integer>> dict = new HashMap<>();
		queue.offer(new Node(root, 0));
		int maxDepth = 0;

		while (!queue.isEmpty()) {
			Node now = queue.poll();
			if(visited[now.num]) continue;
			visited[now.num] = true;
			List<Integer> dList = dict.getOrDefault(now.depth, new ArrayList<>());
			dList.add(now.num);
			dict.put(now.depth, dList);
			maxDepth = now.depth;

			for (int node : adjList[now.num]) {
				if (!visited[node])
					queue.offer(new Node(node, now.depth + 1));
			}
		}
		int answer = 0;
		for(int num : dict.get(maxDepth)) 
			answer=Math.max(answer, num);
		return answer;
	}
}

class Node {
	int num;
	int depth;

	public Node(int num, int depth) {
		this.num = num;
		this.depth = depth;
	}

	@Override
	public String toString() {
		return "Node [num=" + num + ", depth=" + depth + "]";
	}

}