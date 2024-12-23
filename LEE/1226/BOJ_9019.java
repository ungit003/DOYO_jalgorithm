package boj;


import java.io.*;
import java.util.*;

public class BOJ_9019 {



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            String answer = bfs(a,b);
            System.out.println(answer);



        }

    }
    // D S L R 메서드들
    static int D(int n) {
        return n * 2 > 9999 ? (n * 2) % 10000 : n * 2;
    }

    static int S(int n) {
        return n == 0 ? 9999 : n - 1;
    }
    // 4자리로 변환하니까 시간초과 나서 연산으로 해결
    static int L(int n) {
        return (n % 1000) * 10 + (n/1000);
    }
    static int R(int n) {
        return (n - (n / 10) * 10) * 1000 + (n/10);
    }

//    static int L(int n) {
//        String formant = String.format("%04d", n);
//        char first = formant.charAt(0);
//        String result = formant.substring(1) + first;
//        return Integer.parseInt(result);
//    }
//
//    static int R(int n) {
//        String formant = String.format("%04d", n);
//        char last = formant.charAt(3);
//        String result = last + formant.substring(0,3);
//        return Integer.parseInt(result);
//    }

    static String bfs(int a, int b) {
        // bfs 탐색하면서 D,S,L,R 메서드 저장하는 Deque
        Deque<String> totalPath = new LinkedList<>();
        // 초기 숫자와 D,S,L,R 메서드로 인해 바뀌는 숫자들 저장하는 Deque
        Deque<Integer> numbers = new LinkedList<>();
        // 중복된 숫자면 다시 안해도 되니까 방문표시를 하기 위한 배열
        boolean[] visited = new boolean[10000];
        int next;

        // D,S,L,R을 하나씩 저장
        next = D(a);
        if (!visited[next]){
            numbers.add(next);
            totalPath.add("D");
            visited[next] = true;
        }
        next = S(a);
        if (!visited[next]){
            numbers.add(next);
            totalPath.add("S");
            visited[next] = true;
        }
        next = L(a);
        if (!visited[next]){
            numbers.add(next);
            totalPath.add("L");
            visited[next] = true;
        }
        next = R(a);
        if (!visited[next]){
            numbers.add(next);
            totalPath.add("R");
            visited[next] = true;
        }


        while (!numbers.isEmpty()){
            int num = numbers.poll();
            String currentPath = totalPath.poll();

            if (num == b){
                return currentPath;
            }

            int nextNum;
            String path;


            nextNum = D(num);
            // 처리한 숫자가 아닐 때 새로운 숫자와 경로를 Deque에 추가
            if (!visited[nextNum]){
                numbers.add(nextNum);
                path = (currentPath + "D");
                totalPath.add(path);
                visited[nextNum] = true;
            }
            nextNum = S(num);
            if (!visited[nextNum]){
                numbers.add(nextNum);
                path = (currentPath + "S");
                totalPath.add(path);
                visited[nextNum] = true;
            }
            nextNum = L(num);
            if (!visited[nextNum]){
                numbers.add(nextNum);
                path = (currentPath + "L");
                totalPath.add(path);
                visited[nextNum] = true;
            }
            nextNum = R(num);
            if (!visited[nextNum]){
                numbers.add(nextNum);
                path = (currentPath + "R");
                totalPath.add(path);
                visited[nextNum] = true;
            }

        }
        return "-1";
    }


}
