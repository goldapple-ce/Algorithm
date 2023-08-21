
import java.util.*;
import java.io.*;

public class Main {
	static int[][] dirType = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int N;
	static char[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int answer1 = 0, answer2 = 0;
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		for (int row = 0; row < N; row++) {
			String strRow = br.readLine();
			for (int col = 0; col < N; col++) {
				map[row][col] = strRow.charAt(col);
			}
		}

		boolean[][] visited = new boolean[N][N];
		boolean[][] RGVisited = new boolean[N][N];
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				if (!visited[row][col]) {
					dfs(row, col, map[row][col], visited);
					answer1++;
				}
			}
		}

		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				if (!RGVisited[row][col]) {
					if (map[row][col] == 'G')
						map[row][col] = 'R';
					dfsRGWeakness(row, col, map[row][col], RGVisited);
					answer2++;
				}
			}
		}

		System.out.println(answer1 + " " + answer2);
	}

	public static void dfsRGWeakness(int row, int col, char color, boolean[][] visited) {
		visited[row][col] = true;

		for (int dir = 0; dir < dirType.length; dir++) {
			int nRow = row + dirType[dir][0];
			int nCol = col + dirType[dir][1];

			if (inRange(nRow, nCol) && !visited[nRow][nCol]) {
				if (map[nRow][nCol] == 'G')
					map[nRow][nCol] = 'R';
				if (map[nRow][nCol] == color)
					dfsRGWeakness(nRow, nCol, color, visited);
			}
		}
	}

	public static void dfs(int row, int col, char color, boolean[][] visited) {
		visited[row][col] = true;

		for (int dir = 0; dir < dirType.length; dir++) {
			int nRow = row + dirType[dir][0];
			int nCol = col + dirType[dir][1];

			if (inRange(nRow, nCol) && !visited[nRow][nCol] && map[nRow][nCol] == color)
				dfs(nRow, nCol, color, visited);
		}
	}

	public static boolean inRange(int row, int col) {
		return 0 <= row && row < N && 0 <= col && col < N;
	}
}
