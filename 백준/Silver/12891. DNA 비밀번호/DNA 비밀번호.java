import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	public static boolean is_valid_password(int[] cnt, int[] needs) {
		for(int i=0;i<4;i++) {
			if(cnt[i]<needs[i]) return false;
		}
		return true;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int S = Integer.parseInt(st.nextToken()), P = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		String DNA = st.nextToken();
		
		int [] needs = new int [4];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<4;i++) {
			needs[i]=Integer.parseInt(st.nextToken());
		}
		
		HashMap<String, Integer> checkList = new HashMap<String,Integer>();
		checkList.put("A", 0);
		checkList.put("C", 1);
		checkList.put("G", 2);
		checkList.put("T", 3);
		
		int s = 0, e=P-1;
		int[] cnt = new int[4];
		
		for(int i=0;i<P;i++) {
			int idx = checkList.get(String.valueOf(DNA.charAt(i)));
			cnt[idx]++;
		}
		
		int answer = 0;
		
		while(true) {
			if(is_valid_password(cnt,needs)) answer++;
			
			e++;
			if(e>=S) break;
			
			int idx = checkList.get(String.valueOf(DNA.charAt(e)));
			cnt[idx]++;
			
			idx = checkList.get(String.valueOf(DNA.charAt(s)));
			cnt[idx]--;
			s++;
		}
		
		System.out.println(answer);
		
		
	}

}
