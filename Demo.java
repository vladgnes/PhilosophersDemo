/**
 * Created by vlad on 09.05.2016.
 */
public class Demo {
    public static void  main(String [] args) {
        //imagine a circle table and 5 philosophers
        //each of them have two forks: one on the right,one the left
        Fork fork1 = new Fork();
        Fork fork2 = new Fork();
        Fork fork3 = new Fork();
        Fork fork4 = new Fork();
        Fork fork5 = new Fork();
        Fork[] forks = {fork1, fork2, fork3, fork4, fork5};
        //they are just eating delicious dish and think about life
        //begins of Aristotle the forks is set in this way
        //fork1 on the right of Aristotle
        //fork2 on the left and so on
        //there are a waiter too, he control eating process, because our philosophers are too busy
        Waiter waiter = new Waiter();
        Philosopher Aristotle = new Philosopher("AristotleThread", fork1, fork2, waiter,forks);
        Philosopher Rousseau = new Philosopher("RousseauThread", fork2, fork3,waiter,forks);
        Philosopher Kant = new Philosopher("KantThread", fork3, fork4,waiter,forks);
        Philosopher Ciceron = new Philosopher("CiceronThread", fork4, fork5,waiter,forks);
        Philosopher Bacon = new Philosopher("BaconThread", fork5, fork1,waiter,forks);
    }
}
