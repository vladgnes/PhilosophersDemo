/**
 * Created by vlad on 06.05.2016.
 */
public class Waiter {
    String name;
    public Waiter(){
        this.name ="Vlad";
    }
    public boolean GiveAPermission(Fork [] forks ){
        int takenForks = 0;
        for (int i = 0; i < 5; i++){
            if(forks[i].taken){
                takenForks++;
            }
        }
        if(takenForks >= 4){
            return false;
        }
        else {
            return true;
        }

    }
}
