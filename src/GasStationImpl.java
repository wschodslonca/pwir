import java.util.Scanner;

public class GasStationImpl implements GasStation{

    GasStationControllerImpl controller;
    GasPumpImpl[] pumps;

    @Override
    public void createAndRun() {
        this.pumps = new GasPumpImpl[3];
        this.controller = new GasStationControllerImpl(this.pumps.length);

        for (int i = 0; i < pumps.length; i++) {
            pumps[i] = new GasPumpImpl();
        }
        printMenu();
        Scanner scanner = new Scanner(System.in);
        String written_from_input = scanner.nextLine();
        while (true){
            switch (written_from_input) {
                case "1" -> {
                    CarImpl car = new CarImpl(this.controller);
                    car.start();
                    written_from_input = scanner.nextLine();
                }
                case "q" -> System.exit(0);

                default -> {
                    System.out.println("No matching clause, try again");
                    written_from_input = scanner.nextLine();
                }
            }
        }
    }
    void printMenu(){
        System.out.println("1 : add new Car");
        System.out.println("q : to end Main process");
    }

}
