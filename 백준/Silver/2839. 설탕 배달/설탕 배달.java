

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static List<Integer> answer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		
		int answer = -1;
		for(int i=0;i<=5000/3;i++) {
			boolean isFinish = false;
			
			for(int j=0; j<=5000/5;j++) {
				if(3*i + 5*j == N) {
					answer = i+j;
					isFinish=true;
					break;
				}
			}
			
			if(isFinish) break;
		}
		
		System.out.println(answer);
		
	}

}
