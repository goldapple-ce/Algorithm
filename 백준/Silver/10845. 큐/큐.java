import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		Deque<Integer> dq = new ArrayDeque<Integer>();
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			String order = st.nextToken();
			if(order.equals("push")) {
				int n = Integer.parseInt(st.nextToken());
				dq.addLast(n);
			}else {
				if(order.equals("pop")) {
					if(dq.isEmpty()) {
						System.out.println("-1");
					}else {
						System.out.println(dq.pollFirst());
					}
				}else if(order.equals("size")) {
					System.out.println(dq.size());
				}else if(order.equals("empty")) {
					if(dq.isEmpty()) System.out.println("1");
					else System.out.println("0");
				}else if(order.equals("front")) {
					if(dq.isEmpty()) System.out.println("-1");
					else System.out.println(dq.peekFirst());
				}else{
					// back
					if(dq.isEmpty()) System.out.println("-1");
					else System.out.println(dq.peekLast());
				}
				}
		}
	
	
	}

}
