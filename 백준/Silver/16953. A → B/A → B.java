import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int answer =-1;
	static String s,e;
	public static void dfs(String n, int cnt) {
		if(Integer.parseInt(n) < Integer.parseInt(s)) return;

		if(n.equals(s)) {
			answer = cnt;
			return;
		}
		
		if(Integer.parseInt(n)%2==0) {
			int temp = Integer.parseInt(n)/2;
			dfs(String.valueOf(temp),cnt+1);
		}
		
		if(n.charAt(n.length()-1)=='1') {
			dfs(n.substring(0,n.length()-1),cnt+1);
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		s = st.nextToken();
		e = st.nextToken();
		
		dfs(e,0);
		if(answer!=-1) System.out.println(answer+1);
		else System.out.println(answer);
		
	}

}
