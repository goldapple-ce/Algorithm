
import java.util.*;
import java.io.*;

public class Main {
	static int maxRow, maxCol;
	static int[][] map;
	static int[][][] dirType = { { { 0 } }, // 0번
			{ { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } }, // 1번
			{ { 0, 1, 0, -1 }, { 1, 0, -1, 0 } }, // 2번
			{ { -1, 0, 0, 1 }, { 0, 1, 1, 0 }, { 1, 0, 0, -1 }, { 0, -1, -1, 0 } }, // 3번
			{ { 0, -1, -1, 0, 0, 1 }, { -1, 0, 0, 1, 1, 0 }, { 0, -1, 0, 1, 1, 0 }, { 0, -1, -1, 0, 1, 0 } }, // 4번
			{ { 0, -1, -1, 0, 0, 1, 1, 0 } } // 5번
	};
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		maxRow = Integer.parseInt(st.nextToken());
		maxCol = Integer.parseInt(st.nextToken());
		map = new int[maxRow][maxCol];
		List<Position> cctvs = new ArrayList<>();
		for (int row = 0; row < maxRow; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < maxCol; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
				if (map[row][col] != 0 && map[row][col] != 6)
					cctvs.add(new Position(row, col, map[row][col]));
			}
		}

		dfs(0, cctvs);
		System.out.println(answer);
	}

	public static void dfs(int index, List<Position> cctvs) {
		if (index == cctvs.size()) {
			answer = Math.min(answer, activateCctv(cctvs));
			return;
		}

		for (int dir = 0; dir < dirType[cctvs.get(index).type].length; dir++) {
			cctvs.get(index).dir = dir;
			dfs(index + 1, cctvs);
		}
	}

	public static int activateCctv(List<Position> cctvs) {
		int answer = 0;
		int[][] copyMap = new int[maxRow][maxCol];
		for (int row = 0; row < maxRow; row++)
			copyMap[row] = Arrays.copyOf(map[row], maxCol);
		for (Position cctv : cctvs) {
			int row = cctv.row, col = cctv.col, dir = cctv.dir, type = cctv.type;

			if (type == 1) {
				while (inRange(row, col) && copyMap[row][col] != 6) {
					if (copyMap[row][col] == 0)
						copyMap[row][col] = -1;
					row += dirType[type][dir][0];
					col += dirType[type][dir][1];
				}
			} else if (type == 2 || type == 3) {
				for (int i = 0; i <= 2; i += 2) {
					int copyRow = row, copyCol = col;
					while (inRange(copyRow, copyCol) && copyMap[copyRow][copyCol] != 6) {
						if (copyMap[copyRow][copyCol] == 0)
							copyMap[copyRow][copyCol] = -1;
						copyRow += dirType[type][dir][i];
						copyCol += dirType[type][dir][1 + i];
					}
				}

			} else if (type == 4) {
				for (int i = 0; i <= 4; i += 2) {
					int copyRow = row, copyCol = col;
					while (inRange(copyRow, copyCol) && copyMap[copyRow][copyCol] != 6) {
						if (copyMap[copyRow][copyCol] == 0)
							copyMap[copyRow][copyCol] = -1;
						copyRow += dirType[type][dir][i];
						copyCol += dirType[type][dir][1 + i];
					}
				}
			} else if (type == 5) {
				for (int i = 0; i <= 6; i += 2) {
					int copyRow = row, copyCol = col;
					while (inRange(copyRow, copyCol) && copyMap[copyRow][copyCol] != 6) {
						if (copyMap[copyRow][copyCol] == 0)
							copyMap[copyRow][copyCol] = -1;
						copyRow += dirType[type][dir][i];
						copyCol += dirType[type][dir][1 + i];
						
					}
				}
			}
		}
		for (int row = 0; row < maxRow; row++) {
			for (int col = 0; col < maxCol; col++) {
				if (copyMap[row][col] == 0)
					answer++;
			}
		}
		return answer;
	}

	public static boolean inRange(int row, int col) {
		return 0 <= row && row < maxRow && 0 <= col && col < maxCol;
	}
}

class Position {
	int row, col;
	int type, dir;

	public Position(int row, int col, int type) {
		this.row = row;
		this.col = col;
		this.type = type;
	}

	@Override
	public String toString() {
		return "Position [row=" + row + ", col=" + col + ", type=" + type + ", dir=" + dir + "]";
	}
}
