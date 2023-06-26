import java.util.*;
import java.util.Map.Entry;

class Solution {
    static HashMap<Integer,List<Node>> dict = new HashMap<>();

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;

        for(int i = 1; i <= n ; i++) dict.put(i,new ArrayList<>());
        for(int[] fare : fares){
            List<Node> arrA = dict.get(fare[0]);
            List<Node> arrB = dict.get(fare[1]);
            arrA.add(new Node(fare[1],fare[2]));
            arrB.add(new Node(fare[0],fare[2]));
            dict.put(fare[0],arrA);
            dict.put(fare[1],arrB);
        }

        int[] costsA = dijstra(a,new int[n+1]);
        int[] costsB = dijstra(b,new int[n+1]);
        int[] costsS = dijstra(s,new int[n+1]);
        
        for(int i = 1; i <= n; i++) answer = Math.min(answer,costsA[i]+costsB[i]+costsS[i]);
        
        return answer;
    }
    
    public int[] dijstra(int start,int[] dp){
        PriorityQueue<Node> queue = new PriorityQueue<>((o1,o2) -> o1.cost - o2.cost);
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0] = 0; dp[start] = 0;
        queue.offer(new Node(start,0));
        
        while(!queue.isEmpty()){
            Node node = queue.poll();
            for(Node endNode : dict.get(node.v)){
                if(node.cost + endNode.cost < dp[endNode.v]){
                    queue.offer(new Node(endNode.v,node.cost+endNode.cost));
                    dp[endNode.v] = node.cost + endNode.cost;
                }
            }
        }
        return dp;
    }
}

class Node{
    int v, cost;
    
    public Node(int v, int cost){
        this.v = v;
        this.cost = cost;
    }
    
    public String toString(){
        return "{v : "+v+", cost : "+cost+"}";
    }
}
