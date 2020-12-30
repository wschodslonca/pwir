import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class GasPumpImpl implements GasPump{

    Semaphore semaphore;
    List<CarImpl> carsQueue;
    @Override
    public void fillFuel(CarImpl car) {
        while(car.gasAmount < car.maxAmount){
            car.gasAmount += 0.1;
            wait(100);
        }
    }

    public GasPumpImpl() {
        this.semaphore = new Semaphore(1,true);
        carsQueue = new ArrayList<>();
    }

    public static void wait(int ms)
    {
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }
}
