
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[] sw = new int[N+1];
		sw[0]=-1;
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=1;i<N+1;i++) 
			sw[i] = Integer.parseInt(st.nextToken());
		
		
		int nums = Integer.parseInt(br.readLine());
		
		for(int k=0;k<nums;k++) {
			st = new StringTokenizer(br.readLine());
			
			if(1==Integer.parseInt(st.nextToken())) {
				// 남자면
				int n = Integer.parseInt(st.nextToken());
				
				int i=n;
				while(i<(N+1)) {
					sw[i]= (sw[i]==0)? 1:0;
					i+=n;
				}
				
			}else {
				//여자면
				int n = Integer.parseInt(st.nextToken());
				int s = n-1;
				int e = n+1;
				sw[n] = (sw[n]==0)? 1:0;
				
				while(0<=s&&s<N+1 && 0<=e&& e<N+1&&sw[s]==sw[e]) {
					sw[s] = (sw[s]==0)? 1:0;
					sw[e] = (sw[e]==0)? 1:0;
					s--;
					e++;
				}
			}
			
		}
		
		for(int i=1;i<N+1;i++) {
			System.out.print(sw[i]+" ");
			if(i%20==0)
				System.out.println();
		}
		
	}

}
