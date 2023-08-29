
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N + 1];
		dp[0] = dp[1] = 0;

		for (int n = 2; n <= N; n++) {
			dp[n] = dp[n - 1] + 1;
			if (n % 2 == 0) {
				dp[n] = Math.min(dp[n], dp[n / 2] + 1);
			}
			if (n % 3 == 0) {
				dp[n] = Math.min(dp[n], dp[n / 3] + 1);
			}
		}
		System.out.println(dp[N]);
	}

}