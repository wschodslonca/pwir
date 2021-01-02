import java.util.Random;
import java.util.Scanner;

public class GasStationImpl implements GasStation{

    GasStationControllerImpl controller;
    GasPumpImpl[] pumps;

    @Override
    public void createAndRun() {
        this.pumps = new GasPumpImpl[3];
        this.controller = new GasStationControllerImpl(this.pumps.length);
        this.controller.start();
        for (int i = 0; i < pumps.length; i++) {
            pumps[i] = new GasPumpImpl(i);
        }
        printMenu();
        Scanner scanner = new Scanner(System.in);
        String written_from_input = scanner.nextLine();
        while (true){
            switch (written_from_input) {
                case "1" -> {
                    int carAmount = scanner.nextInt();
                    Random random = new Random();
                    while (carAmount>0){
                        CarImpl car = new CarImpl(this.pumps[this.controller.getPump()]);
                        System.out.println("New car created "+car.getId()+" and has been added to pump queue "+car.hisPump.pumpId);
                        car.start();
                        UtilityClass.wait(random.nextInt(10)*1000);
                        carAmount--;
                    }
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

    @Override
    public void printMenu(){
        System.out.println("1 : add new Car");
        System.out.println("q : to end Main process");
    }

}
