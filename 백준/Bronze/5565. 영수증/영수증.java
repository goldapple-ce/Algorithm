import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int answer;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
    }
    
    static void input() throws Exception {
        answer = Integer.parseInt(br.readLine());
        for(int i = 0; i < 9; i ++){
            answer -= Integer.parseInt(br.readLine());
        }
    }
    
    static void print() throws Exception {
        System.out.println(answer);
    }
}