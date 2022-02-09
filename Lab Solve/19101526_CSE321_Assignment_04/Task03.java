import java.util.*;
public class Task03 {
    public static void main(String []agrs){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter number of process");
        int processNum= scan.nextInt();
        int arrivalTime[] = new int[processNum];//Assuming arrival time zero for all processes
        int processArr[] = new int[processNum];
        int burstTime[] = new int[processNum];

        for (int i = 0; i < processNum; i++) {
            processArr[i]= i+1;
        }

        System.out.println("Enter Quantum number");
        int quantum = scan.nextInt();

        System.out.println("Enter Burst time of each process");
        for (int i = 0; i < processNum; i++) {
            burstTime[i]= scan.nextInt();
        }
        int wt_time[] = new int[processNum];
        int tat_time[] = new int[processNum];
        int completion_time[] = new int[processNum];
        // copy the value of burstTime array into wt_time array.
        int rem_time[] = new int[processNum];
        for(int i=0;i<wt_time.length;i++){
            rem_time[i]= burstTime[i];
        }
        int t=0;
        int arrival=0;
        // processing until the value of every element of rem_time array is 0
        while(true){
            boolean done = true;
            for(int i=0;i<processNum;i++){
                if(rem_time[i]>0){
                    done =false;
                    if(rem_time[i]>quantum && arrivalTime[i]<=arrival){
                        t +=quantum;
                        rem_time[i]-=quantum;
                        arrival++;
                    }else{
                        if(arrivalTime[i]<=arrival){
                            arrival++;
                            t+=rem_time[i];
                            rem_time[i]=0;
                            completion_time[i]=t;
                        }
                    }
                }
            }
            if(done==true)
                break;
        }
        for(int i=0;i<processNum;i++) {
            tat_time[i] = completion_time[i] - arrivalTime[i];
            wt_time[i] = tat_time[i] - burstTime[i];
        }
        int total_wt = 0, total_tat = 0;

        System.out.println(" #P " +"AT "+ " BT " +" CT "+" TAT " + " WT ");
        for (int j=0; j<processNum; j++){
            total_wt = total_wt + wt_time[j];
            total_tat = total_tat + tat_time[j];
            System.out.println(" " + (j+1) + "\t"+ arrivalTime[j]+"\t"+ + burstTime[j] +"\t" +completion_time[j]+"\t"+tat_time[j] +"\t" + wt_time[j]);
        }
        System.out.println("Average waiting time = "+(float)total_wt / (float)processNum);
        System.out.println("Average turn around time = " +(float)total_tat / (float)processNum);

        scan.close();
    }
}
