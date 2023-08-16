import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static String switchToNumber(String s) {
    	
        int len = s.length();
        
        if(len==0) return "";
        if(len==1) return s.equals("M")? "1":"5";
        
        String result = "";
        if(s.lastIndexOf("K")>0) result+="5";
        else result+="1";

    	for(int i=0;i<len-1;i++)
    		result+="0";
        
        return result;
        
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        String str = br.readLine();
        
        String bigAnswer = "";
        String smallAnswer = "";
        
        // 큰 수 로직(K가 들어가는 가장 긴 수)
        String buffer = "";
        for(int i=0;i<str.length();i++) {
            if(str.charAt(i)=='M') {
                buffer+=str.charAt(i);
                continue;
            }

            sb.append(switchToNumber(buffer+"K"));
            buffer="";
        }
        if(buffer.length()>0) { //buffer에 M만 있을 때 
            for(int i=0;i<buffer.length();i++) {
            	sb.append("1");
            }
        }
        
        sb.append("\n");
        
        // 작은수: K앞에서 짜르기, K 계산
        buffer = "";
        for(int i=0;i<str.length();i++) {
            if(str.charAt(i)=='M') {
                buffer+='M';
                continue;
            }
            
            sb.append(switchToNumber(buffer));
            sb.append("5");
            
            buffer="";
        }
        if(buffer.length()>0) {
        	sb.append(switchToNumber(buffer));
        }
        
        System.out.println(sb);

    }

}
