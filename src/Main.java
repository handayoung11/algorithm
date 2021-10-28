import java.io.*;
import java.util.*;

public class Main {
    static int data[][], row, column;
    static int xMove[] = {0, 0, 1, -1}, yMove[] = {1, -1, 0, 0}, max = 0;
    static boolean checked[][];

    // 백준 14500 - 테트로미노
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer rowColumn = new StringTokenizer(bufferedReader.readLine());
        row = strToInt(rowColumn.nextToken());
        column = strToInt(rowColumn.nextToken());
        data = new int[row][column];
        checked = new boolean[row][column];

        for (int i = 0; i < row; i++) {
            StringTokenizer dataStr = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < column; j++) data[i][j] = strToInt(dataStr.nextToken());
        }

        for (int i = 0; i < row; i++)
            for (int j = 0; j < column; j++) {
                dfs(i, j, 0, 1);
            }
        System.out.println(max);
    }

    private static void dfs(int y, int x, int sum, int len) {
        sum += data[y][x];
        // dfs 탐색 중 길이가 4이면 그만둔다
        if(len >= 4) {
            max = Math.max(max, sum);
            return;
        } else if(len == 3) {
            // ㅏ ㅓ ㅗ ㅜ 테트로미노는 길이가 3인 dfs이므로 따로 설정
            // ㅏ ㅓ 테트로미노
            if(y - 2 >= 0 && checked[y - 1][x] && checked[y - 2][x]) {
                if(x < column - 1) max = Math.max(max, sum + data[y - 1][x + 1]);
                if(x >= 1) max = Math.max(max, sum + data[y - 1][x - 1]);
            }
            // ㅗ ㅜ 테트로미노
            if(x - 2 >= 0 && checked[y][x - 1] && checked[y][x - 2]) {
                if(y < row - 1) max = Math.max(max, sum + data[y + 1][x - 1]);
                if(y >= 1) max = Math.max(max, sum + data[y - 1][x - 1]);
            }
        }
        for(int i = 0; i < 4; i++) {
            checked[y][x] = true;
            int nextX = x + xMove[i], nextY = y + yMove[i];
            if(nextX >= 0 && nextX < column && nextY >= 0 && nextY < row) {
                if (!checked[nextY][nextX]) dfs(nextY, nextX, sum, len + 1);
            }
            // 지나간 좌표(노드)여도 재접근될 수 있으므로 false로 초기화
            checked[y][x] = false;
        }
    }


    private static int strToInt(String str) {
        return Integer.parseInt(str);
    }
}
