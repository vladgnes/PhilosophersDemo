/**
 * Created by vlad on 06.05.2016.
 */
public class Waiter {
    String name;
    public Waiter(){
        this.name ="Vlad";
    }
    public boolean GiveAPermission(Fork leftFork, Fork rightFork){
        return !leftFork.taken & !rightFork.taken;
    }
}
