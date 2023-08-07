
import java.io.*;
import java.util.*;

class Main {
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		int[] tops = new int[N], answer = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int n = 0; n < N; n++) tops[n] = Integer.parseInt(st.nextToken()); 

		Stack<Top> stack = new Stack<>();
		
		for(int i = N-1; i >= 0; i--) {				
			while(!stack.isEmpty() && stack.peek().height <= tops[i]) {
				Top top = stack.pop();
				answer[top.idx] = i+1;
			}
			stack.add(new Top(i,tops[i]));	
		}
		for(int i = 0; i < N; i++) sb.append(answer[i]).append(" ");
		System.out.println(sb);
	}
}

class Top{
	int idx, height;
	
	public Top(int idx, int height) {
		this.idx = idx;
		this.height = height;
	}
	
	public String toString() {
		return "{ idx : "+idx+", heigth : "+height+"}";
	}
}
