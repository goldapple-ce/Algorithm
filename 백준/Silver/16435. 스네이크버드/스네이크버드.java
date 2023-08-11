import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), L = Integer.parseInt(st.nextToken());
		int[] H = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++)
			H[n] = Integer.parseInt(st.nextToken());

		Arrays.sort(H);
		
		for(int n = 0; n < N; n++) {
			if(L < H[n]) break;
			L+=1;
		}
		System.out.println(L);
	}
}
