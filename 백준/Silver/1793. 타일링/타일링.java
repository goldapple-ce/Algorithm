import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

	
	static int N;
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		BigInteger[] dp = new BigInteger[251];
		
		dp[0]= new BigInteger("1");
		dp[1]=new BigInteger("1");
		dp[2]=new BigInteger("3");
		
		for(int i=3;i<251;i++) {
			dp[i] = dp[i-2].multiply(new BigInteger("2"));
			dp[i] = dp[i].add(dp[i-1]);
		}
		
		while(sc.hasNextInt()) {
			int n = sc.nextInt();
			System.out.println(dp[n]);
		}
		
	}

}
