import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int maxSize = Integer.parseInt(br.readLine());
        boolean[] switchs = new boolean[maxSize];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < maxSize; i++) {
            switchs[i] = st.nextToken().equals("0") ? false : true;
        }

        int orderSize = Integer.parseInt(br.readLine());
        for (int i = 0; i < orderSize; i++) {
            st = new StringTokenizer(br.readLine());
            int sex = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            if (sex == 1) {
                for (int j = num - 1; j < maxSize; j += num) {
                    switchs[j] = !switchs[j];
                }
            } else {
                switchs[num - 1] = !switchs[num - 1];
                int left = num - 2, right = num;
                while (0 <= left && right < maxSize && switchs[left] == switchs[right]) {
                    switchs[left] = !switchs[left];
                    switchs[right] = !switchs[right];
                    left--;
                    right++;
                }
            }
        }
        for (int i = 0; i < maxSize; i++) {
            sb.append(switchs[i] ? "1 " : "0 ");
            if ((i + 1) % 20 == 0)
                sb.append("\n");
        }

        System.out.println(sb);
    }

}