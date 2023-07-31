
import java.io.*;
import java.util.*;

class Main {
	static int[][] dirType = {{-1,0},{0,1},{1,0},{0,-1}};
	static int maxRow, maxCol;
	static int[][] room;
	static int r,c,d;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		maxRow = Integer.parseInt(st.nextToken()); 
		maxCol = Integer.parseInt(st.nextToken());
		room = new int[maxRow][maxCol];
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		
		for(int row = 0; row < maxRow; row++) {
			st = new StringTokenizer(br.readLine());
			for(int col = 0; col < maxCol; col++) {
				room[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(dfs(r,c,d,0));
	}
	
	public static int dfs(int row, int col,int dir,int cnt) {
//		System.out.println("row : "+row+", col : "+col+", dir : ,"+dir+" cnt :"+cnt);

		if(inRange(row,col)&&room[row][col] == 0) {
			room[row][col] = 2;
			cnt++;
		}
//		print();
		for(int i = 1; i <= 4; i++) {
			int nDir = (dir-i >= 0) ?dir-i : 4+dir-i;
			int nRow = row+dirType[nDir][0], nCol = col+dirType[nDir][1];
			if(inRange(nRow,nCol) && room[nRow][nCol] == 0) {
				return dfs(nRow,nCol,nDir,cnt);
			}
		}
		int nRow = row-dirType[dir][0], nCol = col-dirType[dir][1];
		if(inRange(nRow,nCol) && room[nRow][nCol] != 1) return dfs(nRow,nCol,dir,cnt);
		return cnt;
	}
	public static void print() {
		for(int[] row : room) {
			System.out.println(Arrays.toString(row));
		}
	}
	
	public static boolean inRange(int row, int col) {
		return 0 <= row && row < maxRow && 0 <= col && col < maxCol;
	}
}