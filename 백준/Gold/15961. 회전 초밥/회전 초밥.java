import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	static int n, d, k, c; 
	static int left, right, maxCase;
	static int[] sushi;
	static Map<Integer, Integer> selectedSushi = new HashMap<>();
	static Set<Integer> tempCase;
	
	public static void main(String[] args) throws IOException {
		input();	// 입력 받기
		run();
		bw.write(maxCase + "");
		bw.flush();
		bw.close();
	}
	
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());	
		d = Integer.parseInt(st.nextToken());	
		k = Integer.parseInt(st.nextToken());	
		c = Integer.parseInt(st.nextToken());	
		sushi = new int[n];
		tempCase = new HashSet<>();
		tempCase.add(c);
		right = k-1;
		for(int i = 0; i <= d; ++i) selectedSushi.put(i, 0);
		for(int i = 0; i < n; ++i) {
			sushi[i] = Integer.parseInt(br.readLine());
			if(i < k) {
				selectedSushi.replace(sushi[i], selectedSushi.get(sushi[i]) + 1);		
				tempCase.add(sushi[i]);
			}
		}
		maxCase = tempCase.size();
	}
	
	static void run() {
		if(n == k) return;
		for(int i = 1; i < n; ++i) {
			removeSushi(sushi[left]);
			left = (left+1) % n;
			right = (right+1) % n;
			addSushi(sushi[right]);
			
			maxCase = maxCase > tempCase.size() ? maxCase : tempCase.size() ;
		}
	}
	
	static void removeSushi(int id) {
		if(id == c) return;
		selectedSushi.put(id, selectedSushi.get(id) - 1);
		if(selectedSushi.get(id) == 0) tempCase.remove(id); 
	}
	
	static void addSushi(int id) {
		if(id == c) return;
		selectedSushi.put(id, selectedSushi.get(id) + 1);
		tempCase.add(id);
	}	
}
