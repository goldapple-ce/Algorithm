
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
		int[][] table = new int[N + 1][N + 1];
		int[][] sections = new int[M][4];
		for (int row = 1; row <= N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 1; col <= N; col++)
				table[row][col] = table[row][col - 1] + Integer.parseInt(st.nextToken());

			for (int col = 1; col <= N; col++)
				table[row][col] += table[row - 1][col];
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			sections[i][0] = Integer.parseInt(st.nextToken()); sections[i][1] = Integer.parseInt(st.nextToken());
			sections[i][2] = Integer.parseInt(st.nextToken()); sections[i][3] = Integer.parseInt(st.nextToken());
		}
		
		for(int[] sec : sections) {
			int sRow = sec[0], sCol = sec[1];
			int eRow = sec[2], eCol = sec[3];
			sb.append(table[eRow][eCol] - table[sRow - 1][eCol] - table[eRow][sCol - 1] + table[sRow - 1][sCol - 1]).append("\n");
		}
		System.out.println(sb);
	}

}
