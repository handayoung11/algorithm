import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    // 백준 2798 블랙잭 - 완전탐색
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int cardsCount = scanner.nextInt(), targetNumber = scanner.nextInt();
        List<Integer> cards = new ArrayList<>(cardsCount);
        for(int i = 0; i < cardsCount; i++) cards.add(scanner.nextInt());
        System.out.println(findClosestNumber(targetNumber, cards));
    }

    // num에서 임의의 숫자 3개의 합 중 target에 가장 가까운 수를 찾는 함수
    public static int findClosestNumber(int target, List<Integer> num) {
        int theClosestN = 0;
        // i: 조합할 첫번째 수의 index
        // j: 조합할 두번째 수의 index
        // k: 조합할 세번째 수의 index
        for(int i = 0; i < num.size(); i++)
            // i >= j이면 동일한 수를 2번 꺼내게 되므로 j = i + 1 시작
            for(int j = i + 1; j < num.size(); j++) {
                // j >= k이면 동일한 수를 2번 꺼내게 되므로 k = j + 1 시작
                for(int k = j + 1; k < num.size(); k++) {
                    // n: 세 수를 조합한다
                    // dif: 조합한 수와 목표 숫자의 차
                    int n = num.get(i) + num.get(j) + num.get(k), dif = target - n;
                    if(dif >= 0 && dif <= target - theClosestN) {
                        theClosestN = n;
                        // dif가 0이면 이것보다 가까운 수를 찾을 수 없으므로 바로 반환한다.
                        if(dif == 0) return theClosestN;
                    }
                }
            }
        return theClosestN;
    }
}
