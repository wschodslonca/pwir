import java.util.Random;

public class CarImpl extends Thread implements Car{

    double gasAmount;
    double maxAmount;
    GasPumpImpl hisPump;

    @Override
    public void run() {
        try {
            this.fillFuel();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public CarImpl(double maxAmount){
        this.maxAmount = maxAmount;
        this.gasAmount = 0;
    }

    public CarImpl(GasPumpImpl gasPump){
        Random random = new Random();
        this.gasAmount = 0;
        this.maxAmount = 35*Math.round(random.nextDouble()*10.0)/10.0+5;
        hisPump = gasPump;
        hisPump.carsQueue.add(this);
    }

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
