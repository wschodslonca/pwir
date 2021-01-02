public class GasStationControllerImpl extends Thread implements GasStationController{

    GasPumpImpl [] pumps;
    final Object lock;
    double cash;

    GasStationControllerImpl(GasPumpImpl [] pumps,Object lock){
        this.pumps = pumps;
        this.lock = lock;
        this.cash = 0.0;
    }

    @Override
    public void run() {
        while (true){
            synchronized (this.lock){
                try {
                    System.out.println("wait");
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("active");
                System.out.println("Station money = "+this.cash);
            }
        }
    }

    @Override
    public synchronized int getPump() {
        return UtilityClass.getIndexOfMin(pumps);
    }

    public void payment(double fuelAmount){
        this.cash+= fuelAmount*4.3;
    }
}
