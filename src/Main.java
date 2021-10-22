import java.util.Scanner;

public class Main {

    // 백준 2231 분해합 - 완전탐색
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nunmber = scanner.nextInt();
        System.out.println(findGenerator(nunmber));
    }

    public static int findGenerator(int number) {
        // 1에서부터 분해합을 찾아나간다.
        for(int i = 1; i < number; i++) {
            // 분해합 구하기
            int digitSum = i, n = i;
            while(n > 0) {
                digitSum += n % 10; // 맨 끝자리를 더한다
                n /= 10; // n을 10으로 나눠 1의 자리를 없앤다
            }
            if(digitSum == number) return i;
        }
        return 0;
    }
}
