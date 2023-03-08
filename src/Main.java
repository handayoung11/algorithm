import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static int N;
    public static int[] buildings;
    public static int ans = 0;

    // 백준 1027 - 고층 건물
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        buildings = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < N; i++) {
            int showed = 0;

            for (int j = 0; j < N; j++) {
                if (i - 1 <= j && i + 1 >= j) {
                    if (i != j) {
                        showed++;
                    }
                    continue;
                }

                //기울기: y증가량 / x증가량
                double a = (buildings[i] - buildings[j]) / (double) (i - j);
                //y절편: ax+b=y, b=y-ax
                double b =  buildings[i] - a * (i + 1);
                boolean canShow = true;

                if (j < i) {
                    for (int c = i - 1; c > j; c--) {
                        if (a * (c + 1) + b <= buildings[c]) {
                            canShow = false;
                            break;
                        }
                    }
                } else {
                    for (int c = i + 1; c < j; c++) {
                        if (a * (c + 1) + b <= buildings[c]) {
                            canShow = false;
                            break;
                        }
                    }
                }
                if (canShow) showed++;
            }

//            System.out.println("buildings[" + i + "] = " + buildings[i] + ": " + showed );
            ans = Math.max(showed, ans);
        }

        System.out.println(ans);
    }
}
