class Solution {
    String[] alphas = {"A","E","I","O","U"};
    int answer = 0;
    public int solution(String word) {
        for(String alpha : alphas){
            if(dfs(alpha,word)) break;
        }
        
        return answer;
    }
    
    public boolean dfs(String str,String word){
        answer++;
        
        if(str.equals(word)) return true;
        if(str.length() == 5) return false;
        
        for(String alpha : alphas){
            if(dfs(str+alpha,word)) return true;
        }
        return false;
    }
}