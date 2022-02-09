class TASK01 extends Thread {
    public static void main(String []args){
        // creating two threads
        Thread01 t1 = new Thread01("Thread 1");
        Thread02 t2 = new Thread02("Thread 2");
        t1.setPriority(10);
        t1.start();
        t2.start();
    }
}
class Thread01 extends Thread {
    public Thread01(String name) {
        super(name);
    }
    @Override
    public void run(){
        System.out.print("Printing from  "+Thread.currentThread().getName()+" "+": ");
        for (int i = 1; i <= 10; i++) {
            System.out.print(i+"  ");
        }
        System.out.println();
        try {
            sleep(11);//during this time 2nd thread will print 11-20
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("Printing from  "+Thread.currentThread().getName()+" "+": ");
        for (int i = 21; i <= 30; i++) {
            System.out.print(i+" ");
        }
        System.out.println();
    }
}
class Thread02 extends Thread {
    public Thread02(String name) {
        super(name);
    }
    @Override
    public void run(){
        try {
        sleep(10);//during this time 2nd thread will print 11-20
        } catch (InterruptedException e) {
        e.printStackTrace();
        }
        System.out.print("Printing from  "+Thread.currentThread().getName()+" "+": ");
        for (int i = 11; i <= 20; i++) {
            System.out.print(i+" ");
        }
        System.out.println();
    }
}



