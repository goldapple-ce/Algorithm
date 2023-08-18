
import java.util.*;
import java.io.*;

public class Main {
	static int maxRow, maxCol;
	static int[][] dirType = { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		maxRow = Integer.parseInt(st.nextToken());
		maxCol = Integer.parseInt(st.nextToken());
		boolean[][] map = new boolean[maxRow][maxCol];
		for (int row = 0; row < maxRow; row++) {
			String strRow = br.readLine();
			for (int col = 0; col < maxCol; col++)
				map[row][col] = strRow.charAt(col) == '1';
		}
		System.out.println(findRoad(new Position(0, 0, 0), map)+1);
	}

	public static int findRoad(Position start, boolean[][] map) {
		Deque<Position> queue = new ArrayDeque<>();
		queue.offer(start);
		map[start.row][start.col] = false;

		while (!queue.isEmpty()) {
			Position now = queue.poll();
			if (now.row == maxRow - 1 && now.col == maxCol - 1) 
				return now.len;
			
			
			for (int dir = 0; dir < dirType.length; dir++) {
				int nRow = now.row + dirType[dir][0];
				int nCol = now.col + dirType[dir][1];

				if (inRange(nRow, nCol) && map[nRow][nCol]) {
					map[nRow][nCol] = false;
					queue.offer(new Position(nRow, nCol, now.len + 1));
				}
			}
		}
		return 0;
	}

	public static boolean inRange(int row, int col) {
		return 0 <= row && row < maxRow && 0 <= col && col < maxCol;
	}
}

class Position {
	int row, col;
	int len;

	public Position(int row, int col, int len) {
		this.row = row;
		this.col = col;
		this.len = len;
	}

	@Override
	public String toString() {
		return "Position [row=" + row + ", col=" + col + ", len=" + len + "]";
	}

}