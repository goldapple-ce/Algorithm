import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[][] d = {{0,1},{1,0},{0,-1},{-1,0}};
	
	public static boolean is_valid_coord(int r, int c) {
		return 0<=r&&r<N &&0<=c&&c<M;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		//세로
		N = Integer.parseInt(st.nextToken());
		//가로
		M = Integer.parseInt(st.nextToken());
		
		int[][] adj = new int[N][M];
		int[][] answer  = new int[N][M];
		for(int i=0;i<N;i++)
			Arrays.fill(answer[i], -1);
		
		int[] start = new int[2];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				adj[i][j] = Integer.parseInt(st.nextToken());
				if(adj[i][j]==2) {
					start = new int[]{i,j};
					adj[i][j]=0;
				}
				if(adj[i][j]==0) {
					answer[i][j]=0;
				}
			}
		}
		
		Deque<int[]> dq = new ArrayDeque<>();
		
		
		dq.add(start);
		while(!dq.isEmpty()) {
			int[] now = dq.pollFirst();
			int r = now[0];
			int c = now[1];
			
			for(int i=0;i<4;i++) {
				int nr = r+d[i][0];
				int nc = c+d[i][1];
				
				if(is_valid_coord(nr,nc) && adj[nr][nc]==1) {
					answer[nr][nc]= answer[r][c]+1;
					adj[nr][nc]=0;
					dq.add(new int[] {nr,nc});
				}
			}
		}
		
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++)
				System.out.print(answer[i][j]+" ");
			System.out.println();
		}
		
		
	}

}