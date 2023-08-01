import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static boolean is_valid_coord(int x, int y) {
		return 0<=x && x<100 && 0<=y && y<100;
	}
	public static int check(int[][] arr, int y) {
		
		// 우, 좌, 위
		int[][] d = {{0,1}, {0,-1},{-1,0}};
		int x=99;
		
		
		while(x>0) {
			arr[x][y]=0;
			for(int i=0;i<d.length;i++) {
				int nx = x+d[i][0];
				int ny = y+d[i][1];
				
				if(is_valid_coord(nx,ny) && arr[nx][ny]==1) {
					x=nx;
					y=ny;
					break;
				}
			}
		}

		
		return y;
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[][] adj = new int[100][100];
		
		for(int t=1;t<=10;t++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			
			
			for(int i=0;i<100;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<100;j++) {
					adj[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int resultY = -1;
			for(int j=0;j<100;j++) {
				if(adj[99][j]==2) {
					resultY=j;
					break;
				}
			}
			System.out.println("#"+idx+" "+check(adj,resultY));
			
			
			
			
		}
	}

}
