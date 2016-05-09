/**
 * Created by vlad on 06.05.2016.
 */
public class Philosopher implements Runnable {
    boolean leftHand = false;
    boolean rightHand = false;
    Fork [] forks;//set of  forks that use our philosopher
    Waiter waiter;//waiter that serves our philosopher
    Fork leftFork;
    Fork rightFork;
    Thread t;
    String name;

    public Philosopher(String threadname, Fork leftFork, Fork rightFork, Waiter waiter, Fork [] forks){
        leftFork = this.leftFork;
        rightFork = this.rightFork;
        this.forks = forks;
        this.waiter = waiter;
        name = threadname;
        t = new Thread(this, name);
        System.out.println(threadname + " thread have started... " );
        t.start(); // Запуск потока
    }

    public void run(){
        try {
            for (int i = 0;i < 3; i++) {//assume that all food would be eaten by philosopher in three times
                eat(leftFork, rightFork,forks);
                think();
            }
        }catch (InterruptedException e){
            System.out.println(t + " have interrupted");
        }
    }


    public void putLeftFork(Fork fork) throws InterruptedException{
        if(fork.taken) {
            leftHand = true;//true means hand is holding a fork
        }else {
            t.wait(5000);//if a fork is taken, wait 5 second
            putLeftFork(fork);//and check again
        }
        fork.taken = true;
    }
    public void putRightFork(Fork fork) throws  InterruptedException {
        if(fork.taken) {
            rightHand = true;//true means hand is holding a fork
        }else {
            t.wait(5000);//if a fork is taken, wait 5 second and check again
            putRightFork(fork);//and check again
        }
        fork.taken = true;
    }
    public void think() throws InterruptedException {
        System.out.println(name + " start thinking");
        t.wait(20000);//thinking during 20 seconds
        System.out.println(name + " finish thinking");
    }
    public boolean askPermission(Fork [] forks){
         return waiter.GiveAPermission(forks);
    }
    public void eat(Fork leftFork, Fork rightFork,Fork [] forks) throws InterruptedException{
        if(askPermission(forks)) {
            System.out.println("Waiter allowed" + name +  "to take forks");
            putLeftFork(leftFork);
        }
        else {
            System.out.println(name + " is waiting for forks");
            t.wait(5000);
            eat(leftFork,rightFork,forks);
        }
        putRightFork(rightFork);
        System.out.println(name + " start eating");
        t.wait(10000);//eating during 10 seconds
        System.out.println(name + " finish eating");
    }
}
