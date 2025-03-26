import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 9012 - 괄호
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder b = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringBuilder w = new StringBuilder(br.readLine());
            int cnt = 0;

            for (int j = 0; j < w.length(); j++) {
                if (w.charAt(j) == ')') cnt--;
                else cnt++;

                if (cnt < 0) {
                    b.append("NO\n");
                    break;
                } else if(j == w.length() - 1) {
                    b.append(cnt == 0 ? "YES\n" : "NO\n");
                }
            }
        }
        System.out.println(b);
    }
}