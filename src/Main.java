import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 9093 - 단어 뒤집기
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char st[] = new char[20];
        int top = -1;
        StringBuilder b = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringBuilder w = new StringBuilder(br.readLine()).append(' ');

            for (int j = 0; j < w.length(); j++) {
                if (w.charAt(j) == ' ') {
                    while(top != -1)
                        b.append(st[top--]);
                    b.append(' ');
                } else st[++top] = w.charAt(j);
            }
            b.append('\n');
        }
        System.out.print(b);
    }
}