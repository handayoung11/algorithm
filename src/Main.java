import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int board[], N, ans;

    // 백준 9663 - N-Queen
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            board = new int[N];
            putQueen(i, 0);
        }

        System.out.println(ans);
    }

    private static void putQueen(int x, int y) {
        if (y == N - 1) {
            ans++;
            return;
        }

        board[y] = x;

        int nextY = y + 1;
        outer:
        for (int j = 0; j < N; j++) {
            for (int i = 0; i < nextY; i++) {
                if (j == board[i] || Math.abs(board[i] - j) == nextY - i) {
                    continue outer;
                }
            }

            putQueen(j, y + 1);
        }
    }
}
