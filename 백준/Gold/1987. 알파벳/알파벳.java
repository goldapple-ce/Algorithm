
import java.util.*;
import java.io.*;

public class Main {
	static int[][] dirType = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int maxRow, maxCol;
	static char[][] board;
	static int answer = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		maxRow = Integer.parseInt(st.nextToken());
		maxCol = Integer.parseInt(st.nextToken());
		board = new char[maxRow][maxCol];
		for (int row = 0; row < maxRow; row++) {
			String strRow = br.readLine();
			for (int col = 0; col < maxCol; col++) {
				board[row][col] = strRow.charAt(col);
			}
		}
		Set<Character> set = new HashSet<>();
		set.add(board[0][0]);
		dfs(0,0, set);
		System.out.println(answer);
	}

	public static void dfs(int row, int col, Set<Character> set) {
		boolean flag = false;
//		System.out.println("row : "+row+", col : "+col+", set :"+set.toString());
		for (int dir = 0; dir < dirType.length; dir++) {
			int nRow = row + dirType[dir][0];
			int nCol = col + dirType[dir][1];
			if (inRange(nRow, nCol) && !set.contains(board[nRow][nCol])) {
//				Set<Character> nSet = new HashSet<>(set);
				set.add(board[nRow][nCol]);
				flag = true;
				dfs(nRow, nCol, set);
				set.remove(board[nRow][nCol]);
			}
		}
		if (!flag)
			answer = Math.max(answer, set.size());
	}

	public static boolean inRange(int row, int col) {
		return 0 <= row && row < maxRow && 0 <= col && col < maxCol;
	}

}
