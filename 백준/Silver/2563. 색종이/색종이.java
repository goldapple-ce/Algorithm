import java.io.*;
import java.util.*;

class Main {
	static final int MAX_SIZE = 100, SIZE = 10;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] map = new int[MAX_SIZE + 1][MAX_SIZE + 1];
		int N = Integer.parseInt(br.readLine());
		int answer = 0;
		int maxRow = 0, maxCol = 0;
		for (int n = 0; n < N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int col = Integer.parseInt(st.nextToken()), row = Integer.parseInt(st.nextToken());
			map[row][col] += 1;
			map[row + SIZE ][col + SIZE ] += 1;
			map[row + SIZE ][col] -= 1;
			map[row][col + SIZE ] -= 1;
			maxRow = Math.max(maxRow, row+SIZE);
			maxCol = Math.max(maxCol, col+SIZE);
		}

		for (int row = 0; row < maxRow; row++) {
			for (int col = 1; col < maxCol; col++)
				map[row][col] += map[row][col - 1];
		}

		for (int col = 0; col < maxCol; col++) {
			for (int row = 1; row < maxRow; row++)
				map[row][col] += map[row - 1][col];
		}
		
		for (int row = 0; row < maxRow; row++) {
			for (int col = 0; col < maxCol; col++)
				if(map[row][col] > 0) answer++;
		}
		System.out.println(answer);

	}
}
