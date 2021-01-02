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
                    System.out.println("how many Cars do you want to accept?");
                    int carAmount = scanner.nextInt();
                    Random random = new Random();
                    while (carAmount>0){
                        CarImpl car = new CarImpl(this.pumps[this.controller.getPump()]);
                        System.out.println(""+this.controller.pumpLengths[0]+this.controller.pumpLengths[1]+this.controller.pumpLengths[2]);
                        System.out.println("New car nr "+car.getId()+" appeared and has been assigned to pump queue nr "+car.hisPump.pumpId);
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
        System.out.println("1 : start accepting Cars");
        System.out.println("q : end Main process");
    }

}
