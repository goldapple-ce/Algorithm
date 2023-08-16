
import java.util.Scanner;

public class Main {
    static String[] video;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        video = new String[n];
        for (int i = 0; i < n; i++) {
            video[i] = sc.next();
        }
        recur(0, 0, n);
        System.out.println(sb);
    }

    static void recur(int x, int y, int len) {
        if (len == 1) {
            sb.append(video[y].charAt(x));
            return;
        }
        if (isSameRange(x, y, len)) {
            sb.append(video[y].charAt(x));
            return;
        }
        sb.append('(');
        int half = len / 2;
        recur(x, y, half);
        recur(x + half, y, half);
        recur(x, y + half, half);
        recur(x + half, y + half, half);
        sb.append(')');
    }

    static boolean isSameRange(int x, int y, int len) {
        char bit = video[y].charAt(x);
        for (int ny = y; ny < y + len; ny++) {
            for (int nx = x; nx < x + len; nx++) {
                if (video[ny].charAt(nx) != bit)
                    return false;
            }
        }
        return true;
    }
}

