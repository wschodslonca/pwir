import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class GasPumpImpl implements GasPump{

    int pumpId;
    Semaphore semaphore;
    List<CarImpl> carsQueue;
    @Override
    public void fillFuel(CarImpl car) throws InterruptedException {
        System.out.println("Kolejka "+ pumpId+ " "+this.carsQueue);
        this.semaphore.acquire();
        while(car.gasAmount < car.maxAmount){
            car.gasAmount += 0.5;
            if (car.gasAmount> car.maxAmount){
                car.gasAmount = car.maxAmount;
            }
            //System.out.println("Car thread id = "+car.getId()+" "+car);
            UtilityClass.wait(100);
        }
        System.out.println("Car "+car.getId()+" finished fueling");
        this.carsQueue.remove(0);
        this.semaphore.release();
    }

    public GasPumpImpl(int pumpId) {
        this.semaphore = new Semaphore(1,true);
        carsQueue = new ArrayList<>();
        this.pumpId = pumpId;
    }


    @Override
    public String toString() {
        return "GasPumpImpl{" +
                "pumpId=" + pumpId +
                '}';
    }
}
