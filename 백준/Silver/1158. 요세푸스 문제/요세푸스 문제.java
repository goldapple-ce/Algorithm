import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Deque<Integer> dq = new ArrayDeque<>();
		for(int i=0;i<N;i++)
			dq.add(i+1);
		
		while(!dq.isEmpty()) {
			for (int i = 0; i < K-1; i++) {
				dq.add(dq.poll());
			}
			sb.append(dq.poll()+", ");
		}
		
		sb.delete(sb.length()-2, sb.length());
		System.out.print("<"+sb+">");
	}

}
