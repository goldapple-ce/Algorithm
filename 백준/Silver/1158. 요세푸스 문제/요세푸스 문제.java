import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;


/*
 * N: 1-N까지 들어 있는 deque 생성
 * K: K번째 숫자 제거
 * */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Deque<Integer> dq = new ArrayDeque<Integer>();
		
		for(int n=1;n<=N;n++)
			dq.addLast(n);
		
		sb.append("<");
		while(dq.size()>1) {
			
			int cnt=0;
			while(cnt<K-1) {
				int target = dq.pollFirst();
				dq.addLast(target);
				cnt++;
			}
			sb.append(dq.pollFirst()+", ");
		}
		sb.append(dq.pollFirst()+">");
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}
