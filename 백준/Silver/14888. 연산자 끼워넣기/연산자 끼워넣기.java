//package cote.baekjoon;

import java.io.*;
import java.util.*;

class Main {
	static int[] operator = new int[4];
	static int N;
	static int answerMax = -1_000_000_001, answerMin = 1_000_000_001;
	static int[] A;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int n = 0; n < N; n++) A[n] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++) operator[i] = Integer.parseInt(st.nextToken());
		dfs(A[0],1);
		System.out.println(answerMax);
		System.out.println(answerMin);
	}
	
	public static void dfs(int sum,int depth) {
//		System.out.println("sum :"+sum+", depth :"+depth);
		if(depth == N) {
			answerMax = Math.max(answerMax, sum);
			answerMin = Math.min(answerMin, sum);
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			if(0 < operator[i]) {
				operator[i]--;
				if(i == 0) dfs(sum+A[depth],depth+1);
				else if(i == 1) dfs(sum-A[depth],depth+1);
				else if(i == 2) dfs(sum*A[depth],depth+1);
				else if(i == 3) dfs(sum/A[depth],depth+1);
				operator[i]++;
			}
		}
	}

}