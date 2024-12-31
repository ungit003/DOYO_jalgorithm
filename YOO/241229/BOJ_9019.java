import java.io.*;
import java.util.*;

public class Main {
    // 각 숫자에 대한 명령어 시퀀스를 저장하는 배열
    static String[] command = new String[10000];
    // 각 숫자의 방문 여부를 체크하는 배열
    static boolean[] visited = new boolean[10000];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 수
        
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()); // 시작 숫자
            int B = Integer.parseInt(st.nextToken()); // 목표 숫자
            
            // 각 테스트 케이스마다 배열 초기화
            Arrays.fill(command, "");
            Arrays.fill(visited, false);
            
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(A); // 시작 숫자를 큐에 삽입
            visited[A] = true; // 시작 숫자를 방문했다고 표시
            
            while (!queue.isEmpty()) {
                int current = queue.poll(); // 현재 숫자
                
                if (current == B) { // 목표 숫자에 도달했다면
                    System.out.println(command[current]); // 명령어 시퀀스 출력
                    break;
                }
                
                // D 연산: 2배한 후 10000으로 나눈 나머지
                int D = (current * 2) % 10000;
                // S 연산: 1을 뺀 값 (0이면 9999)
                int S = (current == 0) ? 9999 : current - 1;
                // L 연산: 왼쪽으로 자릿수 회전
                int L = (current % 1000) * 10 + current / 1000;
                // R 연산: 오른쪽으로 자릿수 회전
                int R = (current % 10) * 1000 + current / 10;
                
                // 각 연산 결과에 대해 방문하지 않았다면 큐에 추가하고 명령어 갱신
                if (!visited[D]) {
                    queue.offer(D);
                    visited[D] = true;
                    command[D] = command[current] + "D";
                }
                if (!visited[S]) {
                    queue.offer(S);
                    visited[S] = true;
                    command[S] = command[current] + "S";
                }
                if (!visited[L]) {
                    queue.offer(L);
                    visited[L] = true;
                    command[L] = command[current] + "L";
                }
                if (!visited[R]) {
                    queue.offer(R);
                    visited[R] = true;
                    command[R] = command[current] + "R";
                }
            }
        }
    }
}
