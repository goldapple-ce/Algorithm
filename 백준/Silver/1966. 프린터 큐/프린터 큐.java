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
			
			Deque<Doc> dq = new ArrayDeque<Doc>();
			st = new StringTokenizer(br.readLine());
			int[] priorityList=new int[N];
			
			for(int i=0;i<N;i++) {
				int priority = Integer.parseInt(st.nextToken());
				priorityList[i] = priority;
				Doc doc = new Doc(priority,i);
				dq.addLast(doc);
			}

			int answer = 1;
			while(!dq.isEmpty()){
				int tempMax = Arrays.stream(priorityList).max().getAsInt();
				
				if(tempMax==dq.peekFirst().priority) {
					priorityList[dq.peekFirst().index]=0;
					if(dq.peekFirst().index==M)
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
	
	static class Doc{
		int priority;
		int index;
		
		public Doc(int priority, int index) {
			super();
			this.priority = priority;
			this.index = index;
		}
		
		@Override
		public String toString() {
			return "Print [priority=" + priority + ", index=" + index + "]";
		}

		
	}

}
