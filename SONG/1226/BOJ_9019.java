import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    static String[] ans;

    public static void main(String[] args) throws IOException {
//        BufferedReader in = new BufferedReader(new FileReader("input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int T= Integer.parseInt(in.readLine());

        for(int i= 0;i<T;i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int A = Integer.parseInt(st.nextToken()); // 자릿수 맞추고 시작
            int B = Integer.parseInt(st.nextToken()); // 자릿수 맞추고 시작

//            System.out.println("A= " + A);
//            System.out.println("B= " + B);

            String result =bfs(A,B);
            System.out.println(result);
        }

    }

    static String bfs(int A, int B){
        boolean[] visited = new boolean[10000];
        ans = new String[10000];
        char[] commends = {'D','S','L','R'};

        Queue<Integer> q =new LinkedList<>();

        q.add(A);
        visited[A]=true;
        ans[A]="";

        while (!q.isEmpty()){
            int curr = q.poll();
            if (curr == B){ //종료 조건
                return ans[B];
            }
            for (char commend : commends) {
                int number=cal(curr, commend); // commend에 따라 숫자 계산
                if (visited[number]){
                    continue;
                }
                visited[number]=true;
                ans[number]=ans[curr]+commend;
                q.add(number);
            }

        }
        return "";
    }
    static int cal(int number,char commend){
        int result=0;
        if (commend=='D'){ // D일 경우
            number *=2; //곱하기 2

            if (number>9999){
                number %= 10000;
            }
            // 숫자 갯수 맟춰주기
            result = number;
        } else if (commend=='S') {
            if (number==0){
                number=9999;
            }else{
                number--;
            }
            result = number;
        } else if (commend=='L') {
            result = (number%1000 *10) + number/1000;
        } else if (commend=='R') {
            result = (number%10 *1000) + number/10;
        }
        return result;
    }
}


