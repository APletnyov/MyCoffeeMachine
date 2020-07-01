package machine;

import java.util.Scanner;

public class CoffeeMachine {

    static int waterSupplyMl  = 400;
    static int milkSupplyMl   = 540;
    static int coffeeSupplyGr = 120;
    static int cupsPiece      = 9;
    static int moneyDollars   = 550;

    static int waterMlEspressoOneCup      = 250;
    static int milkMlEspressoOneCup       = 0;
    static int coffeeGrEspressoOneCup     = 16;
    static int priceDollarsEspressoOneCup = 4;

    static int waterMlLatteOneCup      = 350;
    static int milkMlLatteOneCup       = 75;
    static int coffeeGrLatteOneCup     = 20;
    static int priceDollarsLatteOneCup = 7;

    static int waterMlCappuccinoOneCup      = 200;
    static int milkMlCappuccinoOneCup       = 100;
    static int coffeeGrCappuccinoOneCup     = 12;
    static int priceDollarsCappuccinoOneCup = 6;

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        boolean continueAsking = true;
        while (continueAsking){
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            String action = sc.next();
            switch (action){
                case ("buy"):
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, " +
                            "back - to main menu:");
                    String coffeeType = sc.next();
                    switch (coffeeType){
                        case "1":
                            makingACup(waterMlEspressoOneCup, milkMlEspressoOneCup, coffeeGrEspressoOneCup,
                                    priceDollarsEspressoOneCup);
                            break;
                        case "2":
                            makingACup(waterMlLatteOneCup, milkMlLatteOneCup, coffeeGrLatteOneCup,
                                    priceDollarsLatteOneCup);
                            break;
                        case "3":
                            makingACup(waterMlCappuccinoOneCup, milkMlCappuccinoOneCup, coffeeGrCappuccinoOneCup,
                                    priceDollarsCappuccinoOneCup);
                            break;
                        case "back":
                            break;
                        default:
                            System.out.println("Sorry, wrong number.");
                            break;
                    }
                    break;
                case ("fill"):
                    fill();
                    break;
                case ("take"):
                    System.out.println("I gave you $" + moneyDollars);
                    moneyDollars = 0;
                    break;
                case ("remaining"):
                    printCurrentState();
                    break;
                case ("exit"):
                    continueAsking = false;
                    break;
                default:
                    System.out.println("Sorry, wrong action.");
                    break;
            }
        }
    }

    public static void makingACup(int waterOneCup, int milkOneCup, int coffeeOneCup, int priceOneCup) {
        boolean success = true;
        String  message = "";
        if (cupsPiece-1<0){
            success = false;
            message = "disposable cups";
        }
        if (waterSupplyMl-waterOneCup<0){
            success = false;
            message = (message.equals("")) ? "water" : message + ", water";
        }
        if (milkSupplyMl-milkOneCup<0) {
            success = false;
            message = (message.equals("")) ? "milk" : message + ", milk";
        }
        if (coffeeSupplyGr-coffeeOneCup<0) {
            success = false;
            message = (message.equals("")) ? "coffee beans" : message + ", coffee beans";
        }
        if (success){
            System.out.println("I have enough resources, making you a coffee!");
            cupsPiece--;
            waterSupplyMl  -= waterOneCup;
            milkSupplyMl   -= milkOneCup;
            coffeeSupplyGr -= coffeeOneCup;
            moneyDollars   += priceOneCup;
        } else {
            message = message + "!";
            System.out.println("Sorry, not enough " + message);
        }
    }

    public static void fill(){
        System.out.println("Write how many ml of water do you want to add:");
        int q = sc.nextInt();
        waterSupplyMl   += q;
        System.out.println("Write how many ml of milk do you want to add:");
        q = sc.nextInt();
        milkSupplyMl    += q;
        System.out.println("Write how many grams of coffee beans do you want to add:");
        q = sc.nextInt();
        coffeeSupplyGr  += q;
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        q = sc.nextInt();
        cupsPiece       += q;
    }

    public static void printCurrentState() {
        System.out.println("The coffee machine has:");
        System.out.println(waterSupplyMl  + " of water");
        System.out.println(milkSupplyMl   + " of milk");
        System.out.println(coffeeSupplyGr + " of coffee beans");
        System.out.println(cupsPiece      + " of disposable cups");
        System.out.println("$" + moneyDollars   + " of money");
    }
}

enum MachineState{
    CHOOSINGANACTION,
    CHOOSINGAVARIANTOFCOFFEE,
    FILLINGWATER,
    FILLINGMILK,
    FILLINGCOFFEEBEANS,
    FILLINGCUPS
}