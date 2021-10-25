import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static boolean colors[][];

    // 백준 1018 - 체스판 다시 칠하기
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        int row = strToInt(tokenizer.nextToken()), column = strToInt(tokenizer.nextToken());

        // true = B, false = W
        colors = new boolean[row][column];

        for (int i = 0; i < row; i++) {
            String str = bufferedReader.readLine();
            for (int j = 0; j < column; j++) colors[i][j] = str.charAt(j) == 'B';
        }

        // 잘못된 블럭개수의 최대값인 32 초기값으로 지정
        int count = 32;
        for (int i = 0; 8 <= row - i; i++) {
            for (int j = 0; 8 <= column - j; j++) {
                count = Math.min(count, getUnmatchedBlock(i, j));
            }
            if(count == 0) break;
        }

        System.out.println(count);
    }

    private static int strToInt(String str) {
        return Integer.parseInt(str);
    }

    private static int getUnmatchedBlock(int rowStart, int colStart) {
        boolean start = true;
        int wrongBlock = 0;
        for(int i = rowStart; i < rowStart + 8; i++) {
            // 색깔 전환해가며 다음 색깔과 비교 반복
            for(int j = colStart; j < colStart + 8; j++) if(colors[i][j] != (start = !start)) wrongBlock++;
            // 행 전환 시 색깔 전환
            start = !start;
        }

        // 64 - W 시작 기준의 잘못된 블럭 개수 = B 시작 기준의 잘못된 블럭 개수
        // 둘 중 더 작은 수 반환
        return Math.min(wrongBlock, 64 - wrongBlock);
    }
}
