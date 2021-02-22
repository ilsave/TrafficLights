package ru.ilsave;

import java.util.Random;

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
        new Thread(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(true){
                            LightInfo.getSvetoforHorizontal().state = LightState.RED;
                            LightInfo.getSvetoforVertical().state = LightState.GREEN;
                            try {
                                Thread.sleep(15000L);
                                LightInfo.getSvetoforHorizontal().state = LightState.YELLOW;
                                LightInfo.getSvetoforVertical().state = LightState.YELLOW;
                                Thread.sleep(5000L);
                                LightInfo.getSvetoforHorizontal().state = LightState.GREEN;
                                LightInfo.getSvetoforVertical().state = LightState.RED;
                                Thread.sleep(15000L);
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
                            if (LightInfo.getSvetoforHorizontal().state == LightState.GREEN
                                    && !LightInfo.getRightDownLightTraffic().isEmpty()){
                                try {
                                    Thread.sleep(500L);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                LightInfo.getRightDownLightTraffic().remove(0);
                                if (!LightInfo.getLeftUpLightTraffic().isEmpty()){
                                    LightInfo.getLeftUpLightTraffic().remove(0);
                                }
                            }
                        }
                    }
                }).start();

                while (true) {
                    System.out.println("            |    |    |     ");
                    System.out.println("            |    |    |     ");
                    System.out.println("           "+LightInfo.getLeftUpLightTraffic().size()+      "| |  |    |     ");
                    System.out.println("         "+getState(LightInfo.getSvetoforHorizontal())+   "| ↓  |    |     ");
                    System.out.println("————————————┘         └————————————");
                    System.out.println("                       ←———         ");
                    System.out.println("————————————           ————————————");
                    System.out.println("        ———→                      ");
                    System.out.println("————————————┐         ┌————————————");
                    System.out.println("            |    | ↑  |" + getState(LightInfo.getSvetoforHorizontal()));
                    System.out.println("            |    | |  |" + LightInfo.getRightDownLightTraffic().size());
                    System.out.println("            |    |    |     ");
                    System.out.println("            |    |    |     ");
                    System.out.println();
                    switch (random.nextInt(3)) {
                        case 0:
                            if (LightInfo.getRightDownLightTraffic().size() < 9) {
                                LightInfo.addRightDownLightTraffic(new Car("Bmw"));
                            }
                            break;
                        case 1:
                            if (LightInfo.getLeftUpLightTraffic().size() < 9){
                                LightInfo.addLeftUpLightTraffic(new Car("Bmw"));
                            }
//                            if (!LightInfo.getRightDownLightTraffic().isEmpty()) {
//                                LightInfo.getRightDownLightTraffic().remove(LightInfo.getRightDownLightTraffic().size() - 1);
//                            }
                            break;
                        case 2:
                           // System.out.println("Bingo!");
                            break;
                    }

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (int i = 0; i < 30; i++) {
                        System.out.println();
                    }
                }
            }
        }).start();
    }
    static String getState(Light svet){
        return switch (svet.state){
            case RED -> "Red";
            case GREEN -> "Grn";
            case YELLOW -> "Ylw";
            case FLASHINGGREEN -> "FlG";
            case BROKEN -> "Throw new exception";
        };
    }
}
