package com.example.test;

import java.util.Scanner;

/**
 * @author hnn
 * @date 2021/02/19
 */
public class IoClass {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (true){
            String input= scanner.next();
            if("exit".equals(input)){
                System.out.println("bye!");
                break;
            }
            System.out.println(input);
        }
    }
}
