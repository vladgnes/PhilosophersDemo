/**
 * Created by vlad on 06.05.2016.
 */
public class Philosopher implements Runnable {
    boolean leftHand = false;
    boolean rightHand = false;
    Fork [] forks;//set of  forks that our philosopher uses
    Waiter waiter;//waiter that our philosopher serves
    Fork leftFork;
    Fork rightFork;
    String name;

    public Philosopher(String threadName, Fork leftFork, Fork rightFork, Waiter waiter, Fork [] forks){
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.forks = forks;
        this.waiter = waiter;
        name = threadName;
        System.out.println(threadName + " have started... " );
        run();
    }

    public void run(){
        try {
            for (int i = 0;i < 3; i++) {//assume that all food would be eaten by philosopher in three times
                eat(leftFork, rightFork);
                putLeftFork();
                putRightFork();
                think();
            }
        }catch (InterruptedException e){
            System.out.println(name + " have interrupted");
        }
    }

    public void putLeftFork(){
        leftHand = false;
        leftFork.taken = false;
    }

    public void putRightFork(){
        leftHand = false;
        leftFork.taken = false;
    }

    public void takeLeftFork(Fork fork) throws InterruptedException{
        synchronized (fork) {
            if (!fork.taken) {
                leftHand = true;//true means hand is holding a fork
            } else {
                wait(3000);//if a fork is taken, wait 3 second
                takeLeftFork(fork);//and check again
            }
            fork.taken = true;
            System.out.println(name + " take left fork");
        }
    }
    public void takeRightFork(Fork fork) throws  InterruptedException {
        synchronized (fork) {
            if (!fork.taken) {
                rightHand = true;//true means hand is holding a fork
            } else {
                wait(3000);//if a fork is taken, wait 3 second and check again
                takeRightFork(fork);//and check again
            }
            fork.taken = true;
            System.out.println(name + " take right fork");
        }

    }
    public void think() throws InterruptedException {
        System.out.println(name + " start thinking");
        wait(5000);//thinking during 5 seconds
        System.out.println(name + " finish thinking");
    }
    public boolean askPermission(Fork leftFork, Fork rightFork){
         return waiter.GiveAPermission(leftFork,rightFork);
    }
    public void eat(Fork leftFork, Fork rightFork) throws InterruptedException{
        if (askPermission(leftFork, rightFork)) {
            System.out.println("Waiter allowed " + name + " to take forks");
            takeLeftFork(leftFork);
        } else {
            System.out.println(name + " is waiting for forks");
            wait(5000);
            eat(leftFork, rightFork);
        }
        takeRightFork(rightFork);
        System.out.println(name + " start eating");
        wait(5000);//eating during 5 seconds
        System.out.println(name + " finish eating");
    }
}
