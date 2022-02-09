public class TASK02_BONUS{
    public static void main(String[] args) throws Exception {
        MaxDivisorThread[] threadArr = new MaxDivisorThread[10];
        long startTimeMultiThread = System.currentTimeMillis(); // timer start
        for (int idx = 0; idx < 10;idx++) {
            int lower = idx* 100000 / 10;
            int upper = (idx + 1) * 100000 / 10;
            threadArr[idx] = new MaxDivisorThread(lower,upper ); // create a thread
            threadArr[idx].start(); //start a thread
        }

        for (int i = 0; i < 10; i++)
            threadArr[i].join(); //wait till all 10 threads terminate.
        long endTimeMultiThread = System.currentTimeMillis();


        long startTimeSingleThread = System.currentTimeMillis();
        MaxDivisorThread OneThread=new MaxDivisorThread(1,100000);
        OneThread.start();
        OneThread.join();
        long endTimeSingleThread = System.currentTimeMillis();


        long totalMultiThreadTime=endTimeMultiThread - startTimeMultiThread;
        long totalSingleThreadTime=endTimeSingleThread - startTimeSingleThread;
        long difference=totalSingleThreadTime - totalMultiThreadTime;
        System.out.println("Total Execution time in 10 threads (in milliseconds): " + totalMultiThreadTime);
        System.out.println("Total Execution time in 1 thread (in milliseconds): " + totalSingleThreadTime);
        System.out.println("Total Execution time difference (in milliseconds): " + difference);
    }
}

class MaxDivisorThread extends Thread {
    int lower, upper, maxDivisorNumberOfThisThread, maxDivisorCountOfThisThread;

    MaxDivisorThread(int lower_bound, int upper_bound) {
        lower = lower_bound;
        upper = upper_bound;
        maxDivisorCountOfThisThread = 0;
        maxDivisorNumberOfThisThread = -1; //final answer for this thread
    }
    @Override
    public void run() {
        for (int num = lower; num <= upper; num++) {
            int count =getDivisorCount(num);
            if (count > maxDivisorCountOfThisThread) {
                maxDivisorCountOfThisThread = count;
                maxDivisorNumberOfThisThread = num;
            }
        }
    }
    private int getDivisorCount(int number) { //returns number of divisors of a number
        int divisorCount = 0;
        for (int n = 1; n <= number; n++)
            if (number % n == 0)
                divisorCount++;
        return divisorCount;
    }


}