
import java.io.*;
import java.util.*;

class Main {
	static int[][] dirType = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 } };
	static int[] diagonal = { 1, 3, 5, 7 };
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
			int dir = Integer.parseInt(st.nextToken()) - 1, S = Integer.parseInt(st.nextToken());
			for (int s = 0; s < S; s++) {
				for (Position cloud : clouds) {
					cloud.row = (cloud.row + dirType[dir][0]) % N;
					if (cloud.row < 0)
						cloud.row = N - 1;
					cloud.col = (cloud.col + dirType[dir][1]) % N;
					if (cloud.col < 0)
						cloud.col = N - 1;
				}
			}

//			System.out.println("최종 구름위치 : "+clouds);
			for (Position cloud : clouds)
				map[cloud.row][cloud.col] += 1;

//			System.out.println("구름 비내리기");
//			for(int[] row : map) {
//				System.out.println(Arrays.toString(row));
//			}System.out.println();

			for (Position cloud : clouds) {
				int cnt = 0;
				for (int d : diagonal) {
					int nRow = cloud.row + dirType[d][0], nCol = cloud.col + dirType[d][1];
					if (inRange(nRow, nCol) && map[nRow][nCol] > 0)
						cnt++;
				}
				map[cloud.row][cloud.col] += cnt;
			}

//			System.out.println("주위 물 가져오기");
//			for(int[] row : map) {
//				System.out.println(Arrays.toString(row));
//			}System.out.println();

			List<Position> newClouds = new ArrayList<>();
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					if (!contains(clouds, row, col) && map[row][col] > 1) {
						newClouds.add(new Position(row, col));
						map[row][col] -= 2;
					}
				}
			}

			clouds = newClouds;

//			System.out.println("최종상태");
//			for(int[] row : map) {
//				System.out.println(Arrays.toString(row));
//			}
//			
//			System.out.println(clouds);
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
	public boolean equals(Object o) {
		Position pos = (Position) o;
		return this.row == pos.row && this.col == pos.col;
	}

	@Override
	public String toString() {
		return "{row : " + row + ", col : " + col + "}";
	}
}
