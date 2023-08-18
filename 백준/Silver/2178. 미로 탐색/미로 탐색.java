
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int R,C;
	static int adj[][];
	
	public static void main(String[] args) throws Exception {
		input();
		bfs(0,0);
		System.out.println(adj[R-1][C-1]+1);
	}


	private static void input() throws Exception {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		adj =new int[R][C];
		for(int i=0;i<R;i++) {
			String[] str = br.readLine().split("");
			for(int j=0;j<C;j++) {
				adj[i][j] = Integer.parseInt(str[j]);
			}
		}
		
	}
	
	private static void bfs(int r, int c) {
		Deque<int[]> dq  = new ArrayDeque<>();		
		int[][] delta = {{1,0},{0,1},{-1,0},{0,-1}}; // 하, 우, 상, 좌
		dq.add(new int[] {r,c});
		adj[r][c]=0;
		
		while(!dq.isEmpty()) {
			int[] now = dq.poll();
			
			for(int[] d: delta) {
				int nr = now[0]+d[0];				
				int nc = now[1]+d[1];				
				
				if(nr<0 || nc<0 || nr>=R || nc >=C) continue;
				if(adj[nr][nc]!=1) continue;
				
				adj[nr][nc]  = adj[now[0]][now[1]]+1;
				dq.add(new int[] {nr,nc});
			}
			
		}
		
	}

}
