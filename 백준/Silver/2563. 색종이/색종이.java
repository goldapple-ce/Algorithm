import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb= new StringBuilder();
        
		int[][] adj = new int[100][100];
		int N = Integer.parseInt(br.readLine());
		
		int answer = 0;
		for(int n=0;n<N;n++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			for(int i=r;i<r+10;i++) {
				for(int j=c;j<c+10;j++) {
					if(adj[i][j]==0) {
						answer++;
						adj[i][j]=1;
					}
					
				}
			}
		}
		
		sb.append(answer);
		System.out.println(sb);

	}

}
