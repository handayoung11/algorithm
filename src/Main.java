import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 17413 - 단어 뒤집기 2
public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine() + " ";

        int idx = 0;
        int front = 0;
        StringBuilder sb = new StringBuilder();
        while (idx < s.length()) {
            if(s.charAt(idx) == '<' || s.charAt(idx) == ' ') {
                for (int i = idx - 1; i >= front; i--)
                    sb.append(s.charAt(i));

                if (s.charAt(idx) == '<') {
                    while (s.charAt(idx) != '>') {
                        sb.append(s.charAt(idx++));
                    }
                }

                sb.append(s.charAt(idx++));
                front = idx;
            } else idx++;
        }

        System.out.println(sb);
    }
}
