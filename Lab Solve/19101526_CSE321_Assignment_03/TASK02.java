public class TASK02 {
    public static void main(String[] args) throws Exception {
        MaxDivisorThread[] threadArr = new MaxDivisorThread[10];

        for (int idx = 0; idx < 10;idx++) {
            int lower = idx* 100000 / 10;
            int upper = (idx + 1) * 100000 / 10;
            threadArr[idx] = new MaxDivisorThread(lower,upper ); // create a thread
            threadArr[idx].start(); //start a thread
        }

        for (int i = 0; i < 10; i++)
            threadArr[i].join(); //wait till all 10 threads terminate.

        int maxDivisorCountOverAll = -1;
        int maxDivisorNumberOverAll = 0;
        for (int threadNo = 0; threadNo < 10; threadNo++) { // find result among threads
            if (threadArr[threadNo].maxDivisorCountOfThisThread > maxDivisorCountOverAll) {
                maxDivisorCountOverAll = threadArr[threadNo].maxDivisorCountOfThisThread;
                maxDivisorNumberOverAll = threadArr[threadNo].maxDivisorNumberOfThisThread;
            }
        }

        System.out.println("Number with largets number of divisors is : " + maxDivisorNumberOverAll);
        System.out.println("The number of divisors: " +maxDivisorCountOverAll );
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