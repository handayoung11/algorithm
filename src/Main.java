import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 1038 - 감소하는 수
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        if (N <= 10) {
            System.out.println(N);
            return;
        } else if (N >= 1023) {
            System.out.println(-1);
            return;
        }

        int target = 11;
        long num = 20;
        while(true) {
            String s = num + "";
            boolean isDecreased = true;
            int len = s.length();

            for (int i = 0; i < len - 1; i++) {
                if (s.charAt(i) <= s.charAt(i + 1)) {
                    isDecreased = false;
                    int digit = (int) Math.pow(10, len - 1 - i);
                    num = (num / digit * digit) + digit;
                    break;
                }
            }

            if (isDecreased) {
                if (target == N) {
                    System.out.println(num);
                    return;
                }
                num++;
                target++;
            }
        }
    }
}
