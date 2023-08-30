
import java.util.*;
import java.io.*;

public class Main {

	static int[][] dp = new int[31][31];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for (int i = 1; i <= 30; i++)
			dp[i][1] = i;

		for (int i = 2; i <= 30; i++) {
			for (int j = 2; j <= 30; j++)
				dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
		}

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());

			sb.append(dp[N][M]).append('\n');
		}
		System.out.println(sb);
	}

}