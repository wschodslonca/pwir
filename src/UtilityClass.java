public class UtilityClass {

    public static void wait(int ms)
    {
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    public static int getIndexOfMin(GasPumpImpl [] pumps) {
        int tablen = pumps.length;
        if (tablen>0) {
            int i = 0;
            int minv = pumps[0].carsQueue.size();
            for (int j = 1; j < tablen; j++) {
                if(pumps[j].carsQueue.size()<minv) {
                    minv = pumps[j].carsQueue.size();
                    i = j;
                }
            }
            return i;
        }
        return -1;
    }
}
