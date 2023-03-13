import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    static int ans = 0;
    static Map<String, Integer[]> map = new HashMap<>();

    // 백준 1034 - 램프
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String nm[] = br.readLine().split(" ");
        int N = Integer.parseInt(nm[0]);
        int M = Integer.parseInt(nm[1]);

        //문자열을 key로 0의 개수와 카운트 기록
        //0의 개수가 K보다 작거나 같고 K와 홀짝이 맞을 경우 ans = 카운트로 처리
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            int zeros = 0;
            for (int j = 0; j < M; j++) {
                if (s.charAt(j) == '0') {
                    zeros++;
                }
            }
            Integer[] value = map.get(s);
            if (value == null) {
                value = new Integer[] {0, zeros};
                map.put(s, value);
            }
            value[0]++;
        }
        int K = Integer.parseInt(br.readLine());
        int K_REMAINDER = K % 2;

        for (Map.Entry<String, Integer[]> m : map.entrySet()) {
            if (m.getValue()[1] <= K && m.getValue()[1] % 2 == K_REMAINDER) {
                ans = Math.max(ans, m.getValue()[0]);
            }
        }
        System.out.println(ans);
    }
}
