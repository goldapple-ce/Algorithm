import java.util.*;
import java.io.*;

class Main{
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N,K, MaxLen;
    static int answer;
    static int[] visited = new int[1000001];

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }

    static void input() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        MaxLen = String.valueOf(N).length();
    }

    static void run(){
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(N);

        while(!queue.isEmpty() && K > 0){
            int size = queue.size();
            for(int x = 0; x < size; x++){
                int now = queue.poll();

                for(int i = 0; i < MaxLen -1; i++){
                    for(int j = i +1; j < MaxLen; j++){
                        int next = swap(now, i, j);

                        if(next != 0 && visited[next] != K){
                            queue.add(next);
                            visited[next] = K;
                        }
                    }
                }
            }
            K--;
        }

        if(queue.isEmpty()) 
            answer = -1;
        else{
            for(int x : queue){
                answer = Math.max(answer, x);
            }
        }
    }

    static int swap(int num, int idx1, int idx2){
        char[] strNum = String.valueOf(num).toCharArray();

        char tmp = strNum[idx1];
        strNum[idx1] = strNum[idx2];
        strNum[idx2] = tmp;

        if(strNum[0] == '0')
            return 0;

        return Integer.parseInt(String.valueOf(strNum));
    }

    static void print(){
        System.out.println(answer);
    }
}