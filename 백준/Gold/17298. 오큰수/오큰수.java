
import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] answer = new int[N];
		Arrays.fill(answer, -1);
		StringTokenizer st = new StringTokenizer(br.readLine());
		Stack<Number> stack = new Stack<>();
		for(int n = 0; n < N; n++) {
			int value = Integer.parseInt(st.nextToken());
			while(!stack.empty() &&stack.peek().value < value) {
				Number number = stack.pop();
				answer[number.idx] = value;
			}
			stack.add(new Number(n,value));
		}
		StringBuilder sb = new StringBuilder();
		for(int value : answer) {
			sb.append(value).append(' ');
		}
		System.out.println(sb);
	}
}

class Number{
	int idx, value;
	
	public Number(int idx, int value) {
		this.idx = idx;
		this.value = value;
	}
	
	public String toString() {
		return "{idx : "+idx+", value :"+value+"}";
	}
}
