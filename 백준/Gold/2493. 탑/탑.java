import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
        int N = Integer.parseInt(br.readLine());
        
        int[] arr = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<N+1;i++) 
        	arr[i]= Integer.parseInt(st.nextToken());
        
        int[] answer = new int[N+1];
        
        Stack<int[]> s = new Stack<int[]>();
        
        for(int idx=N; idx>=0;idx--) {
        	int now = arr[idx];
    		while(!s.isEmpty() && s.peek()[1]<=now) {
    			answer[s.pop()[0]] = idx;
    		}
    			s.add(new int[] {idx,arr[idx]});
        	}

        for(int i=1;i<N+1;i++)
        	sb.append(answer[i]+" ");
        
        System.out.println(sb);
        
	}

}
