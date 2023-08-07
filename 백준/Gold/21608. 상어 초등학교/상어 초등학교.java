
import java.io.*;
import java.util.*;

class Main {
	static int N;
	static int[][] likes, map;
	static int[][] dirType = {{-1,0},{0,1},{1,0},{0,-1}};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int answer = 0;
		
		N = Integer.parseInt(br.readLine());
		likes = new int[N*N+1][4];
		map = new int[N][N];
		for(int n = 0; n < N*N; n++) {
			st = new StringTokenizer(br.readLine());
			int student = Integer.parseInt(st.nextToken());
			for(int i = 0; i < 4; i++) likes[student][i] = Integer.parseInt(st.nextToken());
			
			Seat seat = findSeat(student);
			map[seat.row][seat.col] = student;
		}
		for(int row = 0; row < N; row++) {
			for(int col = 0; col < N; col++) {
				int value = getStudentSum(row,col,map[row][col]);
				if(value >0) answer+= Math.pow(10, value-1);
			}
		}
		System.out.println(answer);
		
	}
	
	public static Seat findSeat(int student) {
		Seat seat = null;
		for(int row = 0; row < N; row++) {
			for(int col = 0; col < N; col++) {
				if(map[row][col] != 0) continue;
				
				Seat cur = new Seat(row,col, getStudentSum(row,col,student),getEmptySum(row,col));
				if(seat == null) seat = cur;
				else if(seat.compareTo(cur) > 0) seat = cur;
			}
		}
		return seat;
	}
	
	public static int getStudentSum(int row, int col, int student) {
		int cnt = 0;
		int[] like = likes[student];
		
		for(int[] dir: dirType) {
			int nRow = row + dir[0];
			int nCol = col + dir[1];
			
			if(!inRange(nRow, nCol)) continue;
			for(int l : like) {
				if(map[nRow][nCol] == l) cnt++;
			}
		}
		return cnt;
	}
	
	public static int getEmptySum(int row, int col) {
		int cnt = 0;
		for(int[] dir : dirType) {
			int nRow = row+dir[0];
			int nCol = col + dir[1];
			
			if(inRange(nRow,nCol) && map[nRow][nCol] == 0) cnt++;
		}
		
		return cnt;
	}
	
	public static boolean inRange(int row, int col) {
		return 0 <= row && row < N && 0 <= col && col < N;
	}
}

class Seat implements Comparable<Seat>{
	int row, col;
	int aroundLike, aroundEmpty;
	
	public Seat(int row, int col, int aroundLike, int aroundEmpty) {
		this.row = row;
		this.col = col;
		this.aroundEmpty = aroundEmpty;
		this.aroundLike = aroundLike;
	}
	
	@Override
	public int compareTo(Seat other) {
		if(aroundLike != other.aroundLike) return other.aroundLike - aroundLike;
		if(aroundEmpty != other.aroundEmpty) return other.aroundEmpty - aroundEmpty;
		if(row != other.row) return row - other.row;
		return col - other.col;
	}
	
	@Override
	public String toString() {
		return "{row :"+row+", col :"+col+", aroundLike :"+aroundLike+", aroundEmpty :"+aroundEmpty+"}";
	}
}
