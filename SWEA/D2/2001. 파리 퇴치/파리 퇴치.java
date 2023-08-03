import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int M;
	static int[][] adj;
	public static int cnt(int r, int c) {
		int result = 0;
		for(int i=r;i<r+M;i++) {
			for(int j=c;j<c+M;j++) {
				result+=adj[i][j];
			}
		}
		return result;
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for (int idx = 1; idx <= T; idx++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			adj = new int [N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					adj[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
//			입출력 끝
			
			int answer = 0;
			for(int i=0;i<N-M+1;i++) {
				for(int j=0;j<N-M+1;j++) {
					
					answer = Math.max(answer, cnt(i,j));
				}
			}
			System.out.println("#"+idx+" "+answer);
		}
	}

}
