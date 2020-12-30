public class GasStationControllerImpl extends Thread implements GasStationController{

    int nextPumpToUse;
    int [] pumpLengths;

    GasStationControllerImpl(int pumpLength){
        this.nextPumpToUse = 1;
        this.pumpLengths = new int[pumpLength];
    }
    @Override
    public void run() {
        super.run();
    }

    @Override
    public int getPump() {
        return getIndexOfMin(pumpLengths);
    }

    @Override
    public void setPump() {

    }

    @Override
    public int getIndexOfMin(int[] tab) {
        int tablen = tab.length;
        if (tablen>0) {
            int i = 0;
            int minv = tab[0];
            for (int j = 1; j < tab.length; j++) {
                if(tab[j]<minv) {
                    minv = tab[j];
                    i = j;
                }
            }
            return i;
        }
        return -1;
    }
}
