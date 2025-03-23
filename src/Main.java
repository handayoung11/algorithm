import java.util.Scanner;
import java.util.Stack;

// 백준 10828 - 스택
public class Main {

    public static void main(String[] args) {
        Stack<Integer> arr= new Stack<>();
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        for (int i = 0; i < n; i++) {
            String command = s.next();
            if (command.contains("push")) {
                String num = s.next();
                arr.add(Integer.parseInt(num));
            } else if(command.equals("pop")) {
                if (arr.isEmpty()) {
                    System.out.println(-1);
                    continue;
                }
                System.out.println(arr.pop());
            } else if(command.equals("size")) {
                System.out.println(arr.size());
            } else if(command.equals("empty")) {
                System.out.println(arr.isEmpty() ? 1 : 0);
            } else {
                if (arr.isEmpty()) {
                    System.out.println(-1);
                    continue;
                }
                System.out.println(arr.peek());
            }
        }
    }
}