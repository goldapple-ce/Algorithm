
import java.io.*;
import java.util.*;

class Main {
	static int[][] map;
	static int[][] dirType = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	static int N, M;
	static boolean[][] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < M; col++)
				map[row][col] = Integer.parseInt(st.nextToken());
		}

		for (int r = 0; r < R; r++) {
			visited = new boolean[N][M];
			for (int i = 0; i < Math.max(1, Math.min(N, M)-2); i++) {
				rotate(i);
			}
		}
		for(int row = 0; row < N; row++) {
			for(int col = 0; col < M; col++)
				sb.append(map[row][col]).append(' ');
			sb.append('\n');
		}
		System.out.println(sb);
	}

	public static boolean inRange(int row, int col) {
		return 0 <= row && row < N && 0 <= col && col < M;
	}

	public static void rotate(int n) {
		Position now = new Position(n, n, map[n][n]);
		for (int j = 0; j < 2*(N-2*n)+2*(M-2*n)-4; j++) {
			for(int i = 0; i < 3; i++) {
				int nDir = (now.dir + i)%4;
				int nRow = now.row + dirType[nDir][0], nCol = now.col + dirType[nDir][1];
				if(inRange(nRow,nCol) && !visited[nRow][nCol]) {
					now.row = nRow; now.col = nCol;
					now.dir = nDir;
					break;
				}
			}
			visited[now.row][now.col] = true;
			int temp = map[now.row][now.col];
			map[now.row][now.col] = now.val;
			now.val = temp;
		}
	}
}

class Position {
	int row, col;
	int dir, val;

	public Position(int row, int col, int val) {
		this.row = row;
		this.col = col;
		this.val = val;
		this.dir = 0;
	}
}