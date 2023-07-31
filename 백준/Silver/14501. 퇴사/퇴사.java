import java.io.*;
import java.util.*;

class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
//		System.setIn(new FileInputStream("C:\\SSAFY\\workspace\\cote\\bin\\cote\\baekjoon\\input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		Work[] scedule = new Work[N];
		for(int n = 0; n < N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			scedule[n] = new Work(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
		int[] dp = new int[N+1];
		for(int n = 0; n < N; n++) {
			Work work = scedule[n];
			if(n>0)dp[n] = Math.max(dp[n], dp[n-1]);
			if(n+work.T <= N) dp[n+work.T] = Math.max(dp[n+work.T], work.P+dp[n]);
		}
		System.out.println(Math.max(dp[N-1], dp[N]));
	}

}

class Work{
	int T, P;
	
	public Work(int T, int P) {
		this.T = T;
		this.P = P;
	}
}