import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		int n=Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
	
		st = new StringTokenizer(br.readLine());

		int[] sum = new int[n+1];
		for(int i=1;i<=n;i++) {
			int num = Integer.parseInt(st.nextToken());
			sum[i]=sum[i-1]+num;
		}
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			System.out.println(sum[e]-sum[s-1]);
			
		}
		
	}

}
