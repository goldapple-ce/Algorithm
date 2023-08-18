
import java.util.*;
import java.io.*;

public class Main {
	static int maxRow, maxCol, D;
	static int[][] dirType = { { 0, -1 }, { -1, 0 }, { 0, 1 } };
	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		maxRow = Integer.parseInt(st.nextToken());
		maxCol = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		int[][] originMap = new int[maxRow][maxCol];
		for (int row = 0; row < maxRow; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < maxCol; col++) {
				originMap[row][col] = Integer.parseInt(st.nextToken());
			}
		}
//		printMap(originMap);
		combination(maxCol, originMap);
		System.out.println(answer);
	}

	public static void combination(int N, int[][] originMap) {
		for (int i = 0; i < N - 2; i++) {
			for (int j = i + 1; j < N - 1; j++) {
				for (int k = j + 1; k < N; k++) {
//					System.out.println("i : " + i + ", j : " + j + ", k :" + k);
					startGame(new Position[] { new Position(maxRow - 1, i, 0), new Position(maxRow - 1, j, 0),
							new Position(maxRow - 1, k, 0) }, originMap);
				}
			}
		}
	}

	public static void startGame(Position[] archers, int[][] originMap) {
		Set<Position> set = new HashSet<>();
		int kill = 0;
		int[][] map = new int[maxRow][maxCol];
		for (int row = 0; row < maxRow; row++) {
			map[row] = Arrays.copyOf(originMap[row], originMap[row].length);
		}
		for (int level = 0; level < maxRow; level++) {
			set.clear();
//			System.out.println("level :" + level);
			for (int i = 0; i < archers.length; i++) {
				Position enemy = findEnemy(archers[i], map);
				if (enemy.depth != -1)
					set.add(enemy);
			}
			kill += set.size();
//			System.out.println(set);
			for (Position enemy : set)
				map[enemy.row][enemy.col] = 0;
//			printMap(map);
			nextStage(map);
//			printMap(map);
//			System.out.println(kill);
		}

		answer = Math.max(answer, kill);
//		System.out.println(answer);
	}

	public static void printMap(int[][] map) {
		for (int[] row : map) {
			System.out.println(Arrays.toString(row));
		}
		System.out.println();
	}

	public static Position findEnemy(Position archer, int[][] map) {
		boolean[][] visited = new boolean[maxRow][maxCol];
		Deque<Position> queue = new ArrayDeque<>();
		queue.offer(archer);
		while (!queue.isEmpty()) {
			Position pos = queue.poll();

			if (pos.depth == D)
				continue;
			if (map[pos.row][pos.col] == 1)
				return pos;
			visited[pos.row][pos.col] = true;

			for (int dir = 0; dir < dirType.length; dir++) {
				int nRow = pos.row + dirType[dir][0];
				int nCol = pos.col + dirType[dir][1];

				if (inRange(nRow, nCol) && !visited[nRow][nCol]) {
					queue.offer(new Position(nRow, nCol, pos.depth + 1));
				}
			}
		}

		return new Position(-1, -1, -1);
	}

	public static void nextStage(int[][] map) {
		for (int row = maxRow - 1; row > 0; row--)
			map[row] = Arrays.copyOf(map[row - 1], map[row - 1].length);
		Arrays.fill(map[0], 0);
	}

	public static boolean inRange(int row, int col) {
		return 0 <= row && row < maxRow && 0 <= col && col < maxCol;
	}

}

class Position {
	int row, col;
	int depth;

	@Override
	public String toString() {
		return "Position [row=" + row + ", col=" + col + ", depth=" + depth + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		result = prime * result + row;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (col != other.col)
			return false;
		if (row != other.row)
			return false;
		return true;
	}

	public Position(int row, int col, int depth) {
		this.row = row;
		this.col = col;
		this.depth = depth;
	}

}