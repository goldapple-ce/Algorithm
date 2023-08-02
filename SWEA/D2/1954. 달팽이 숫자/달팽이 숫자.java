import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	public static boolean is_valid_coord(int x, int y) {
		return 0<=x && x<N && 0<=y && y<N;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		// 오, 아래, 왼, 위
		int[][] delta = {{0,1}, {1,0}, {0,-1}, {-1,0}};
		
		for(int idx=1;idx<=T;idx++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			int[][] adj = new int[N][N];
			int i=0,j=0;
			int di=0;
			int cnt =1;

			while(cnt<=N*N) {
				if(is_valid_coord(i,j) && adj[i][j]==0) {
					adj[i][j]=cnt;
					cnt++;
				}else {
					i=i-delta[di][0];
					j=j-delta[di][1];
					di=(di+1)%4;
				}

				i=i+delta[di][0];
				j=j+delta[di][1];
				
				
			}
			System.out.println("#"+idx);
			for(int i1=0;i1<N;i1++) {
				for(int j1 = 0; j1<N;j1++) {
					System.out.print(adj[i1][j1]+" ");
				}
				System.out.println();
			}
			
			
			

		}
	}

}
