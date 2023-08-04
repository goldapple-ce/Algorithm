import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] map;
	static ArrayList<Position> house = new ArrayList<>();
	static ArrayList<Position> chicken = new ArrayList<>();
	static int answer = Integer.MAX_VALUE;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < N; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());

				if (map[row][col] == 1) {
					house.add(new Position(row, col));
				} else if (map[row][col] == 2) {
					chicken.add(new Position(row, col));
				}
			}
		}

		visited = new boolean[chicken.size()];

		DFS(0, 0);
		System.out.println(answer);
	}

	public static void DFS(int now, int depth) {
		if (depth == M) {
			int res = 0;

			for (int row = 0; row < house.size(); row++) {
				int temp = Integer.MAX_VALUE;

				for (int col = 0; col < chicken.size(); col++) {
					if (visited[col]) {
						int distance = Math.abs(house.get(row).row - chicken.get(col).row)
								+ Math.abs(house.get(row).col - chicken.get(col).col);
						temp = Math.min(temp, distance);
					}
				}
				res += temp;
			}
			answer = Math.min(answer, res);
			return;
		}

		// 백트래킹
		for (int i = now; i < chicken.size(); i++) {
			visited[i] = true;
			DFS(i + 1, depth + 1);
			visited[i] = false;
		}
	}
}

class Position {
	int row;
	int col;

	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}
}