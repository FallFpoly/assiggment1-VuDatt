
package com.mycompany.assigment12;

import java.util.Scanner;

public class Assigment1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n----- MENU -----");
            System.out.println("1. Chuc nang 1");
            System.out.println("2. Chuc nang 2");
            System.out.println("3. Chuc nang 3");
            System.out.println("4. chuc nang 4");
            System.out.println("5. chuc nang 5");
            System.out.println("6. chuc nang 6");
            System.out.println("7. Thoat");
            System.out.print("Chon chuc nang : ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Ban chon chuc nang 1");
                    
                    break;
                case 2:
                    System.out.println("Ban chon chuc nang 2");
                    
                    break;
                case 3:
                    System.out.println("Ban chon chuc nang 3");
                    
                    break;
                case 4:
                    System.out.println("Ban chon chuc nang 4");
                    
                    break; 
                case 5:
                    System.out.println("Ban chon chuc nang 5");
                    
                    break;  
                case 6:
                    System.out.println("Ban chon chuc nang 6");
                    
                    break;    
                case 7:
                    System.out.println("Thoat chuong trinh");
                    break;
                default:
                    System.out.println("Vui long chon lai");
            }
        } while (choice != 7);

        sc.close();
    }
}