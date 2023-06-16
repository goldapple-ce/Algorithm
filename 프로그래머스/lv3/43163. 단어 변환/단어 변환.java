import java.util.Queue;
import java.util.LinkedList;

class Solution {
    public int solution(String begin, String target, String[] words) {
        boolean[] visited = new boolean[words.length];
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(begin,0));
        
        while(!queue.isEmpty()){
            Node node = queue.poll();
            
            if(target.equals(node.word)) return node.depth;
            
            for(int i = 0; i < words.length; i++){
                if(!visited[i] && checkWords(node.word,words[i])){
                    queue.offer(new Node(words[i],node.depth+1));
                    visited[i] = true;
                }
            }
        }
        return 0;
    }
    
    public boolean checkWords(String word, String target){
        int cnt = 0;
        for(int i = 0; i < target.length(); i++){
            if(word.charAt(i) != target.charAt(i)){
                if(++cnt > 1) return false;
            };
        }
        return true;
    }
}

class Node{
    String word;
    int depth;
    
    public Node(String word, int depth){
        this.word = word;
        this.depth = depth;
    }
}