
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		List<Integer>[] students = new ArrayList[N + 1];
		int[] edgeCnt = new int[N + 1];
		for (int n = 0; n <= N; n++)
			students[n] = new ArrayList<>();

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			students[parent].add(child);
			edgeCnt[child]++;
		}

		Deque<Integer> queue = new ArrayDeque<>();
		for (int n = 1; n <= N; n++) {
			if (edgeCnt[n] == 0)
				queue.offer(n);
		}
		
		while(!queue.isEmpty()) {
			int student = queue.poll();
			
			sb.append(student).append(' ');
			List<Integer> childs = students[student];
			
			for(int i = 0;i< childs.size(); i++) {
				int child = childs.get(i);
				edgeCnt[child]--;
				
				if(edgeCnt[child] == 0) queue.offer(child);
			}
//			System.out.println(queue);
		}
		System.out.println(sb);
	}
}
