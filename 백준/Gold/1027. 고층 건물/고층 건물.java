import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
    static BufferedReader br;
    static int N;
    static int[] buildings;
    static StringTokenizer st;
    static int answer;
    public static void main(String[] args) throws Exception{
       run();
        System.out.println(answer);
    }

    static void run() throws Exception{
        setUp();
        input();
        for(int i = 0; i < N; i++){
            answer = Math.max(answer,cal(i));
        }
    }

    static void setUp() throws Exception{
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    static void input() throws Exception{
        N = Integer.parseInt(br.readLine());
        buildings = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int n = 0; n < N; n++){
            buildings[n] = Integer.parseInt(st.nextToken());
        }
    }

    static int cal(int pivotIdx){
        int cnt = 0;
        for(int i = 0; i < pivotIdx; i++){
            if(isAble(i, pivotIdx)){
                // System.out.println("true "+i+","+pivotIdx);
                cnt++;
            }
        }
        for(int i = pivotIdx+1; i < N; i++){
            if(isAble(pivotIdx, i)){
                // System.out.println("true "+i+","+pivotIdx);
                cnt++;
            }
        }
        // System.out.println("pivotIdx : "+pivotIdx+", cnt : "+cnt);
        return cnt;
    }

    static boolean isAble(int leftIdx, int rightIdx){
        double height = (double)(buildings[rightIdx]-buildings[leftIdx])/(rightIdx-leftIdx);
        for(int i = 1;leftIdx+i<rightIdx;i++){
            // System.out.println("buildings"+buildings[leftIdx+i]+", "+buildings[leftIdx]+i*height);
            if(buildings[leftIdx+i] >= buildings[leftIdx]+i*height) 
                return false;
        }
        return true;
    }
}