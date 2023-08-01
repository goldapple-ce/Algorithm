import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		Deque<int[]> dq = new ArrayDeque<int[]>();
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) {
			int n = Integer.parseInt(st.nextToken());
			dq.addLast(new int[] {n,i});
		}
		
//			방법1.
//			int[] result = dq.peekFirst();
		int cnt = dq.peekFirst()[0];
		int idx = dq.pollFirst()[1];		
		
		sb.append(idx+" ");
		while(!dq.isEmpty()) {
			

			
			if(cnt>0) {
				// 앞에꺼 빼서 뒤로 addLast(pollFirst)
				for(int i=0;i<cnt-1;i++) {
					dq.addLast(dq.pollFirst());
				}
				cnt = dq.peekFirst()[0];
				idx = dq.pollFirst()[1];
				
			}else {
				// 뒤에꺼 빼서 앞으로 addFirst(pollLast)
				for(int i=cnt; i<-1;i++) {
					dq.addFirst(dq.pollLast());
				}
				cnt = dq.peekLast()[0];
				idx = dq.pollLast()[1];
			}
			sb.append(idx+" ");
		}
		System.out.println(sb);
		
		
	}

}
