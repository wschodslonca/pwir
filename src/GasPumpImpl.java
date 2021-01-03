import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class GasPumpImpl extends Thread implements GasPump{
    int pumpId;
    Semaphore semaphore;
    List<CarImpl> carsQueue;

    @Override
    public void run() {
        while (true){
            synchronized (this) {
                try {
                    System.out.println("gasPump id=" + this.pumpId + " waiting...");
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    this.fillFuel(this.carsQueue.get(0));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void fillFuel(CarImpl car) throws InterruptedException {
        //System.out.println("Kolejka "+ pumpId+ " "+this.carsQueue);
        System.out.println("gasPump id="+this.pumpId+" working.");
        this.semaphore.acquire();
        while(car.gasAmount < car.maxAmount){
            car.gasAmount += 0.1;
            if (car.gasAmount> car.maxAmount){
                car.gasAmount = car.maxAmount;
            }
            //System.out.println("Car thread id = "+car.getId()+" "+car);
            UtilityClass.wait(100);
        }
        System.out.println("Car "+car.getId()+" finished fueling");
        this.semaphore.release();
        synchronized (this.carsQueue.get(0)) {
            this.carsQueue.get(0).notify();
        }
    }

    public GasPumpImpl(int pumpId, Object gasPumpLock) {
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
