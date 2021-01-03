public class GasStationControllerImpl extends Thread implements GasStationController{

    GasPumpImpl [] pumps;
    final Object controllerLock;
    double cash;

    GasStationControllerImpl(GasPumpImpl [] pumps,Object controllerLock) {
        this.pumps = pumps;
        this.controllerLock = controllerLock;
        this.cash = 0.0;
    }

    public void controllerWait(){
        synchronized (this.controllerLock){
            try {
                System.out.println("Controller waiting...");
                controllerLock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            this.controllerWait();
        }
    }

    @Override
    public synchronized int getPump() {
        return UtilityClass.getIndexOfMin(pumps);
    }

    public void payment(double fuelAmount){
        double moneytoPay = fuelAmount*4.4;
        this.cash += Math.round((moneytoPay) * 100.0) / 100.0;
        System.out.println("Station money = " + Math.round((this.cash) * 100.0) / 100.0);
    }
}
