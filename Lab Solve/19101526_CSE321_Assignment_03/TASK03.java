
import java.util.*;
import java.util.concurrent.*;
public class TASK03 extends RecursiveTask<Integer> {
    int startIndex, endIndex;
    int[] arr;
    public TASK03 (int start,int end,int[] arr) {
        this.arr = arr;
        this.startIndex = start;
        this.endIndex = end;
    }
    public static void main(String args[]){
        Scanner input = new Scanner (System.in);
        System.out.print("Enter Array length ");
        int n = input.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter the integers : ");
        for (int i=0;i<n;i++) {
            int number = input.nextInt();
            arr[i]=number;
        }

        ForkJoinPool pool = ForkJoinPool.commonPool();
        // start_index the first thread in fork join pool for range 0, n-1
        pool.invoke( new QuickSort_usingMutliThreading ( 0, n - 1, arr));
        System.out.println("Sorted Array elements");
        for (int i = 0; i < n; i++)
            System.out.print(arr[i] + " ");
    }
    private int partion(int startIdx, int endIdx,int[] arr) {
        int i = startIdx;
        int j = endIdx;
        int pivot = new Random().nextInt(j - i)+ i;// Swap the pivot with LAST element of array;
        int t = arr[j];
        arr[j] = arr[pivot];
        arr[pivot] = t;
        j--;
        // FIRST_index partioning
        while (i <= j){
            if (arr[i] <= arr[endIdx]) {
                i++;
                continue;
            }
            if (arr[j] >= arr[endIdx) {
                j--;
                continue;
            }
            t = arr[j];
            arr[j] = arr[i];
            arr[i] = t;
            i++;
            j--;
        }
        // Swap pivot to its correct position
        t = arr[j + 1];
        arr[j + 1] = arr[endIdx];
        arr[endIdx]= t;
        return j + 1;
    }

    @Override
    protected Integer compute(){// Base case
        if (startIndex >= endIndex)return null;// Find partion
        int p = partion(startIndex, endIndex, arr);// Divide array
        TASK03 left= new TASK03 (startIndex, p - 1, arr);
        TASK03 right= new TASK03 (p + 1, endIndex, arr);
        left.fork();
        right.compute();
        left.join();
        return null;
    }

}