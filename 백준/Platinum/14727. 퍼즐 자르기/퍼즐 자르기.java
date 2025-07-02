import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N;
    static long[] heights;
    static long answer;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        Stack<Integer> stack = new Stack<>();
        for(int n = 0; n < N; n++){
            while(!stack.isEmpty() && heights[stack.peek()] > heights[n]){
                long height = heights[stack.pop()];
                int width = stack.isEmpty() ? n : n - stack.peek()-1;

                answer = Math.max(answer, height * width);
            }

            stack.push(n);
        }

        while(!stack.isEmpty()){
            long height = heights[stack.pop()];
            int width = stack.isEmpty() ? N : N - stack.peek()-1;

            answer = Math.max(answer, height * width);
        }
    }
    
    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());

        heights = new long[N];

        for(int n = 0; n < N; n++){
            heights[n] = Integer.parseInt(br.readLine());
        }
    }
    
    static void print() throws Exception {
        System.out.println(answer);
    }
}