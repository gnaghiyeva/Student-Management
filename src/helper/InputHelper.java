package helper;

import java.util.Scanner;

public class InputHelper {
    private static  final Scanner sc = new Scanner(System.in);

    public static String readText(String prompt){
        System.out.print(prompt);
        return sc.nextLine();
    }

    public static int readNumber(String prompt){
        while (true){
            System.out.print(prompt);
            if(sc.hasNextInt()){
                int value = sc.nextInt();
                sc.nextLine();
                return value;
            }
            else{
                System.out.println("Please enter the number.");
                sc.nextLine();
            }
        }


    }
}
