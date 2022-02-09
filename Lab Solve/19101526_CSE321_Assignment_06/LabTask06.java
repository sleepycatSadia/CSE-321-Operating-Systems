import java.io.*;
import java.util.*;
public class LabTask06 {
    static int processNum, resourceNum;
    static int[] safe = new int[processNum + 10];
    public static void main(String[] args) {
        try{
        Scanner sc = new Scanner(new File("F:\\7TH\\CSE321\\19101526_CSE321_Assignment_06\\LabTask06_Input.txt"));
        processNum = sc.nextInt();//Number of processes
        resourceNum = sc.nextInt();//Number of resources
        int[][] max = new int[processNum][resourceNum];
        int[][] allocation = new int[processNum][resourceNum];
        int[][] changeInAvailable = new int[processNum][resourceNum];
        int[][] need = new int[processNum][resourceNum];
        int[] available = new int[resourceNum];
        for (int i = 0; i < processNum; i++) {
            for (int j = 0; j < resourceNum; j++)
                max[i][j] = sc.nextInt();
        }
        for (int i = 0; i < processNum; i++) {
            for (int j = 0; j < resourceNum; j++)
                allocation[i][j] = sc.nextInt();
        }
        for (int i = 0; i < resourceNum; i++) {
            available[i] = sc.nextInt();
        }

        for (int i = 0; i < processNum; i++) {
            for (int j = 0; j < resourceNum; j++)
                need[i][j] = max[i][j] - allocation[i][j];
        }
        System.out.println("Need matrix is: ");
        for (int i = 0; i < processNum; i++) {
            for (int j = 0; j < resourceNum; j++) {
                System.out.print(need[i][j] + " ");
            }
            System.out.println();
        }
        if (deadlockExistenceCheck(available, allocation, need, processNum, resourceNum, changeInAvailable)) {
            System.out.println("Safe sequence is :");
            for (int i = 0; i < processNum; i++)
                System.out.print((char) (65 + safe[i]) + " ");
            System.out.println();
            System.out.println("Change in available resource matrix : ");
            for (int i = 0; i < processNum; i++) {
                for (int j = 0; j < resourceNum ; j++)
                    System.out.print(changeInAvailable[i][j] + " ");
                System.out.println();
            }
        }
        else
            System.out.println("Deadlock exists !");
    }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static boolean deadlockExistenceCheck(int[] available_resource, int[][] allocated_resource, int[][] needed_resource, int _processNum, int _resourceNum, int[][] changeInAvailable) {
        int check = 0;
        int check1 = 0;
        boolean[] checkSafe = new boolean[_processNum];
        for (int i = 0; i < _processNum; i++)
            checkSafe[i] = false;
        while (check < _processNum && check1 < _processNum){
            for (int i = 0; i < _processNum; i++) {
                boolean flag = true;
                if (!checkSafe[i]) {
                    for (int j = 0; j < _resourceNum; j++) {
                        if (available_resource  [j] < needed_resource[i][j]) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        for (int j = 0; j < _resourceNum; j++) {
                            available_resource[j] = allocated_resource[i][j] + available_resource[j];
                            changeInAvailable[check][j] = available_resource[j];
                        }
                        safe[check] = i;
                        check++;
                        checkSafe[i] = true;
                    }
                }
            }
            check1++;
        }
        return check <= _processNum;
    }

}
