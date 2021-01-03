import java.util.Random;

public class CarImpl extends Thread implements Car{

    double gasAmount;
    double maxAmount;
    final GasPumpImpl hisPump;
    final GasStationControllerImpl controller;

    @Override
    public void run() {
        if (this.hisPump.carsQueue.get(0) != this){
            synchronized (this) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Car " + this.getId() + " at pump nr " + this.hisPump.pumpId);
        synchronized (this.hisPump) {
            this.hisPump.notify();
        }
        synchronized (this) {
            while (this.gasAmount < this.maxAmount){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        synchronized (this.controller){
            System.out.println("Car " + this.getId() + " paying...");
            this.controller.fuel = maxAmount;
            this.controller.pump = this.hisPump.pumpId;
            this.controller.notify();
        }
        synchronized (this) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.hisPump.carsQueue.remove(0);
        if (!this.hisPump.carsQueue.isEmpty()){
             synchronized (this.hisPump.carsQueue.get(0)){
                this.hisPump.carsQueue.get(0).notify();
            }
        }
    }

    public CarImpl(Object controllerLock, GasStationControllerImpl controller){
        Random random = new Random();
        this.gasAmount = 0;
        this.maxAmount = 35*Math.round(random.nextDouble()*10.0)/10.0+5;
        this.hisPump = controller.pumps[controller.getPump()];
        this.hisPump.carsQueue.add(this);
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
