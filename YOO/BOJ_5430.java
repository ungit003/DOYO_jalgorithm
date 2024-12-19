import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        sc.nextLine(); // 개행문자 제거 : 런타임 에러 (InputMismatch) 방지

        for (int t = 0; t < T; t++) {
            String p = sc.nextLine(); // 수행할 함수를 입력받음
            int n = Integer.parseInt(sc.nextLine()); // 배열의 크기를 입력받음 : 런타임 에러 (InputMismatch) 방지
            String arrStr = sc.nextLine();

            Deque<String> deque = new ArrayDeque<>(); // 양방향 큐 생성
            if (n > 0) { // 배열의 크기가 0보다 크면 : 런타임 에러 (StringIndexOutOfBounds) 방지
                // 입력받은 문자열에서 대괄호를 제거하고 쉼표로 분리하여 배열로 만듦
                String[] arr = arrStr.substring(1, arrStr.length() - 1).split(",");
                deque.addAll(Arrays.asList(arr)); // 배열의 모든 요소를 큐에 추가
            }

            boolean isReversed = false;
            boolean hasError = false;

            for (char func : p.toCharArray()) {
                if (func == 'R') {
                    isReversed = !isReversed; // R : 뒤집기 상태를 토글
                } else if (func == 'D') {
                    if (deque.isEmpty()) { // 큐가 비어있으면 에러
                        hasError = true;
                        break;
                    }
                    if (isReversed) {
                        deque.removeLast(); // 뒤집힌 상태면 마지막 요소 제거
                    } else {
                        deque.removeFirst(); // 아니면 첫 번째 요소 제거
                    }
                }
            }

            if (hasError) {
                System.out.println("error"); // 에러가 발생했으면 "error" 출력
            } else {
                StringBuilder sb = new StringBuilder("["); // 결과 문자열 생성 시작
                while (!deque.isEmpty()) {
                    // 뒤집힘 상태에 따라 앞이나 뒤에서 요소를 꺼내 추가
                    sb.append(isReversed ? deque.removeLast() : deque.removeFirst());
                    if (!deque.isEmpty()) {
                        sb.append(","); // 요소 사이에 쉼표 추가
                    }
                }
                sb.append("]"); // 결과 문자열 완성
                System.out.println(sb.toString());
            }
        }
        sc.close();
    }
}
