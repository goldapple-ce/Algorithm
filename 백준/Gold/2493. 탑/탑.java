import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
        int N = Integer.parseInt(br.readLine());
        
        int[] arr = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<N+1;i++) 
        	arr[i]= Integer.parseInt(st.nextToken());
        
        int[] answer = new int[N+1];
        
        Stack<int[]> s = new Stack<int[]>();
        
        for(int i=N; i>=0;i--) {
        	
        	int now = arr[i];
        	
        	if(s.isEmpty())
        		s.add(new int[] {i,arr[i]});
        	else {
        		while(!s.isEmpty() && s.peek()[1]<=now) {
        			answer[s.pop()[0]] = i;
        		}
        		s.add(new int[] {i,arr[i]});
        	}
        }
        for(int i=1;i<N+1;i++)
        	System.out.print(answer[i]+" ");
        
	}

}
