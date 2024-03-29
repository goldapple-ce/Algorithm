
import java.io.*;
import java.util.*;

class Main {
	static int[][] dirType = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 } };
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][N];
		
		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < N; col++)
				map[row][col] = Integer.parseInt(st.nextToken());
		}
		List<Position> clouds = new ArrayList<>();
		clouds.add(new Position(N - 2, 0));
		clouds.add(new Position(N - 2, 1));
		clouds.add(new Position(N - 1, 0));
		clouds.add(new Position(N - 1, 1));

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(st.nextToken()) - 1, s = Integer.parseInt(st.nextToken());
			boolean[][] visited=new boolean[N][N];
			for (Position cloud : clouds) {
				cloud.row = (cloud.row + N + (dirType[dir][0] * (s % N))) % N;
				cloud.col = (cloud.col + N + (dirType[dir][1] * (s % N))) % N;

				map[cloud.row][cloud.col] += 1;
				visited[cloud.row][cloud.col] = true;
			}
			for (Position cloud : clouds) {
				int cnt = 0;
				for (int d = 1; d < 8; d+=2) {
					int nRow = cloud.row + dirType[d][0], nCol = cloud.col + dirType[d][1];
					if (inRange(nRow, nCol) && map[nRow][nCol] > 0)
						cnt++;
				}
				map[cloud.row][cloud.col] += cnt;
			}

			List<Position> newClouds = new ArrayList<>();
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					if (!visited[row][col] && map[row][col] > 1) {
						newClouds.add(new Position(row, col));
						map[row][col] -= 2;
					}
				}
			}

			clouds = newClouds;
		}
		int answer = 0;
		for (int row[] : map)
			answer += Arrays.stream(row).sum();

		System.out.println(answer);
	}

	public static boolean inRange(int row, int col) {
		return 0 <= row && row < N && 0 <= col && col < N;
	}

	public static boolean contains(List<Position> clouds, int row, int col) {
		for (int i = 0; i < clouds.size(); i++) {
			if (clouds.get(i).row == row && clouds.get(i).col == col)
				return true;
		}
		return false;
	}
}

class Position {
	int row, col;

	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}

	@Override
	public String toString() {
		return "{row : " + row + ", col : " + col + "}";
	}
}
