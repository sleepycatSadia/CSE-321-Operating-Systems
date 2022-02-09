import java.util.*;
public class Task01 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        float avg_turnaround_time;
        float avg_waiting_time;
        int total_turnaround_time = 0;
        int total_waiting_time = 0;

        System.out.println("Enter the number of processes: ");
        int processNum = sc.nextInt();
        int[] is_completed = new int[processNum];
        Process[] p = new Process [processNum];
        for(int i=0;i<processNum;i++)
            p[i]=new Process();

        for (int i = 0; i < processNum; i++){
            p[i].pid = i + 1;
            System.out.println("Enter arrival time of process "+(i+1));
            p[i].arrival_time =  sc.nextInt();
            System.out.println("Enter burst time of process "+(i+1));
            p[i].burst_time = sc.nextInt();
        }

        int current_time = 0;
        int completed = 0;

        while (completed != processNum){
            int idx = -1;
            int mn = 10000000;
            for (int i = 0; i < processNum; i++){
                if (p[i].arrival_time <= current_time && is_completed[i] == 0){
                    if (p[i].burst_time < mn){
                        mn = p[i].burst_time;
                        idx = i;
                    }
                    if (p[i].burst_time == mn){
                        if (p[i].arrival_time < p[idx].arrival_time){
                            mn = p[i].burst_time;
                            idx = i;
                        }
                    }
                }
            }
            if (idx != -1){
                p[idx].start_time = current_time;
                p[idx].completion_time = p[idx].start_time + p[idx].burst_time;
                p[idx].turnaround_time = p[idx].completion_time - p[idx].arrival_time;
                p[idx].waiting_time = p[idx].turnaround_time - p[idx].burst_time;

                total_turnaround_time += p[idx].turnaround_time;
                total_waiting_time += p[idx].waiting_time;

                is_completed[idx] = 1;
                completed++;
                current_time = p[idx].completion_time;
            }else
                current_time++;
        }

        int min_arrival_time = 10000000;
        int max_completion_time = -1;
        for (int i = 0; i < processNum; i++)  {
            min_arrival_time = Math.min(min_arrival_time,p[i].arrival_time);
            max_completion_time = Math.max(max_completion_time,p[i].completion_time);
        }

        avg_turnaround_time = (float) total_turnaround_time / processNum;
        avg_waiting_time = (float) total_waiting_time / processNum;
        System.out.print( "#P\tAT\tBT\tST\tCT\tTAT\tWT\t\n\n");


        for (int i = 0; i < processNum; i++){
            System.out.print(p[i].pid+"\t"+p[i].arrival_time +"\t"+p[i].burst_time+"\t"
                    + p[i].start_time+"\t"+p[i].completion_time+"\t"+ p[i].turnaround_time
                    +"\t"+ p[i].waiting_time+"\t\n");
        }
        System.out.println("Average Waiting Time = "+avg_waiting_time);
        System.out.println("Average Turnaround Time = "+avg_turnaround_time);
    }
}

