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

    public static int getIndexOfMin(int[] tab) {
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
