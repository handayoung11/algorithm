import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int MAX = 0, N;
    // 상하좌우
    static int xyMove[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static boolean calculated[][];

    // 백준 12100 - 2048 (Easy)
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = strToInt(bufferedReader.readLine());
        int data[][] = new int[N][N];
        calculated = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer token = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) data[i][j] = strToInt(token.nextToken());
        }

        if(N == 1) {
            System.out.println(data[0][0]);
            return;
        }
        dfs(data, 0);
        System.out.println(MAX + "");
    }

    // 이동이 일어난 후의 결과값을 얻어오는 dfs
    private static void dfs(int[][] data, int len) {
        if (len == 5) return;

        int source[][] = new int[N][N];
        for (int i = 0; i < N; i++) source[i] = data[i].clone();
        for (int i = 0; i < 4; i++) {
            int x = 0, y = 0;
            if (xyMove[i][0] == -1 || xyMove[i][1] == -1) {
                x = xyMove[i][0] * -1;
                y = xyMove[i][1] * -1;
            } else {
                if(xyMove[i][0] == 0) y = N - 2;
                else x = N - 2;
            }
            calculated = new boolean[N][N];
            blockMove(y, x, i, data, false);
            dfs(data, len + 1);
            for (int j = 0; j < N; j++) data[j] = source[j].clone();
        }
    }

    //입력받은 좌표를 기점으로 moveIdx에 따라 한 칸 움직이는 함수
    private static void blockMove(int y, int x, int moveIdx, int result[][], boolean fromZero) {
        int nextY = y + xyMove[moveIdx][1];
        int nextX = x + xyMove[moveIdx][0];
        if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= N || calculated[y][x]) return;

        // 블록 밀기
        int v1 = result[y][x], v2 = result[nextY][nextX];
        if (v2 == 0) {
            result[y][x] = 0;
            result[nextY][nextX] += v1;
            if(v1 != 0) blockMove(nextY, nextX, moveIdx, result, true);
        } else if (v1 == v2) {
            result[y][x] = 0;
            result[nextY][nextX] += v1;
            calculated[y][x] = true;
        }

        MAX = Math.max(MAX, result[x][y]);
        if(fromZero) return; //0에서 온 경우 다음 인덱스로 갈 필요 X

        if (xyMove[moveIdx][0] == 0) {
            x++;
            if(x >= N) {
                x = 0;
                y += xyMove[moveIdx][1] * -1;
            }
        } else {
            y++;
            if(y >= N) {
                y = 0;
                x += xyMove[moveIdx][0] * -1;
            }
        }
        if (y < 0 || y >= N || x < 0 || x >= N) return;
        blockMove(y, x, moveIdx, result, false);
    }

    private static int strToInt(String str) {
        return Integer.parseInt(str);
    }
}
