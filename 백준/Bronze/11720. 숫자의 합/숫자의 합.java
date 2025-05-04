import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N;
    static int answer;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
    }
    
    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        char[] str = br.readLine().toCharArray();
        for(char c : str){
            answer += c -'0';
        }
    }
    
    static void print() throws Exception {
        System.out.println(answer);
    }
}