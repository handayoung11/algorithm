import java.io.*;
import java.util.LinkedList;

// 백준 10845 - 큐
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] q = new int[100000];
        int top = -1, idx = 0;

        for (int i = 0; i < N; i++) {
            String command = br.readLine();

            switch (command) {
                case "pop" : {
                } case "front": {
                } case "back": {
                    if (top < idx) sb.append("-1");
                    else {
                        if (command.equals("pop"))
                            sb.append(q[idx++]);
                        else if(command.equals("front"))
                            sb.append(q[idx]);
                        else
                            sb.append(q[top]);
                    }
                    sb.append("\n");
                    break;
                } case "size": {
                    sb.append(top - idx + 1).append("\n");
                    break;
                } case "empty": {
                    sb.append(top < idx ? 1 : 0).append("\n");
                    break;
                } default: {
                    q[++top] = Integer.parseInt(command.split(" ")[1]);
                }
            }
        }

        System.out.println(sb);
    }
}