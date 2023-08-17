
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, adj[][];

	static StringBuilder sb  = new StringBuilder();
	public static void solve(int r, int c, int size) {
		
		// 현재 범위 체크
		int[] cnt= new int[2];
		for (int i = r; i < r + size; i++) {
			for (int j = c; j < c + size; j++) {
				cnt[adj[i][j]]++;
				if(cnt[0]>0 && cnt[1]>0) break;
			}
		}
		if(cnt[0]==size*size) {
			sb.append("0");
		}else if(cnt[1]==size*size) {
			sb.append("1");
		}else {
			sb.append("(");
			int half = size/2;
			// 1사분면
			solve(r,c,half);
			// 2사분면
			solve(r,c+half,half);
			// 3사분면
			solve(r+half, c, half);
			// 4사분면
			solve(r+half, c+half, half);
			sb.append(")");
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		adj = new int[N][N];
		for (int i = 0; i < N; i++) {
			String[] str = br.readLine().split("");
			for (int j = 0; j < N; j++) {
				adj[i][j] = Integer.parseInt(str[j]);
			}
		}

		solve(0, 0, N);
		System.out.println(sb);
	}
}
