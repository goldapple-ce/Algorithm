import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][] delta = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	static int N;
	static int M;
	
	public static boolean is_valid_coord(int r, int c) {
		return 0<=r && r<N && 0<=c && c<M;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());

		int[][] adj = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				adj[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int j=0;j<R;j++) {
			
		
		int r = 0, c = 0;
		int d = 0;
		boolean[][] visited= new boolean[N][M];
			
		for (int i = 0; i < (Math.min(N,M) / 2); i++) {
			r = i;
			c = i;
			
			int[]tmp = new int[2];
			tmp[0]=adj[i][i];
			
			int cnt = 2 * ((N - 2 * i) + (M - 2 * i)) - 4;
			
			while(cnt>0){
				
				int nr = r + delta[d][0];
				int nc = c + delta[d][1];
				
				if (is_valid_coord(nr, nc) && !visited[nr][nc]) {
					visited[nr][nc] = true;
					tmp[1] = adj[nr][nc];
					adj[nr][nc]=tmp[0];
					tmp[0] = tmp[1];
					cnt--;		
				} else {
					nr = nr -delta[d][0];
					nc = nc -delta[d][1];
					d = (d + 1) % 4;
				}
				r = nr;
				c = nc;
			}
		}
		}
		
		for (int i = 0; i < N; i++) {
			for(int j=0;j<M;j++)
				System.out.print(adj[i][j]+" ");
			System.out.println();
			
		}

	}

}
