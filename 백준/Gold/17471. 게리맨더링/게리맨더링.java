import java.util.*;
import java.io.*;

public class Main {
	static int totalPeople = 0;
	static int answer = Integer.MAX_VALUE;
	static int N;
	static List<Integer>[] adjList;
	static int[] people;
	static int[] camps;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		people = new int[N + 1];
		adjList = new ArrayList[N + 1];
		camps = new int[N+1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int n = 1; n <= N; n++) {
			adjList[n] = new ArrayList<>();
			people[n] = Integer.parseInt(st.nextToken());
			totalPeople += people[n];
		}

		for (int n = 1; n <= N; n++) {
			st = new StringTokenizer(br.readLine());
			int adjSize = Integer.parseInt(st.nextToken());
			for (int i = 0; i < adjSize; i++)
				adjList[n].add(Integer.parseInt(st.nextToken()));
		}
		dfs(1);
		System.out.println((answer != Integer.MAX_VALUE) ? answer : -1);
	}

	/**
	 * 진영 나누기
	 * @param now	현재 노드
	 */
	public static void dfs(int now) {
		if(now == N+1) {
			//진영 개수 체크
			boolean[] visited = new boolean[N+1];
			int numOfCamp = 0;	//진영 개수
			for(int area = 1; area <= N; area++) {
				if(!visited[area]) {
					check(area,camps[area],visited);
					numOfCamp++;
				}
			}
			//진영 개수가 2개로 나뉘지 않는다면 인구수를 구할 필요 없음.
			if(numOfCamp != 2) return;
			
			//red, blue 인구수 구하기
			int red = 0,blue = 0;
			for(int i = 1; i <= N;i++) {
				if(camps[i] == 1) red += people[i];
				else blue += people[i];
			}
			answer = Math.min(answer, Math.abs(red-blue));
			return;
		}
		
		//현재 노드가 red진영일 시
		camps[now] = 1;
		dfs(now+1);
		
		//현재 노드가 blue진영일 시
		camps[now] = 2;
		dfs(now+1);
	}

	/**
	 * 진영 체크, root와 같은 진영을 가진 모든 노드를 체크한다.
	 * @param root	진영 시작점
	 * @param camp	진영(red/blue/등등)
	 * @param visited	방문
	 */
	public static void check(int root,int camp,boolean[] visited) {
		Deque<Integer> queue = new ArrayDeque<>();
		queue.offer(root);

		while (!queue.isEmpty()) {
			int now = queue.poll();
			if (visited[now])
				continue;
			visited[now] = true;

			for (int area : adjList[now]) {
				//같은 진영이며, 방문하지 않았다면 넣음.
				if (camps[area] == camp && !visited[area])
					queue.offer(area);
			}
		}
	}
}
