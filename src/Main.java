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
        int count = 0;
       for(int i = 1; i<= num; i++) {
           int[] intArr = Arrays.stream((i + "").split("")).mapToInt(s -> parseInt(s)).toArray();
           if(intArr.length <= 2) {
               count++;
               continue;
           }

           int dif = intArr[1] - intArr[0];
           boolean isNumber = true;
           for (int j = 2; j < intArr.length; j++)
               if(intArr[j] != dif + intArr[j - 1]) {
                   isNumber = false;
                   break;
               }

           if(isNumber) count++;
       }

       return count;
    }
}
