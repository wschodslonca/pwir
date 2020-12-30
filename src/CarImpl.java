import java.util.Random;

public class CarImpl extends Thread implements Car{

    double gasAmount;
    double maxAmount;
    int hisPump;
    GasStationControllerImpl controller;

    @Override
    public void run() {
        super.run();
    }

    public CarImpl(double maxAmount){
        this.maxAmount = maxAmount;
        this.gasAmount = 0;
    }

    public CarImpl(GasStationControllerImpl controller){
        Random random = new Random();
        this.gasAmount = 0;
        this.maxAmount = 35*random.nextDouble()+5;
        hisPump = controller.getPump();
    }


}
