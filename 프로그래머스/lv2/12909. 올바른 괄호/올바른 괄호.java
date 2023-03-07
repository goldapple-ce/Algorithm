import java.util.Stack;

class Solution {
    boolean solution(String s) {
        Stack<Character> stack = new Stack<>();        
        char[] s_arr = s.toCharArray();
        for(char s_i:s_arr){
            if(s_i =='('){
                stack.push(')');
            }else if(!stack.empty()){
                stack.pop();
            }else return false;
        }
        return (stack.empty()) ?true :false;
    }
}