import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N,M;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int[][] candy = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				candy[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] delta = { { 0, -1 }, { -1, 0 }, { -1, -1 } }; // 왼, 위, 좌상
		
		int answer[][] = new int[N][M];
		
		answer[0][0] = candy[0][0];
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				
				for(int[] d: delta) {
					int nr =r+d[0];
					int nc = c+d[1];
					if(is_valid(nr,nc)) {
						answer[r][c] = 
								Math.max(answer[r][c], candy[r][c]+answer[nr][nc]);
					}
					
				}
			}
		}
		System.out.println(answer[N-1][M-1]);
		
	}

	private static boolean is_valid(int nr, int nc) {
		return 0<= nr && nr<N && 0<=nc && nc<M;
	}

}
