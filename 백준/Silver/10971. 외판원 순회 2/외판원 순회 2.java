
import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[][] map;
	static int[] dp;
	static long answer = Long.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int row = 0; row < N; row++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int col = 0; col < N; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}

		for (int n = 0; n < N; n++) {
			dfs(n, n, 0L,0, new boolean[N]);
		}
		System.out.println(answer);
	}

	public static void dfs(int start, int now, long cost,int depth, boolean[] visited) {
		if (depth == N-1) {
			if (map[now][start] != 0) {
				answer = Math.min(answer, cost + map[now][start]);
//				System.out.println("answer :" + answer);
			}
		}

		if (visited[now])
			return;
		visited[now] = true;
//		System.out.println(
//				"start : " + start + ", now : " + now + ", cost : " + cost + ", visited :" + Arrays.toString(visited));

		for (int n = 0; n < N; n++) {
			if (!visited[n] && map[now][n] != 0) {
				dfs(start, n, cost + map[now][n],depth+1, visited);
				visited[n] = false;
			}
		}
	}

	public static boolean check(boolean[] visited) {
		for (int n = 0; n < N; n++) {
			if (!visited[n])
				return false;
		}
		return true;
	}
}