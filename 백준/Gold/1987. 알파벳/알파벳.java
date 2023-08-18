
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

//DFS
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int R, C;
	static int adj[][];
	static Map<Integer, Boolean> visited;
	static int answer;
	static int d[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 상 하 좌 우

	public static void main(String[] args) throws Exception {
		input();
		visited.put(adj[0][0], true);
		dfs(0, 0, 1);
		print();
	}

	private static void input() throws Exception {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		adj = new int[R][C];
		visited = new HashMap<>();
		
		for (int i = 0; i <= 'Z' - 'A'; i++) 
			visited.put(i, false);
		

		for (int i = 0; i < R; i++) {
			String[] str = br.readLine().split("");
			for (int j = 0; j < C; j++)
				adj[i][j] =(int)(str[j].charAt(0)-'A');
		}

	}

	private static void dfs(int r, int c, int cnt) {
		answer = Math.max(answer, cnt);

		for (int i = 0; i < 4; i++) {
			int nr = r + d[i][0];
			int nc = c + d[i][1];

			if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
			if (visited.get(adj[nr][nc])) continue;

			visited.put(adj[nr][nc], true);
			dfs(nr, nc, cnt + 1);
			visited.put(adj[nr][nc], false);

		}

	}

	private static void print() {
		sb.append(answer);
		System.out.println(sb);
	}

}
