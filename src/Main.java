import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, expectedMax = 2, max, maps[][][], count = 1, SIZE = 10;

    // 백준 12094 2048 (Hard)
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        maps = new int[SIZE + 1][N][N];

        String str;
        StringTokenizer st;
        int total = 0;
        for (int i = 0; i < N; i++) {
            str = br.readLine();
            st = new StringTokenizer(str, " ");

            for (int j = 0; j < N; j++) {
                maps[0][i][j] = Integer.parseInt(st.nextToken());
                total += maps[0][i][j];
            }
        }
        for (; expectedMax * 2 <= total; expectedMax *= 2) ;
        for (Dir value : Dir.values()) {
            move(1, value);
        }
        System.out.println(max);
    }

    public static void move(int cnt, Dir dir) {
        boolean isChanged;
        count = cnt;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                maps[cnt][i][j] = maps[cnt - 1][i][j];
            }
        }
        isChanged = true;

        switch (dir) {
            case UP:
                isChanged = moveUp() != 0;
                break;
            case DOWN:
                isChanged = moveDown() != 0;
                break;
            case LEFT:
                isChanged = moveLeft() != 0;
                break;
            case RIGHT:
                isChanged = moveRight() != 0;
                break;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                max = Math.max(max, maps[cnt][i][j]);
        }
        if (!isChanged) return;
        if (max >= expectedMax) return;
        if (cnt >= SIZE) return;

        for (Dir value : Dir.values()) {
            move(cnt + 1, value);
        }
    }

    private static int moveUp() {
        int cnt = 0;
        int[][] map = maps[count];
        for (int i = 0; i < N; i++) {
            int pos = 0;
            for (int j = 1; j < N; j++) {
                if (map[j][i] != 0) {
                    if (map[j][i] == map[pos][i]) {
                        map[pos++][i] *= 2;
                        map[j][i] = 0;
                        cnt++;
                    } else {
                        if (map[pos][i] != 0) pos++;
                        if (pos < j) {
                            map[pos][i] = map[j][i];
                            map[j][i] = 0;
                            cnt++;
                        }
                    }
                }
            }
        }
        return cnt;
    }

    private static int moveDown() {
        int cnt = 0;
        int[][] map = maps[count];
        for (int i = 0; i < N; i++) {
            int pos = N - 1;
            for (int j = N - 2; j >= 0; j--) {
                if (map[j][i] != 0) {
                    if (map[pos][i] == map[j][i]) {
                        map[pos--][i] *= 2;
                        map[j][i] = 0;
                        cnt++;
                    } else {
                        if (map[pos][i] != 0) pos--;
                        if (pos > j) {
                            map[pos][i] = map[j][i];
                            map[j][i] = 0;
                            cnt++;
                        }
                    }
                }
            }
        }
        return cnt;
    }

    private static int moveLeft() {
        int cnt = 0;
        int[][] map = maps[count];
        for (int j = 0; j < N; j++) {
            int pos = 0;
            for (int i = 1; i < N; i++) {
                if (map[j][i] != 0) {
                    if (map[j][i] == map[j][pos]) {
                        map[j][pos++] *= 2;
                        map[j][i] = 0;
                        cnt++;
                    } else {
                        if (map[j][pos] != 0) pos++;
                        if (pos < i) {
                            map[j][pos] = map[j][i];
                            map[j][i] = 0;
                            cnt++;
                        }
                    }
                }
            }
        }
        return cnt;
    }


    private static int moveRight() {
        int cnt = 0;
        int[][] map = maps[count];
        for (int j = 0; j < N; j++) {
            int pos = N - 1;
            for (int i = N - 2; i >= 0; i--) {
                if (map[j][i] != 0) {
                    if (map[j][i] == map[j][pos]) {
                        map[j][pos--] *= 2;
                        map[j][i] = 0;
                        cnt++;
                    } else {
                        if (map[j][pos] != 0) pos--;
                        if (pos > i) {
                            map[j][pos] = map[j][i];
                            map[j][i] = 0;
                            cnt++;
                        }
                    }
                }
            }
        }
        return cnt;
    }
}

enum Dir {
    UP, DOWN, LEFT, RIGHT
}