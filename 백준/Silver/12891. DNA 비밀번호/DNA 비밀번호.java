import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main{
	static char[] array= {'A', 'C', 'G', 'T'};
	
	public static boolean is_valid_password(HashMap<Character,Integer> cnt, int[] needs) {
		
		for(int i=0;i<4;i++) {
			if(cnt.get(array[i])<needs[i]) return false;
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
		
		HashMap<Character, Integer> cnt = new HashMap<Character,Integer>();
		cnt.put('A', 0);
		cnt.put('C', 0);
		cnt.put('G', 0);
		cnt.put('T', 0);
		
		for(int i=0;i<P;i++) {
			cnt.put(DNA.charAt(i), cnt.get(DNA.charAt(i))+1);
		}
		
		int answer = 0;
		int s = 0, e=P-1;
		
		while(true) {
			if(is_valid_password(cnt,needs)) answer++;
			
			e++;
			if(e>=S) break;
			
			cnt.put(DNA.charAt(e), cnt.get(DNA.charAt(e))+1);
			
			cnt.put(DNA.charAt(s), cnt.get(DNA.charAt(s))-1);
			s++;
		}
		
		System.out.println(answer);
		
		
	}

}
