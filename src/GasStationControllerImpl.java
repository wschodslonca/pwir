public class GasStationControllerImpl extends Thread implements GasStationController{

    int [] pumpLengths;

    GasStationControllerImpl(int pumpLength){
        this.pumpLengths = new int[pumpLength];
        for (int i = 0;i<pumpLength;i++){
            pumpLengths[i] = 0;
        }
    }

    @Override
    public void run() {
        super.run();
    }

    @Override
    synchronized public int getPump() {
        int pumpId = UtilityClass.getIndexOfMin(pumpLengths);
        this.pumpLengths[pumpId]++;
        return pumpId;
    }

}
