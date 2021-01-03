public class GasStationControllerImpl extends Thread implements GasStationController{

    GasPumpImpl [] pumps;
    double cash;
    Double fuel;
    int pump;

    GasStationControllerImpl(GasPumpImpl [] pumps) {
        this.pumps = pumps;
        this.cash = 0.0;
        this.fuel = 0.0;
        this.pump = 0;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                while (fuel == 0.0) {
                    try {
                        System.out.println("Controller waiting...");
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.payment(fuel);
                    synchronized (this.pumps[pump].carsQueue.get(0)){
                        this.pumps[pump].carsQueue.get(0).notify();
                    }
                    fuel = 0.0;
                }
            }
        }
    }

    @Override
    public synchronized int getPump() {
        return UtilityClass.getIndexOfMin(pumps);
    }

    public void payment(double fuelAmount){
        double moneytoPay = fuelAmount*4.4;
        this.cash += Math.round((moneytoPay) * 100.0) / 100.0;
        UtilityClass.wait(2000);
        System.out.println("Station money = " + Math.round((this.cash) * 100.0) / 100.0);
    }
}
