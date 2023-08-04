import java.io.*;
import java.util.*;

class Main {
	static LinkedList<Integer>[] queue = new LinkedList[4];
	static boolean visited[] = new boolean[4];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int answer = 0;
		for(int i = 0; i < 4; i++) {
			queue[i] = new LinkedList<>();
			String row = br.readLine();
			for(int j = 0; j < 8; j++) queue[i].offer(row.charAt(j)-'0');
		}
		
		int K = Integer.valueOf(br.readLine());
		
		for(int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken())-1, dir = Integer.parseInt(st.nextToken());
			Arrays.fill(visited, false);
			rotate(idx,dir);
		}
		for(int i = 0; i < 4; i++) {
			if(queue[i].get(0) == 1) answer += Math.pow(2, i);
		}
		System.out.println(answer);
	}
	
	public static void rotate(int idx,int dir) {
		if(visited[idx]) return;
		visited[idx] = true;
		int right = queue[idx].get(2), left = queue[idx].get(6);
		if(dir == 1) queue[idx].offerFirst(queue[idx].pollLast());
		else queue[idx].offer(queue[idx].poll());

		if(idx-1 >= 0 && !visited[idx-1]&& left != queue[idx-1].get(2)) rotate(idx-1,-1*dir);
		if(idx+1 < 4 && !visited[idx+1] && right != queue[idx+1].get(6)) rotate(idx+1,-1*dir);
	}
}