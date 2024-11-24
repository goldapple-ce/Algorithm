import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N, M;
    static PriorityQueue<Integer> [] students;
    static int[] selected;
    static int answer = 1_000_000_001;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        for(int n = 0; n < N; n++){
            selected[n] = students[n].poll();
        }

        for(int s = 0; s < N * M; s++){
            int maxIdx = 0, minIdx = 0;
            for(int n = 1; n < N; n++){
                if(selected[maxIdx] < selected[n]){
                    maxIdx = n;
                }else if(selected[minIdx] > selected[n]){
                    minIdx = n;
                }
            }

            answer = Math.min(answer, selected[maxIdx] - selected[minIdx]);

            if(students[minIdx].isEmpty()){
                break;
            }
            selected[minIdx] = students[minIdx].poll();
        }
    }
    
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        students = new PriorityQueue[N];
        selected = new int[N];

        for(int n = 0; n < N; n++){
            students[n] = new PriorityQueue<>();
            st = new StringTokenizer(br.readLine());
            for(int m = 0; m < M; m++){
                students[n].offer(Integer.parseInt(st.nextToken()));
            }
        }
    }
    
    static void print() throws Exception {
        System.out.println(answer);
    }
}