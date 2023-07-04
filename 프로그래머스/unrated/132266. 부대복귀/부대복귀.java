import java.util.*;

class Solution {
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        HashMap<Integer,List<Node>> dict = new HashMap<>();
        int[] dist = new int[n+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        boolean[] visited = new boolean[n+1];
        for(int i = 1; i <= n; i++) dict.put(i,new ArrayList<>());
        for(int[] road: roads){
            dict.get(road[0]).add(new Node(road[1],1));
            dict.get(road[1]).add(new Node(road[0],1));
        }
        PriorityQueue<Node> queue = new PriorityQueue<>((o1,o2) -> o1.cost - o2.cost);
        queue.offer(new Node(destination,0));
        dist[destination] = 0;
        while(!queue.isEmpty()){
            Node node = queue.poll();
            
            if(visited[node.v]) continue;
            visited[node.v] = true;
            
            for(Node nNode : dict.get(node.v)){
                if(dist[nNode.v] > dist[node.v] + nNode.cost ){
                    dist[nNode.v] = dist[node.v] + nNode.cost;
                    queue.offer(new Node(nNode.v,dist[nNode.v]));
                }
            }
        }
        for(int i = 0; i < sources.length; i++){
            answer[i] = dist[sources[i]];
            if(answer[i] == Integer.MAX_VALUE) answer[i] = -1;
        }
        
        return answer;
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