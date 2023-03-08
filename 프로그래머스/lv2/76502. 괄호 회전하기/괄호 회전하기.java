import java.util.Stack;

class Solution {
    public int solution(String s) {
        int answer = 0;
        for(int x = 0;x<s.length();x++){
            if(check(s)) answer++;
            s = s.substring(1) + s.charAt(0);
        }
        
        return answer;
    }
    public boolean check(String s){
        Stack<Character> stack = new Stack<>();
        for(int i = 0;i<s.length();i++){
            if(s.charAt(i) == '{' || s.charAt(i) == '(' || s.charAt(i) == '['){
                switch(s.charAt(i)){
                    case '{':
                        stack.push('}');
                        break;
                    case '[':
                        stack.push(']');
                        break;
                    default:
                        stack.push(')');
                        break;
                }
            }else{
                if (stack.empty() || stack.peek() != s.charAt(i)) return false;
                stack.pop();
            }
        }
        return stack.empty();
    }
}