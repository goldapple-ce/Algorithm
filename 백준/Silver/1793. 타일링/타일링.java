
import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		BigInteger[] dp = new BigInteger[251];
		dp[0] = new BigInteger("1");
		dp[1] = new BigInteger("1");
		dp[2] = new BigInteger("3");

		for (int n = 3; n <= 250; n++) {
			dp[n] = dp[n-2].multiply(new BigInteger("2"));
			dp[n] = dp[n].add(dp[n-1]);
		}

		StringBuilder sb = new StringBuilder();
		while(sc.hasNextInt()) {
			int n = sc.nextInt();
			sb.append(dp[n]).append('\n');
		}
		System.out.println(sb);
	}
}