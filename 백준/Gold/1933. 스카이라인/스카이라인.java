import java.io.*;
import java.util.*;
 
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int N;
    static PriorityQueue<Building> queue;
    static Building[] buildings;
    static TreeMap<Integer,Integer> map;
    static List<Building> answer = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        while(!queue.isEmpty()){
            Building now = queue.poll();

            if(now.height > 0){
                map.put(now.height, map.getOrDefault(now.height, 0)+1);
            }else{
                int height = -now.height;
                int pos = map.get(height);

                if(pos == 1){
                    map.remove(height);
                }else{
                    map.put(height,pos -1);
                }
            }

            if(map.size() == 0){
                answer.add(new Building(now.pos, 0));
            }else{
                answer.add(new Building(now.pos, map.firstKey()));
            }
        }
    }
    
    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        buildings = new Building[N];
        queue = new PriorityQueue<>();
        map = new TreeMap<>(Collections.reverseOrder());

        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            queue.offer(new Building(start, height));
            queue.offer(new Building(end, -height));
        }
    }
    
    static void print() throws Exception {
        sb.append(answer.get(0).pos).append(' ').append(answer.get(0).height);
        int prevHeight = answer.get(0).height;
        for(int i = 1; i < answer.size(); i++){
            if(prevHeight != answer.get(i).height){
                sb.append(' ').append(answer.get(i).pos).append(' ').append(answer.get(i).height);
                prevHeight = answer.get(i).height;
            }
        }
        System.out.println(sb);
    }

    static class Building implements Comparable<Building>{
        int pos, height;

        public Building(int pos, int height) {
            this.pos = pos;
            this.height = height;
        }

        @Override
        public String toString() {
            return "Building [pos=" + pos + ", height=" + height + "]";
        }

        @Override
        public int compareTo(Main.Building o) {
            if(this.pos == o.pos){
                return o.height - this.height;
            }
            return this.pos - o.pos;
        }
        
    }
}