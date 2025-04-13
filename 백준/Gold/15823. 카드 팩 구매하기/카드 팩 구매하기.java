import java.util.*;
import java.io.*;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, M;
    static int[] cards;

    static int answer;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        int min = 0, max = N / M;

        while(min <= max){
            int mid = (min + max) / 2;

            // System.out.println(String.format("min : %d, max : %d, mid : %d", min,max,mid));

            if(packed(mid)){
                answer = mid;
                min = mid + 1;
            }else{
                max = mid - 1;
            }
        }
    }

    static boolean packed(int window){
        int start = 0, now = 0;
        int packedCnt = 0;
        Set<Integer> pack = new HashSet<>();

        while(now < N){
            if(pack.contains(cards[now])){
                while(cards[start++] != cards[now]){
                }

                now = start;
                pack = new HashSet<>();
            }

            // System.out.println(String.format("start : %d, now : %d", start,now));

            pack.add(cards[now++]);

            if(pack.size() >= window){
                if(++packedCnt == M){
                    return true;
                }
                pack = new HashSet<>();
            }
        }
        
        return false;
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cards = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int n = 0; n < N; n++){
            cards[n] = Integer.parseInt(st.nextToken());
        }
    }
    
    static void print() throws Exception {
        System.out.println(answer);
    }
}