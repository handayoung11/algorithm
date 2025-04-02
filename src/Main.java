import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

// 10866 - 덱(배열로 풀기)
public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] deque = new int[N];
        int front = 0, back = 0, size = 0;

        for (int i = 0; i < N; i++) {
            String command = br.readLine();

            switch (command) {
                case "front":
                case "back":
                case "pop_front":
                case "pop_back": {
                    if (size == 0) sb.append("-1\n");
                    else {
                        switch (command) {
                            case "pop_front": size--;
                            case "front":
                                sb.append(deque[front]).append("\n");
                                if (command.equals("pop_front"))
                                    if (++front == N) front = 0;
                                break;
                            case "pop_back": size--;
                            case "back":
                                int idx = back - 1;
                                if (idx == -1) idx = N - 1;
                                sb.append(deque[idx]).append("\n");
                                if (command.equals("pop_back"))
                                    back = idx;
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
                    size++;
                    String[] comX = command.split(" ");
                    int X = Integer.parseInt(comX[1]);
                    if (comX[0].equals("push_front")) {
                        if (--front == -1)
                            front = N - 1;
                        deque[front] = X;
                    } else {
                        deque[back] = X;
                        if (++back == N) back = 0;
                    }
                }
            }
        }
        System.out.print(sb);
    }
}