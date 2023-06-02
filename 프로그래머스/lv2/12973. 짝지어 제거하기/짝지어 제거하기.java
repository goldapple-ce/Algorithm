import java.util.Stack;

class Solution{
    public int solution(String s){
        Stack<Character> stack = new Stack<>();
        for(char c : s.toCharArray()){
            if(stack.size() > 0 && stack.peek() == c){
                stack.pop();
                continue;
            }
            stack.push(c);
        }
        return stack.size() == 0 ? 1 : 0;
    }
}