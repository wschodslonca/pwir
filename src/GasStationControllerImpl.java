public class GasStationControllerImpl extends Thread implements GasStationController{

    GasPumpImpl [] pumps;

    GasStationControllerImpl(GasPumpImpl [] pumps){
        this.pumps = pumps;
    }

    @Override
    public void run() {
        super.run();
    }

    @Override
    public int getPump() {
        return UtilityClass.getIndexOfMin(pumps);
    }

}
