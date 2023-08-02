
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
		int[] arr = new int[N+1];
		int[][] sections = new int[M][2];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++)
			arr[i] = arr[i-1] + Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			sections[i][0] = Integer.parseInt(st.nextToken())-1;
			sections[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for(int[] sec : sections)
			sb.append(arr[sec[1]]-arr[sec[0]]).append("\n");
		System.out.println(sb);
	}

}
