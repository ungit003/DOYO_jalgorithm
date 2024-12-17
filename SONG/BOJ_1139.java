import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
//        BufferedReader in = new BufferedReader(new FileReader("input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());

        StringTokenizer st = new StringTokenizer(in.readLine());
        int[] arr = new int[N];
        for(int i=0;i<N;i++){
            int n = Integer.parseInt(st.nextToken());
            arr[i]=n;
        }

        // 정렬하기
        for(int i=0;i<N-1;i++){
            for(int j=i;j<N;j++){
                if (arr[i]>arr[j]){
                    int temp=arr[i];
                    arr[i]=arr[j];
                    arr[j]=temp;
                }
            }
        }
//        System.out.println(Arrays.toString(arr));
        int result = 0;
        int wait=0;
        for (int i = 0; i < N; i++) {
            result += wait+arr[i];
            wait = wait+arr[i]; // 다음사림이 가딜는 시간 업데이트
        }
        System.out.println(result);
    }


}