package ru.ilsave;

import java.io.IOException;

/*
1) сколько потоков в программе?
     1 - отрисовка машин
     2 - время
     3 - генератор машин  - зависит от текущего времени, утро или вечер - много машин,
            днем и утром - мало машин
2) как рисовать?
3) че по файлам?
 */
public class Main {

    public static void main(String[] args) {
	// write your code here
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    System.out.print("/");
                    try {
                        Thread.sleep(2000);
                        System.out.println("\f");
//                        System.out.print("\033[H\033[2J");
//                        System.out.flush();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println((int) (Math.random()*10));
                }
            }
        }).start();
    }
}
