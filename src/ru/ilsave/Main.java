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
        LightInfo.initProperties();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(true){
                            LightInfo.getSvetoforVerticalLeftUp().state = LightState.GREEN;
                            LightInfo.getSvetoforVerticalRightDown().state = LightState.GREEN;
                            LightInfo.getSvetoforHorizontalLeftDown().state = LightState.RED;
                            LightInfo.getSvetoforHorizontalRightUp().state = LightState.RED;
                            try {
                                Thread.sleep(15000L);
                                LightInfo.getSvetoforVerticalLeftUp().state = LightState.YELLOW;
                                LightInfo.getSvetoforVerticalRightDown().state = LightState.YELLOW;
                                LightInfo.getSvetoforHorizontalLeftDown().state = LightState.YELLOW;
                                LightInfo.getSvetoforHorizontalRightUp().state = LightState.YELLOW;
                                Thread.sleep(5000L);
                                LightInfo.getSvetoforVerticalLeftUp().state = LightState.RED;
                                LightInfo.getSvetoforVerticalRightDown().state = LightState.RED;
                                LightInfo.getSvetoforHorizontalLeftDown().state = LightState.GREEN;
                                LightInfo.getSvetoforHorizontalRightUp().state = LightState.GREEN;
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
                            if (LightInfo.getSvetoforVerticalLeftUp().state == LightState.GREEN
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

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(true){
                            if (LightInfo.getSvetoforHorizontalLeftDown().state == LightState.GREEN
                                    && LightInfo.getLeftDownLightTraffic().size() > 0){
                                try {
                                    Thread.sleep(500L);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                LightInfo.getLeftDownLightTraffic().remove(0);
                                if (LightInfo.getRightUpLightTraffic().size() > 0){
                                    LightInfo.getRightUpLightTraffic().remove(0);
                                }
                            }
                        }
                    }
                }).start();

                while (true) {
                    System.out.println("            |    |    |     ");
                    System.out.println("            |    |    |     ");
                    System.out.println("           "+LightInfo.getLeftUpLightTraffic().size()+        "| |  |    |"+LightInfo.getRightUpLightTraffic().size());
                    System.out.println("         "+getState(LightInfo.getSvetoforVerticalLeftUp())+   "| ↓  |    |"+getState(LightInfo.getSvetoforHorizontalRightUp()));
                    System.out.println("————————————┘         └————————————");
                    System.out.println("                       ←———         ");
                    System.out.println("————————————           ————————————");
                    System.out.println("        ———→                      ");
                    System.out.println("————————————┐         ┌————————————");
                    System.out.println("         "+getState(LightInfo.getSvetoforHorizontalLeftDown())+"|    | ↑  |" + getState(LightInfo.getSvetoforVerticalLeftUp()));
                    System.out.println("           "+LightInfo.getLeftDownLightTraffic().size()+       "|    | |  |" + LightInfo.getRightDownLightTraffic().size());
                    System.out.println("            |    |    |     ");
                    System.out.println("            |    |    |     ");
                    System.out.println();
                    switch (random.nextInt(4)) {
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
                            if (LightInfo.getLeftDownLightTraffic().size() < 9){
                                LightInfo.addLeftDownLightTraffic(new Car("Bmw"));
                            }
                            break;

                        case 3:
                            if (LightInfo.getRightUpLightTraffic().size() < 9){
                                LightInfo.addRightUpLightTraffic(new Car("Bmw"));
                            }
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
