
import java.io.*;
import java.util.*;

class Main {
	static int[] numType = {1,2,3,5,7,9};
	static int N;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		
		for(int i = 1; i < numType.length; i++) 
			checkPrime(numType[i],1);
		System.out.println(sb);
	}

	public static void checkPrime(int num, int depth) {
		if(!isPrime(num)) return;
		if(depth == N) {
			sb.append(num).append("\n");
			return;
		}
		
		for(int i = 0; i < numType.length; i++) {
			checkPrime(num*10+numType[i],depth+1);
		}
	}
	
	public static boolean isPrime(int num) {
		for(int i = 2; i <= Math.sqrt(num); i++) {
			if(num%i == 0) return false;
		}
		return true;
	}
}
