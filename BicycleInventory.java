package bicyleCompany;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BicycleInventory {
    Scanner sc = new Scanner(System.in);
    private Bicycle inventory[];

    private int size;


    public BicycleInventory() {
        this.inventory = new Bicycle[100];
        this.size = 0;
    }

    public static void main(String[] args) {
        BicycleInventory store = new BicycleInventory();
        store.readInventory();
        while (true){
            store.menuOptions();
        }

    }

    public Bicycle[] getInventory() {
        return inventory;
    }

    public void setInventory(Bicycle[] inventory) {
        this.inventory = inventory;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    // read file data
    public void readInventory() {
        try {
            FileInputStream fstream = new FileInputStream("G:\\eclipseprojects\\bicyleCompany\\src\\bicyleCompany\\Bicycle.txt");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            int counter = 0;
            this.size = 0;
            while ((strLine = br.readLine()) != null) {
                String[] tokens = strLine.split(" ");

                Bicycle record = new Bicycle(
                        tokens[0].trim(),
                        Integer.parseInt(tokens[1].trim()),
                        Boolean.parseBoolean(tokens[2].trim()),
                        tokens[3].trim().charAt(0)
                );//process record

                inventory[counter] = record;
                size = size + 1;
                counter++;
            }
            in.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

    }


    public Bicycle [] getChoices(String type, int limit) {
        Bicycle list [] =  new  Bicycle[limit];

        int count = 0;
        for(Bicycle item : inventory){
            if (item.getType().equals(type)) {
                if (count + 1 > limit){
                    break;
                }
                list[count] =  item;
                count++;
            }
        }
        return list;
    }

    public Bicycle [] getChoices(int wheelsize, int limit) {
        Bicycle [] list =  new  Bicycle[limit];

        int count = 0;
        for(Bicycle item : inventory){
            if (item.getWheelSize() == wheelsize) {
                if (count + 1 > limit){
                    break;
                }
                list[count] =  item;
                count++;
            }
        }
        return list;
    }

    public Bicycle [] getChoices(boolean assembled, int limit) {
        Bicycle [] list =  new  Bicycle[limit];

        int count = 0;
        for(Bicycle item : inventory){
            if (item.getAssembled() == assembled) {
                if (count + 1 > limit){
                    break;
                }
                list[count] =  item;
                count++;
            }
        }
        return list;
    }

    public Bicycle [] getChoices(char gender, int limit) {
        Bicycle [] list =  new  Bicycle[limit];

        int count = 0;
        for(Bicycle item : inventory){
            if (item.getGender() == gender) {
                if (count + 1 > limit){
                    break;
                }
                list[count] =  item;
                count++;
            }
        }
        return list;
    }

    public Bicycle [] getChoices(String type, int wheelsize, boolean assembled, char gender, int limit) {
        Bicycle [] list =  new  Bicycle[limit];

        int count = 0;
        for(Bicycle item : inventory){
            if (count + 1 > limit){
                break;
            }
            if (item.getType() == type && item.getWheelSize() == wheelsize && item.getAssembled() == assembled && item.getGender() == gender) {
                list[count] =  item;
                count++;
            }
        }
        return list;
    }

    public Bicycle [] chooseOne(String type, int limit) {

        return getChoices(type, limit);

    }

    public Bicycle [] chooseOne(int wheelsize, int limit) {
       return getChoices(wheelsize, limit);
    }

    public Bicycle [] chooseOne(boolean assembled, int limit) {
        return getChoices(assembled, limit);
    }

    public Bicycle [] chooseOne(char gender, int limit) {
        return getChoices(gender, limit);
    }

    public Bicycle [] chooseOne(String type, int wheelsize, boolean assembled, char gender, int limit) {
        return getChoices(type,  wheelsize,  assembled,  gender, limit);
    }

    public void printSingle(Bicycle bicycle[]){
        for(Bicycle item : bicycle){
            if (item.getType() != "") {
                System.out.println(item.toString());
            }
        }
    }
    public void menuOptions() {
        System.out.println(
                "1- Choose one for me\n" +
                        "2- List inventory by wheelSize\n" +
                        "3- List inventory by type\n" +
                        "4- List inventory by gender\n" +
                        "5- List inventory by assembled\n" +
                        "6- List entire inventory\n" +
                        "7- Quit\n"
        );
        // Read an int value
        System.out.println("Enter Option: ");
        int num = sc.nextInt();
        if (num == 1) {
            determineSearch();
        }else  if (num > 1 && num <= 6) {
            outputLIst(num);
        } else  if (num == 7) {
            System.exit(0);
        }else {
            System.out.println("invalid Option selected.");
        }
    }

    public void determineSearch() {
        System.out.println(
                "1- Choose one by wheelSize\n" +
                        "2- Choose one by type\n" +
                        "3- Choose one by gender\n" +
                        "4- Choose one by assembled\n" +
                        "5- Choose one by all\n"
        );
        // Read an int value
        System.out.println("Enter Option: ");
        int num = sc.nextInt();

        if (num > 0 && num <= 5) {
            determineSearchCriteria(num);
        } else {
            System.out.println("invalid Option selected.");
        }
    }

    public void determineSearchCriteria(int option) {
        if (option == 1) {
            System.out.println("Enter wheel size : ");
            int size = sc.nextInt();
            System.out.println("Enter search limit : ");
            int limit = sc.nextInt();

            printSingle( chooseOne(size, limit));
        } else if (option == 2) {
            System.out.println("Enter type: ");
            String type = sc.next();
            System.out.println("Enter search limit : ");
            int limit = sc.nextInt();
            printSingle(chooseOne(type, limit));
        } else if (option == 3) {
            System.out.println("Enter gender(m/f): ");
            char gender = sc.next().charAt(0);
            System.out.println("Enter search limit : ");
            int limit = sc.nextInt();
            printSingle(chooseOne(gender, limit));
        } else if (option == 4) {
            System.out.println("Enter assembled(true/false): ");
            boolean assembled = sc.nextBoolean();
            System.out.println("Enter search limit : ");
            int limit = sc.nextInt();
            printSingle(chooseOne(assembled, limit));
        } else if (option == 5) {
            System.out.println("Enter wheel size : ");
            int size = sc.nextInt();
            System.out.println("Enter type: ");
            String type = sc.next();
            System.out.println("Enter gender(m/f): ");
            char gender = sc.next().charAt(0);
            System.out.println("Enter assembled(true/false): ");
            boolean assembled = sc.nextBoolean();
            System.out.println("Enter search limit : ");
            int limit = sc.nextInt();
            printSingle(chooseOne(type, size, assembled, gender, limit));
        }
    }

    public void outputLIst(int search) {
        switch (search) {
            case 2:
                System.out.println("Enter wheel size : ");
                int size = sc.nextInt();
                for (int i = 0; i < this.getSize(); i++) {
                    if (inventory[i].getWheelSize() == size) {
                        System.out.println(inventory[i].toString());
                    }
                }

                break;
            case 3:
                System.out.println("Enter type: ");
                String type = sc.next();
                for (int i = 0; i < this.getSize(); i++) {
                    if (inventory[i].getType().equals(type)) {
                        System.out.println(inventory[i].toString());
                    }
                }
                break;
            case 4:
                System.out.println("Enter gender(m/f): ");
                char gender = sc.next().charAt(0);
                for (int i = 0; i < this.getSize(); i++) {
                    if (inventory[i].getGender() == gender) {
                        System.out.println(inventory[i].toString());
                    }
                }
                break;
            case 5:
                System.out.println("Enter assembled(true/false): ");
                boolean assembled = sc.nextBoolean();
                for (int i = 0; i < this.getSize(); i++) {
                    if (inventory[i].getAssembled() == assembled) {
                        System.out.println(inventory[i].toString());
                    }

                }
                break;
            case 6:
                for (int i = 0; i < this.getSize(); i++) {
                    System.out.println(inventory[i].toString());
                }
                break;
        }
    }

}
