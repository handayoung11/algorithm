import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 10799 - 쇠막대기
public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        
        int cnt = 0, opened = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                if (s.charAt(i + 1) == ')') { //isLaser
                    i++;
                    cnt += opened;
                } else opened++;
            } else {
                opened--;
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
