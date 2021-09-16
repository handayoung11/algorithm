import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {

    public static void main(String[] args) {
        // 백준 1065 한수 - 완전탐색
        Scanner scanner = new Scanner(System.in);
        int count = findNumber(scanner.nextInt());
        System.out.println(count);
    }

    public static int findNumber(int num) {
        // 99이하의 숫자는 반드시 한수이므로 num 중 99이하인 수를 기본 count로 설정
        int count = num >= 99 ? 99 : num;

        // 99까지는 체크완료했으므로 100부터 시작
        for (int i = 100; i <= num; i++) {
            // 입력받은 숫자를 자리별로 분해하여 배열로 보관
            int[] intArr = Arrays.stream((i + "").split("")).mapToInt(s -> parseInt(s)).toArray();
            // 공차 구하기
            int dif = intArr[1] - intArr[0];

            boolean isNumber = true;
            for (int j = 2; j < intArr.length; j++)
                // 공차 + 이전수 = 현재수가 성립하는지 검사
                if (intArr[j] != dif + intArr[j - 1]) {
                    isNumber = false;
                    break;
                }

            // 모든 자릿수에서 공차 + 이전수 = 현재수가 성립하면 count++
            if (isNumber) count++;
        }

        return count;
    }
}
