
import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] dp = new int[N + 1][3];

		StringTokenizer st = new StringTokenizer(br.readLine());
		dp[1][0] = Integer.parseInt(st.nextToken());
		dp[1][1] = Integer.parseInt(st.nextToken());
		dp[1][2] = Integer.parseInt(st.nextToken());

		for (int n = 2; n <= N; n++) {
			st = new StringTokenizer(br.readLine());

			dp[n][0] = Math.min(dp[n - 1][1], dp[n - 1][2]) + Integer.parseInt(st.nextToken());
			dp[n][1] = Math.min(dp[n - 1][0], dp[n - 1][2]) + Integer.parseInt(st.nextToken());
			dp[n][2] = Math.min(dp[n - 1][1], dp[n - 1][0]) + Integer.parseInt(st.nextToken());
		}

		System.out.println(Math.min(dp[N][0], Math.min(dp[N][1], dp[N][2])));
	}
}