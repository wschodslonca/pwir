import java.util.Random;

public class CarImpl extends Thread implements Car{

    double gasAmount;
    double maxAmount;
    GasPumpImpl hisPump;
    final Object controllerLock;
    final Object gasPumpLock;
    GasStationControllerImpl controller;

    @Override
    public void run() {
        try {
            synchronized (this.gasPumpLock) {
                gasPumpLock.notify();
                this.fillFuel();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (this.controllerLock){
            controllerLock.notify();
            this.controller.payment(this.maxAmount);
        }
    }

    public CarImpl(Object controllerLock, GasStationControllerImpl controller){
        Random random = new Random();
        this.gasAmount = 0;
        this.maxAmount = 35*Math.round(random.nextDouble()*10.0)/10.0+5;
        this.hisPump = controller.pumps[controller.getPump()];
        this.gasPumpLock = hisPump.gasPumpLock;
        hisPump.carsQueue.add(this);
        this.controllerLock = controllerLock;
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
