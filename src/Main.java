import java.io.*;

// 백준 1874 - 스택 수열
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 400000);

        int n = Integer.parseInt(br.readLine());
        int st[] = new int[100000], top = -1, now, c = 1;

        for (int i = 0; i < n; i++) {
            now = Integer.parseInt(br.readLine());

            while (top == -1 || st[top] < now) {
                st[++top] = c++;
                bw.append("+\n");
            }

            if (st[top] == now) {
                top--;
                bw.append("-\n");
            } else {
                System.out.print("NO");
                return;
            }
        }

        bw.flush();
        bw.close();
    }
}