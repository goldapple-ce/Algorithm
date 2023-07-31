import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int idx=1; idx<=T;idx++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			Deque<int[]> dq = new ArrayDeque<int[]>();
			st = new StringTokenizer(br.readLine());
			int[] priorityList=new int[N];
			
			for(int i=0;i<N;i++) {
				int priority = Integer.parseInt(st.nextToken());
				priorityList[i] = priority;
				dq.addLast(new int[] {priority, i});
			}

			int answer = 1;
			while(!dq.isEmpty()){
				int tempMax = Arrays.stream(priorityList).max().getAsInt();
				
				if(tempMax==dq.peekFirst()[0]) {
					priorityList[dq.peekFirst()[1]]=0;
					if(dq.peekFirst()[1]==M)
						break;
					dq.pollFirst();
					answer++;
				}else {
					dq.addLast(dq.pollFirst());
				}
					
			}
			
			System.out.println(answer);
		}
		
	}
}
