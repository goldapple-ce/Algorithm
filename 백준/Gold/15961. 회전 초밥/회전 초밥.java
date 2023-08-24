
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[] belt = new int[N];
		int[] counter = new int[D + 1];

		for (int n = 0; n < N; n++)
			belt[n] = Integer.parseInt(br.readLine());

		
		int typeCnt = 0,answer = 0;
		for (int i = 0; i < K; i++) {
			if (counter[belt[i]]++ == 0)
				typeCnt++;
		}
		answer = Math.max(answer, typeCnt + ((counter[C] == 0) ? 1 : 0));

		for (int i = 1; i < N; i++) {

			if (--counter[belt[i - 1]] == 0)
				typeCnt--;
			if (counter[belt[(i - 1 + K) % N]]++ == 0)
				typeCnt++;
			answer = Math.max(answer, typeCnt + ((counter[C] == 0) ? 1 : 0));
			
		}
		System.out.println(answer);
	}
}
