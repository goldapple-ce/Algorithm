import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int answer;
	static int R, C;
	static int[][] adj;
	static int[][] delta = {{-1,1}, {0,1}, {1,1}}; // 우상, 오른쪽, 우하
	static boolean[][] visited;
	
	static List<int[]> routes;
	static boolean check;
	
	public static boolean is_valid_coord(int r, int c) {
		return 0<=r && r<R && 0<=c && c<C;
	}
	
	public static void dfs(int r, int c ) {
		
		if(check) return;
		if(c==C-1) {
			answer++;
			check=true;
			return;
		}
		
		for(int[] d: delta) {
			int nr = r+d[0];
			int nc = c+d[1];
			
			if(is_valid_coord(nr,nc) && !visited[nr][nc]&& adj[nr][nc]==1) {
				if(check) return;
				
				visited[nr][nc] = true;
				dfs(nr,nc);
			}
		}
		
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		visited = new boolean[R][C];
		
		adj = new int[R][C];
		for(int i=0;i<R;i++) {
			String[] str = br.readLine().split("");
			
			for(int j=0;j<C;j++) {
				String check = str[j];
				if(check.equals(".")) adj[i][j] = 1;
				else adj[i][j] = 0;
			}
		}
		
		
		for(int i=0;i<R;i++) {
			check = false;
			dfs(i,0);
		}
		
		System.out.println(answer);
	}

}
