import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int N, M, table[][], rowD = 0, colD = 0, ans = -1;

    // 백준 1025 - 제곱수 찾기
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        table = new int[N][M];

        for (int i = 0; i < N; i++) {
            String numbers = br.readLine();
            for (int j = 0; j < M; j++) {
                table[i][j] = numbers.charAt(j) - 48;
            }
        }

        rowD = -N + 1;
        colD = -M + 1;

        // 각각의 공차와 각각의 시작요소에 대해 방향 4개를 다 돌아야 된다.
        // 1. 행 + 열 + 2. 행 + 열 - 3. 행 - 열 + 4. 행 - 열 -
        while (rowD < N && colD < M) {

            for (int row = 0; row < N; row++) {
                for (int col = 0; col < M; col++) {
                    String s = "";
                    for (int i = row, j = col; i < N && i >= 0 && j < M && j >= 0; i += rowD, j += colD) {
                        s += table[i][j];

                        int n = Integer.parseInt(s);

                        if (isSquare(n))
                            ans = Math.max(ans, n);

                        if (colD == 0 && rowD == 0) break;
                    }
                }
            }
            rowD++;

            if (rowD == N && colD < M) {
                rowD = -N + 1;
                colD++;
            }
        }

        System.out.println(ans);
    }

    public static boolean isSquare(int n) {
        double sqrt = Math.sqrt(n);
        return sqrt - Math.floor(sqrt) == 0;
    }
}
