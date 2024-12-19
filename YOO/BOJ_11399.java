import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        int[] times = new int[N];
        
        for (int i = 0; i < N; i++) {
            times[i] = sc.nextInt();
        }
        
        // 버블 정렬
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < N - i - 1; j++) {
                if (times[j] > times[j + 1]) {
                    int temp = times[j];
                    times[j] = times[j + 1];
                    times[j + 1] = temp;
                }
            }
        }

        // // 선택 정렬
        // for (int i = 0; i < N - 1; i++) {
        //     int minIndex = i;
        //     for (int j = i + 1; j < N; j++) {
        //         if (times[j] < times[minIndex]) {
        //             minIndex = j;
        //         }
        //     }
        //     // 최소값을 찾은 후 swap
        //     int temp = times[minIndex];
        //     times[minIndex] = times[i];
        //     times[i] = temp;
        // }

        // // 삽입 정렬
        // for (int i = 1; i < N; i++) {
        //     int key = times[i];
        //     int j = i - 1;
            
        //     while (j >= 0 && times[j] > key) {
        //         times[j + 1] = times[j];
        //         j = j - 1;
        //     }
        //     times[j + 1] = key;
        // }

        // // 퀵 정렬
        // java.util.Stack<Integer> stack = new java.util.Stack<>();
        // stack.push(0);
        // stack.push(N - 1);
        
        // while (!stack.isEmpty()) {
        //     int high = stack.pop();
        //     int low = stack.pop();
            
        //     if (low < high) {
        //         int pivot = times[high];
        //         int i = low - 1;
                
        //         for (int j = low; j < high; j++) {
        //             if (times[j] < pivot) {
        //                 i++;
        //                 int temp = times[i];
        //                 times[i] = times[j];
        //                 times[j] = temp;
        //             }
        //         }
                
        //         int temp = times[i + 1];
        //         times[i + 1] = times[high];
        //         times[high] = temp;
                
        //         int pi = i + 1;
                
        //         stack.push(low);
        //         stack.push(pi - 1);
        //         stack.push(pi + 1);
        //         stack.push(high);
        //     }
        // }

        // // 병합 정렬
        // int[] temp = new int[N];
        // for (int size = 1; size < N; size *= 2) {
        //     for (int start = 0; start < N; start += 2 * size) {
        //         int mid = Math.min(start + size, N);
        //         int end = Math.min(start + 2 * size, N);
        //         int i = start, j = mid, k = start;
                
        //         while (i < mid && j < end) {
        //             if (times[i] <= times[j]) {
        //                 temp[k++] = times[i++];
        //             } else {
        //                 temp[k++] = times[j++];
        //             }
        //         }
        //         while (i < mid) {
        //             temp[k++] = times[i++];
        //         }
        //         while (j < end) {
        //             temp[k++] = times[j++];
        //         }
        //     }
            
        //     System.arraycopy(temp, 0, times, 0, N);
        // }

        // // 힙 정렬
        // for (int i = N / 2 - 1; i >= 0; i--) {
        //     // 최대 힙 구성
        //     int parent = i;
        //     while (true) {
        //         int leftChild = 2 * parent + 1;
        //         int rightChild = 2 * parent + 2;
        //         int largest = parent;
                
        //         if (leftChild < N && times[leftChild] > times[largest]) {
        //             largest = leftChild;
        //         }
        //         if (rightChild < N && times[rightChild] > times[largest]) {
        //             largest = rightChild;
        //         }
                
        //         if (largest == parent) {
        //             break;
        //         }
                
        //         int temp = times[parent];
        //         times[parent] = times[largest];
        //         times[largest] = temp;
                
        //         parent = largest;
        //     }
        // }
        
        // // 힙에서 요소를 하나씩 꺼내어 정렬
        // for (int i = N - 1; i > 0; i--) {
        //     int temp = times[0];
        //     times[0] = times[i];
        //     times[i] = temp;
            
        //     int parent = 0;
        //     while (true) {
        //         int leftChild = 2 * parent + 1;
        //         int rightChild = 2 * parent + 2;
        //         int largest = parent;
                
        //         if (leftChild < i && times[leftChild] > times[largest]) {
        //             largest = leftChild;
        //         }
        //         if (rightChild < i && times[rightChild] > times[largest]) {
        //             largest = rightChild;
        //         }
                
        //         if (largest == parent) {
        //             break;
        //         }
                
        //         temp = times[parent];
        //         times[parent] = times[largest];
        //         times[largest] = temp;
                
        //         parent = largest;
        //     }
        // }
        
        int total = 0;
        int wait = 0;
        
        for (int time : times) {
            wait += time;
            total += wait;
        }
        
        System.out.println(total);
        
        sc.close();
    }
}