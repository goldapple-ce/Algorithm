
import java.io.*;
import java.util.*;

class Main {
	static String[] numType = {"1","2","3","5","7","9"};
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		
		for(int i = 1; i < numType.length; i++) {
			checkPrime(numType[i],1);
		}
	}

	public static void checkPrime(String strNum, int depth) {
		int num = Integer.parseInt(strNum);
		for(int i = 2; i <= Math.sqrt(num); i++) {
			if(num%i == 0) return;
		}
		if(depth == N) {
			System.out.println(strNum);
			return;
		}
		
		for(int i = 0; i < numType.length; i++) {
			checkPrime(strNum+numType[i],depth+1);
		}
	}
}
