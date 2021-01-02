import java.util.Random;

public class CarImpl extends Thread implements Car{

    double gasAmount;
    double maxAmount;
    GasPumpImpl hisPump;
    final Object lock;
    GasStationControllerImpl controller;

    @Override
    public void run() {
        try {
            this.fillFuel();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (this.lock){
            lock.notify();
            this.controller.payment(this.maxAmount);
        }
    }

    public CarImpl(Object lock,GasStationControllerImpl controller){
        Random random = new Random();
        this.gasAmount = 0;
        this.maxAmount = 35*Math.round(random.nextDouble()*10.0)/10.0+5;
        hisPump = controller.pumps[controller.getPump()];
        hisPump.carsQueue.add(this);
        this.lock = lock;
        this.controller = controller;
    }

    @Override
    public void fillFuel() throws InterruptedException {
        this.hisPump.fillFuel(this);
    }

    @Override
    public String toString() {
        return "CarImpl{" +
                "gasAmount=" + Math.round(gasAmount*10.0)/10.0 +
                ", maxAmount=" + maxAmount +
                ", hisPump=" + hisPump +
                '}';
    }
}
