import java.io.*;
import java.util.*;

class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder().append('<');
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
		List<Integer> list = new ArrayList<>();
		int now = 0;
		for (int n = 1; n <= N; n++) list.add(n);
		
		for (int n = 0; n < N; n++) {
			now = (now+K-1)%list.size();
			sb.append(',').append(' ').append(list.remove(now));
		}
		sb.delete(1,3);
		sb.append('>');
		
		System.out.println(sb);
	}
}
