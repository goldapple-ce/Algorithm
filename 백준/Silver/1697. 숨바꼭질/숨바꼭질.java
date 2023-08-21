
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N,K;
	static int answer = Integer.MAX_VALUE;



	public static boolean is_valid(int n) {
		return 0<=n && n<100_001;
	}
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		boolean[] visited = new boolean[100_001];
		
		Deque<int[]> dq = new ArrayDeque<>();
		
		dq.add(new int[] {N,0});
		visited[N]=true;
		
		while(!dq.isEmpty()) {
			int[] now = dq.poll();
			if(now[0]==K) {
				System.out.println(now[1]);
				break;
			}
			
			int num = now[0]+1;
			int cnt = now[1];
			if(is_valid(num)&&!visited[num]) {
				visited[num]=true;
				dq.add(new int[] {num,cnt+1});
			}
			
			num = now[0]-1;
			if(is_valid(num) && !visited[num]) {
				visited[num]=true;
				dq.add(new int[] {num,cnt+1});
			}
			
			num = now[0]*2;
			if(is_valid(num) &&!visited[num]) {
				visited[num]=true;
				dq.add(new int[] {num,cnt+1});
			}
				
		}
		
	}

}
