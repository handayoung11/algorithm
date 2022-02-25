import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int MIN, SHARE;

    // 백준 1214 - 쿨한 물건 구매
    public static void main(String[] args) throws IOException {
        int D, higher, lower;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str, " ");

        D = Integer.parseInt(st.nextToken());
        higher = Integer.parseInt(st.nextToken());
        lower = Integer.parseInt(st.nextToken());

        if(D % higher == 0 || D % lower == 0) {
            System.out.println(D);
            return;
        }

        if (higher < lower) {
            int temp = higher;
            higher = lower;
            lower = temp;
        }

        if (higher % lower == 0) {
            MIN = lower * (D / lower + 1);
            System.out.println(MIN);
            return;
        }

        MIN = higher * (D / higher + 1);

        for (int i = (D / higher) - 1; i >= 0; i--) {
            int res = minVal(D - higher * i, lower, higher * i);
            MIN = Math.min(res, MIN);
            if (MIN == D) {
                MIN = D;
                break;
            }
        }
        System.out.println(MIN);
    }

    private static int minVal(int target, int divider, int base) {
        SHARE = target / divider;
        if (target % divider != 0) SHARE++;
        return base + SHARE * divider;
    }

}
