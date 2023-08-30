
import java.util.*;
import java.io.*;

public class Main {
	static int K, maxRow, maxCol;
	static int[][][] dirType = { { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } },
			{ { -2, 1 }, { -1, 2 }, { 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 }, { -1, -2 }, { -2, -1 } } };
	static final int MAX_VALUE = 400_000;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		maxCol = Integer.parseInt(st.nextToken());
		maxRow = Integer.parseInt(st.nextToken());

		map = new int[maxRow][maxCol];

		for (int row = 0; row < maxRow; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < maxCol; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(bfs(0, 0));
	}

	public static boolean inRange(int row, int col) {
		return 0 <= row && row < maxRow && 0 <= col && col < maxCol;
	}

	public static int bfs(int r, int c) {
		Deque<Position> queue = new ArrayDeque<>();
		boolean[][][] visited = new boolean[K + 1][maxRow][maxCol];
		visited[0][r][c] = true;
		queue.offer(new Position(r, c));

		while (!queue.isEmpty()) {
			Position now = queue.poll();

			if (now.row == maxRow - 1 && now.col == maxCol - 1)
				return now.move;

			for (int i = 0; i < 2; i++) {
				if (i == 1 && now.chance == K)
					continue;
				for (int dir = 0; dir < dirType[i].length; dir++) {
					int nRow = now.row + dirType[i][dir][0];
					int nCol = now.col + dirType[i][dir][1];

					if (inRange(nRow, nCol) && !visited[now.chance + i][nRow][nCol] && map[nRow][nCol] != 1) {
						visited[now.chance + i][nRow][nCol] = true;
						queue.offer(new Position(nRow, nCol, now.chance + i, now.move + 1));
					}
				}
			}
		}
		return -1;
	}

}

class Position {
	int row, col;
	int chance, move;

	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public Position(int row, int col, int chance, int move) {
		this.row = row;
		this.col = col;
		this.chance = chance;
		this.move = move;
	}

	@Override
	public String toString() {
		return "Position [row=" + row + ", col=" + col + "]";
	}
}