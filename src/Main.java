import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

// 10866 - 덱
public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            String command = br.readLine();
            int size = deque.size();

            switch (command) {
                case "front":
                case "back":
                case "pop_front":
                case "pop_back": {
                    if (size == 0) sb.append("-1\n");
                    else {
                        switch (command) {
                            case "pop_front":
                            case "front":
                                sb.append(deque.getFirst()).append("\n");
                                if (command.equals("pop_front"))
                                    deque.removeFirst();
                                break;
                            case "pop_back":
                            case "back":
                                sb.append(deque.getLast()).append("\n");
                                if (command.equals("pop_back"))
                                    deque.removeLast();
                        }
                    }
                    break;
                } case "size": {
                    sb.append(size).append("\n");
                    break;
                } case "empty": {
                    if (size == 0) sb.append("1\n");
                    else sb.append("0\n");
                    break;
                } default: {
                    String[] comX = command.split(" ");
                    int X = Integer.parseInt(comX[1]);
                    if (comX[0].equals("push_front")) {
                        deque.addFirst(X);
                    } else {
                        deque.addLast(X);
                    }
                }
            }
        }
        System.out.print(sb);
    }
}