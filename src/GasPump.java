public interface GasPump {

    void fillFuel(CarImpl car) throws InterruptedException;
    void gasPumpWait();
}
