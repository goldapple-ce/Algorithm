
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Main {
	static int[][] map;
	static int N,L,R;
	static boolean[][] visited;
	static List<Position> union = new ArrayList<>();
	static int[][] dirType = {{0,1},{0,-1},{1,0},{-1,0}};
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        int answer = 0;
        
        for(int row = 0; row < N; row++) {
        	st = new StringTokenizer(br.readLine());
        	for(int col = 0; col < N; col++) {
        		map[row][col] = Integer.parseInt(st.nextToken());
        	}
        }
        while(answer <= 2000) {
        	visited = new boolean[N][N];
        	boolean flag = false;
	        for(int row = 0; row < N; row++) {
	        	for(int col = 0; col < N; col++) {
	        		if(visited[row][col]) continue;
	        		union.clear();
	        		for(int[] dir : dirType) {
	        			int nRow = row+dir[0], nCol = col+dir[1];
	        			if(inRange(nRow,nCol) && !visited[nRow][nCol] &&diff(row,col,nRow,nCol)) 
	        				dfs(nRow,nCol);
	        		}
	            	if(!union.isEmpty()) {
	            		long sum = 0;
        				int size = union.size();
	            		flag = true;
	            		for(Position pos : union) {
	            			sum += map[pos.row][pos.col];
	            		}
	            		for(Position pos : union) {
	            			map[pos.row][pos.col] = (int) (sum/size);
	            		}
	            	}
	        	}
	        }
	        if(!flag) {
	        	System.out.println(answer);
	        	return;
	        }
        	answer++;
        }
    }
    
    public static void dfs(int row, int col) {
    	visited[row][col] = true;
    	union.add(new Position(row,col));
		for(int[] dir : dirType) {
			int nRow = row+dir[0], nCol = col+dir[1];
			if(inRange(nRow,nCol) && !visited[nRow][nCol] &&diff(row,col,nRow,nCol)) 
				dfs(nRow,nCol);
		}
    }
    
    public static boolean inRange(int row, int col) {
    	return 0 <= row && row < N && 0 <= col && col < N;
    }
    
    public static boolean diff(int row, int col,int nRow, int nCol) {
    	int diff = Math.abs(map[row][col] - map[nRow][nCol]);
    	return L <= diff && diff <= R;
    }

}

class Position{
	int row , col;
	
	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}
}
