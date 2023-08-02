import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int M;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
			
		int[] b = new int[M];
		visited = new boolean[N+1];
	
		func(0,1,b);
	}
	public static void func(int depth,int start, int[] bucket) {
		
		if(depth==M) {
			for(int i=0;i<M;i++)
				System.out.print(bucket[i]+" ");
			System.out.println();
			return;
		}
		
		for(int n=start;n<=N;n++) {
			if(!visited[n]) {
				visited[n]=true;
				bucket[depth]=n;
				
				func(depth+1,n+1,bucket);
				
				visited[n]=false;
			}
		}
	}

}
