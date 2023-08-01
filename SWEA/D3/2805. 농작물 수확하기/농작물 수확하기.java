//package com.ssafy.swea;

import java.io.*;
import java.util.*;

public class Solution {
	static int depth = 1;
	static int[][] map;
	public static void main(String args[]) throws Exception{
		//System.setIn(new FileInputStream("C:\\SSAFY\\workspace\\03_Algorithm\\08-kym-lsh\\src\\com\\ssafy\\swea\\input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++){		
			int N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			int answer = 0;
			for(int row = 0; row < N; row++) {
				String strRow = br.readLine();
				for(int col = 0; col < N; col++) {
					map[row][col] = strRow.charAt(col)-'0';
				}

				if(row <= N/2) {
					answer += dfsR(row,N/2,row);
					answer += dfsL(row,N/2,row);
				}else {
					answer += dfsR(row,N/2,N-row-1);
					answer += dfsL(row,N/2,N-row-1);
				}
				answer -= map[row][N/2];
			}
			System.out.println("#"+test_case+" "+answer);
		}
	}
	
	public static int dfsR(int row, int col, int cnt) {
		if(cnt == 0) return map[row][col];
		return map[row][col] + dfsR(row,col+1,cnt-1);
	}
	
	public static int dfsL(int row, int col, int cnt) {
		if(cnt == 0) return map[row][col];
		return map[row][col] + dfsL(row,col-1,cnt-1);
	}
}

