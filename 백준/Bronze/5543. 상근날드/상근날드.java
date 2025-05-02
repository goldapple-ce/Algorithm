import java.util.*;
import java.io.*;
import java.sql.Time;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int[] answer = new int[]{2000,2000};

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
    }
    
    static void input() throws Exception {
        for(int i = 0; i < 3; i++){
            answer[0] = Math.min(answer[0],Integer.parseInt(br.readLine()));
        }
        for(int i = 0; i < 2; i++){
            answer[1] = Math.min(answer[1],Integer.parseInt(br.readLine()));
        }
    }
    
    static void print() throws Exception {
        System.out.println(answer[0] + answer[1] - 50);
    }
}