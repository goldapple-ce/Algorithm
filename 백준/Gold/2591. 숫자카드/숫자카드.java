
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String num = br.readLine();
		
		int n = num.length();
		char[] s = num.toCharArray();
		long[][] two = new long[n-1][2];		// two[i][0] : 나올 수 있는 i번째 두자리수 , two[i][1] : 해당 숫자가 범위 내에 있으면 1 아니면 0
		
		if(num.charAt(0)-'0' == 0) {
			System.out.print(0);
			return;
		}
		if(n == 1) {
			System.out.print(1);
			return;
		}

//		int cnt = 0;
//		boolean flag = false;
		for(int i = 0; i < n-1; i++) {
			if(i+2<n) {
				if(s[i+1] == '0' && s[i+2] == '0') {
					System.out.print(0);
					return;
				} else if(s[i+2] == '0') {
//					if(s[i]-'0' > 3) {
//						System.out.print(0);
//						return;
//					}
//					i += 1;
//					flag = true;
					continue;
				} 
			}
			if(i+1 < n) {
				if(s[i+1] == '0') {
					if(s[i]-'0' > 3) {
						System.out.print(0);
						return;
					}
					i += 1;
//					flag = true;
					continue;
				}
			}
			two[i][0] = (s[i]-'0')*10 + (s[i+1]-'0');
//			cnt++;
		}
		
		for(int i = 0; i < two.length; i++) {
			two[i][1] = (two[i][0] > 0 && two[i][0] <= 34) ? 1 : 0;
		}
		
		long[][] postfix = new long[n/2][n-1];
		postfix[0][n-2] = two[n-2][1];
		for(int i = n-3; i >= 0; i--) {			// 1행 초기화
			postfix[0][i] = postfix[0][i+1] + two[i][1];
		}
		for(int i = 1; i < n/2; i++) {
			for(int j = n-2; j >= 0; j--) {
				if(j+2 > n-2) {
					postfix[i][j] = 0;
				} else if(two[j][1] == 0) {
					postfix[i][j] = postfix[i][j+1];
				} else {
					postfix[i][j] = postfix[i-1][j+2] + postfix[i][j+1];
				}
			}
		}
		
		long ans = 1;
		for(int i = 0; i < n/2; i++) {
			ans += postfix[i][0];
		}
		
		System.out.print(ans);
		
	}

}
