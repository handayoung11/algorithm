import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    // 백준 2339 - 일곱 난쟁이
    public static void main(String[] args) throws IOException {
        int height[] = new int[9], total = -100;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 9; i++) {
            height[i] = Integer.parseInt(br.readLine());
            total += height[i];
        }
        Arrays.sort(height);

        for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (height[i] + height[j] == total) {
                    for (int c = 0; c < 9; c++) {
                        if (c != i && c!= j) {
                            System.out.println(height[c]);
                        }
                    }
                    return;
                }
            }
        }
    }
}
