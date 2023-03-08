import java.util.Stack;
class Solution{
    public int solution(String s){
        int idx = 0;
        Stack<Character> stack = new Stack<>();
        stack.push(s.charAt(idx++));
        while(idx<s.length()){
            char c = s.charAt(idx++);
            
            if(!stack.empty() && stack.peek() == c){
                stack.pop();
            }else{
                stack.push(c);
            }
        }
        

        return stack.empty() ?1 :0;
    }
}