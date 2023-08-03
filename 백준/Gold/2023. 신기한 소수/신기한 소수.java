import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static ArrayList<Integer> answer;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		answer = new ArrayList<Integer>();
		int number=0;
		int cnt=0;
		dfs(number,cnt);
		for(int a:answer)
			System.out.println(a);
		
	}
	
	public static void dfs(int n, int cnt) {
		if(cnt==N) {
			answer.add(n);
			return;
		}
		
		for(int i=0;i<10;i++) {
			int number = n*10+i;
			
			if(isPrime(number)) {
				dfs(number,cnt+1);
			}
		}
	}
	public static boolean isPrime(int n) {
		if(n<2) return false;
		
		for(int i=2;i<n;i++) {
			if(n%i==0) return false;
		}
		return true;
	}

}
