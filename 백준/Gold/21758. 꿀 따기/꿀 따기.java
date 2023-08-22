import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int N;
	static int nums[];
	static int bucket[];
	static int answer;
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		nums = new int[N];
		bucket = new int[2];
		for(int i=0;i<N;i++)
			nums[i]= Integer.parseInt(st.nextToken());
		
		

		comb(0,0);
		System.out.println(answer);

	}
	private static void comb(int start, int cnt) {
		if(cnt==2) {
			int honey=-1;
			for(int i=0;i<N;i++) {
				if(bucket[0]==i || bucket[1]==i)continue;
				honey = i;
				int result = 0;
				result +=getDist(honey,bucket[0]);
				result +=getDist(honey,bucket[1]);
				answer = Math.max(result, answer);
			}
			return;
		}
		for(int i=start;i<N;i++) {
			bucket[cnt]=i;
			comb(i+1,cnt+1);
			
		}
	}
	
	private static int getDist(int honey, int bee) {
		int sum=0;
		int temp1 = nums[bucket[0]];
		int temp2 = nums[bucket[1]];
		
		nums[bucket[0]] = 0;
		nums[bucket[1]] = 0;
		if(honey<bee) {
			while(--bee!=honey) {
				sum+=nums[bee];
			}
		}else { // bee<honey
			while(++bee!=honey) {
				sum+=nums[bee];
			}	
		}
		
		nums[bucket[0]] =temp1;
		nums[bucket[1]] = temp2;
		return sum+nums[honey];
	}
	
}
