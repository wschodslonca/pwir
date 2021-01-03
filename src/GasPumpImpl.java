import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class GasPumpImpl extends Thread implements GasPump{

    int pumpId;
    Semaphore semaphore;
    List<CarImpl> carsQueue;
    final Object gasPumpLock;

    public void gasPumpWait() {
        synchronized (this.gasPumpLock) {
            try {
                System.out.println("gasPump id="+this.pumpId+" waiting...");
                this.gasPumpLock.wait();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        while (true){
            this.gasPumpWait();
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
        this.carsQueue.remove(0);
        this.semaphore.release();
    }

    public GasPumpImpl(int pumpId, Object gasPumpLock) {
        this.semaphore = new Semaphore(1,true);
        carsQueue = new ArrayList<>();
        this.pumpId = pumpId;
        this.gasPumpLock = gasPumpLock;
    }

    @Override
    public String toString() {
        return "GasPumpImpl{" +
                "pumpId=" + pumpId +
                '}';
    }
}
