import java.util.*;
import java.io.*;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, A, B;
    static int[] nums;
    static boolean answer = true;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        if(A+B > N+1){
            answer = false;
            return;
        }

        int a = 1, b = 1;

        for(int n = N-B-A+2; n <= N-B; n++){
            nums[n] += a++;
        }

        for(int n = N-2; n >= N-B; n--){
            nums[n] += b++;
        }

        if(A != 1 && B != 1){
            nums[N-B] = Math.max(nums[N-B-1], nums[N-B+1])+1;
        }

        if(A == 1){
            if(B == N){
                nums[0] = N-1;
            }else{
                nums[0] = nums[N-B];
                nums[N-B] = 0;
            }
        }
        
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        nums = new int[N];
    }
    
    static void print() throws Exception {
        if(answer){
            for(int num : nums){
                sb.append(num+1).append(' ');
            }
        }else{
            sb.append(-1);
        }
        
        System.out.println(sb);
    }
}