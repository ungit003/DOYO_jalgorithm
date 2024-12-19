package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_5430 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < t; i++) {

            String methods = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String input = br.readLine();
            // 배열 파싱하기
            int[] numbers = parsing(input,n);
            // StringBuilder의 reverse메서드와 deleteCharAt를 쓰면 시간초과
            // 실제로 지우거나 뒤집지 않고 플래그와 배열의 처음 인덱스와 마지막 인덱스로 마지막에 처리
            boolean reverse = false;
            boolean error = false;
            int start = 0;
            int end = n-1;
            for (char method : methods.toCharArray()){
                if (method == 'R') {
                    reverse = !reverse;
                }else if(method == 'D'){
                    // 빈 배열일 때는 error
                    if (start > end){
                        error = true;
                        break;
                    }
                    // 뒤집힌 상태이기 때문에 뒤에서 삭제
                    if (reverse){
                        end--;
                    // 정상적인 상태이기 때문에 앞에서 삭제
                    }else {
                        start++;
                    }
                }
            }
            if (error) {
                sb.append("error\n");
            }else {
                sb.append("[");
                if(start <= end){
                    if (reverse){
                        // 뒤집어진 상태이기 때문에 뒤에서부터 저장
                        for (int j = end; j >= start ; j--) {
                            sb.append(numbers[j]);
                            if (j>start) sb.append(",");
                        }
                    }else{
                        // 정상적인 상태이기 때문에 앞에서부터 저장
                        for (int j = start; j <= end; j++) {
                            sb.append(numbers[j]);
                            if (j < end) sb.append(",");
                        }
                    }
                }
                sb.append("]\n");
                }
            }
        System.out.println(sb);
            }









    // 파싱 메서드
    static int[] parsing(String input, int n){
        if(n==0) return new int[0];
        input = input.substring(1,input.length()-1);
        String[] numbers = input.split(",");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(numbers[i].trim());
        }
        return arr;
    }



}
