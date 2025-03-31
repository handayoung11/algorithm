import java.io.*;
import java.util.LinkedList;

// 백준 1406 - 에디터
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String text = br.readLine();
        int N = Integer.parseInt(br.readLine());
        char[] left = new char[600000], right = new char[600000];
        int lTop = text.length() - 1, rTop = -1;

        for (int i = 0; i < text.length(); i++)
            left[i] = text.charAt(i);

        for (int i = 0; i < N; i++) {
            String command = br.readLine();
            switch (command) {
                case "L": {
                    if (lTop != -1) {
                        right[++rTop] = left[lTop--];
                    }
                    break;
                }
                case "D": {
                    if (rTop != -1) {
                        left[++lTop] = right[rTop--];
                    }
                    break;
                }
                case "B": {
                    if (lTop != -1) {
                        lTop--;
                    }
                    break;
                }
                default: {
                    char p = command.charAt(2);
                    left[++lTop] = p;
                }
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < lTop + 1; i++)
            bw.write(left[i]);
        for (int i = rTop; i >= 0; i--)
            bw.write(right[i]);
        bw.flush();
    }
}