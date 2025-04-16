import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N;
    static Meeting[] meetings;
    static int[] nums;

    static int answer;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        nums[0] = meetings[0].num;

        for(int now = 1; now < N; now++){
            int left = 0, right = now-1;
            int ans = -1;

            while(left <= right){
                int mid = (left + right) / 2;
                // System.out.println(String.format("mid : %d",mid));

                if(meetings[now].start >= meetings[mid].end){
                    ans = mid;
                    left = mid+1;
                }else{
                    right = mid -1;
                }
            }

            // System.out.println(String.format("now : %d, ans : %d", now, ans));

            nums[now] = Math.max(nums[now-1], meetings[now].num + (ans == -1 ?0 : nums[ans]));

            // answer = Math.max(answer, nums[now]);
        }
        // System.out.println(Arrays.toString(nums));
    }
    
    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());

        meetings = new Meeting[N];
        nums = new int[N];

        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            meetings[n] = new Meeting(start, end, num);
        }

        Arrays.sort(meetings, (o1, o2) -> {
            if(o1.end == o2.end){
                return o1.start - o2.start;
            }
            return o1.end - o2.end;
        });

        // System.out.println(Arrays.toString(meetings));
    }
    
    static void print() throws Exception {
        System.out.println(nums[N-1]);
    }

    static class Meeting {
        int start, end;
        int num;

        public Meeting(int start, int end, int num) {
            this.start = start;
            this.end = end;
            this.num = num;
        }

        @Override
        public String toString() {
            return "Meeting [start=" + start + ", end=" + end + ", num=" + num + "]";
        }
    }
}