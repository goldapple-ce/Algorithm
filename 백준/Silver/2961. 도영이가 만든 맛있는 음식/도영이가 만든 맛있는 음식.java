import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main{
	static int N;
	static int[][] recipes;
	static int[][] bucket;
	static int answer=Integer.MAX_VALUE;
	
	// 조합
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		recipes = new int[N][2];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int sour = Integer.parseInt(st.nextToken());
			int bitter= Integer.parseInt(st.nextToken());
			recipes[i][0]=sour;
			recipes[i][1]=bitter;
		}
		
		bucket= new int[N][2];
		
		for(int i=1;i<=N;i++) {
			comb(0,i,0);
		}
		System.out.println(answer);
	}
	
	public static void comb(int cnt, int size, int startIdx) {
		
		if(cnt==size) {
			int sourTotal=1;
			int bitterTotal=0;
			
			for(int i=0;i<cnt;i++) {
				sourTotal*=bucket[i][0];
				bitterTotal+=bucket[i][1];
			}
			answer = Math.min(Math.abs(bitterTotal-sourTotal), answer);
			return;
		}
		
		
		for(int i=startIdx;i<N;i++) {
			bucket[cnt]=recipes[i];
			comb(cnt+1,size,i+1);
		}
	}
	

}
