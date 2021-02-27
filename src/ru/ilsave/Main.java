package ru.ilsave;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

/*
1) threads in separated files
2) DI
3) implement all
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
        Light trafficLightVerticalLeftUp = new Light();
        Light trafficLightVerticalRightDown = new Light();
        Light trafficLightHorizontalLeftDown = new Light();
        Light trafficLightHorizontalRightUp = new Light();

        final List<Car> leftUpLightTraffic = Collections.synchronizedList(new ArrayList<>());
        final List<Car> leftDownLightTraffic = Collections.synchronizedList(new ArrayList<>());
        final List<Car> rightUpLightTraffic = Collections.synchronizedList(new ArrayList<>());
        final List<Car> rightDownLightTraffic = Collections.synchronizedList(new ArrayList<>());

        MyThreadTimerDeleter myThreadTimerDeleter = new MyThreadTimerDeleter();
        myThreadTimerDeleter.setAllArgs(leftUpLightTraffic, leftDownLightTraffic, rightUpLightTraffic, rightDownLightTraffic);
        myThreadTimerDeleter.setAllLightArgs(trafficLightVerticalLeftUp, trafficLightVerticalRightDown, trafficLightHorizontalLeftDown, trafficLightHorizontalRightUp);
        myThreadTimerDeleter.start();

        MyThreadSvetoforState myThreadSvetoforState = new MyThreadSvetoforState();
        myThreadSvetoforState.setAllArgs(leftUpLightTraffic, leftDownLightTraffic, rightUpLightTraffic, rightDownLightTraffic);
        myThreadSvetoforState.setAllLightArgs(trafficLightVerticalLeftUp, trafficLightVerticalRightDown, trafficLightHorizontalLeftDown, trafficLightHorizontalRightUp);
        myThreadSvetoforState.start();

        Thread.sleep(5000l);

        MyThreadPrint myThreadPrint = new MyThreadPrint();
        myThreadPrint.setAllArgs(leftUpLightTraffic, leftDownLightTraffic, rightUpLightTraffic, rightDownLightTraffic);
        myThreadTimerDeleter.setAllLightArgs(trafficLightVerticalLeftUp, trafficLightVerticalRightDown, trafficLightHorizontalLeftDown, trafficLightHorizontalRightUp);
        myThreadPrint.start();


        try {
            myThreadTimerDeleter.join();
            myThreadPrint.join();
            myThreadSvetoforState.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
//        });


//        int verticalTraffic;
//        int horizontalTraffic;

//        MyThreadSvetoforState myThreadSvetoforState = new MyThreadSvetoforState();
//        myThreadSvetoforState.setAllLightArgs(trafficLightVerticalLeftUp,trafficLightVerticalRightDown,trafficLightHorizontalLeftDown, trafficLightHorizontalRightUp);
//        myThreadSvetoforState.start();
//
//        new Thread(new Runnable() {
//            //MyThreadSvetoforState
//            @Override
//            public void run() {
//                Random random = new Random();
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        while(true){
//                            LightInfo.getTrafficLightVerticalLeftUp().state = LightState.GREEN;
//                            LightInfo.getTrafficLightVerticalRightDown().state = LightState.GREEN;
//                            LightInfo.getTrafficLightHorizontalLeftDown().state = LightState.RED;
//                            LightInfo.getTrafficLightHorizontalRightUp().state = LightState.RED;
//                            try {
//                                Thread.sleep(15000L);
//                                LightInfo.getTrafficLightVerticalLeftUp().state = LightState.YELLOW;
//                                LightInfo.getTrafficLightVerticalRightDown().state = LightState.YELLOW;
//                                LightInfo.getTrafficLightHorizontalLeftDown().state = LightState.YELLOW;
//                                LightInfo.getTrafficLightHorizontalRightUp().state = LightState.YELLOW;
//                                Thread.sleep(5000L);
//                                LightInfo.getTrafficLightVerticalLeftUp().state = LightState.RED;
//                                LightInfo.getTrafficLightVerticalRightDown().state = LightState.RED;
//                                LightInfo.getTrafficLightHorizontalLeftDown().state = LightState.GREEN;
//                                LightInfo.getTrafficLightHorizontalRightUp().state = LightState.GREEN;
//                                Thread.sleep(15000L);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                }).start();
//
//                //done!
//                new Thread(new Runnable() {
//                    //Timer delete the cars
//                    @Override
//                    public void run() {
//                        while(true){
//                            if (LightInfo.getTrafficLightVerticalLeftUp().state == LightState.GREEN
//                                    && !LightInfo.getRightDownLightTrafficRoad().isEmpty()){
//                                try {
//                                    Thread.sleep(500L);
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }
//                                LightInfo.getRightDownLightTrafficRoad().remove(0);
//                                if (!LightInfo.getLeftUpLightTrafficRoad().isEmpty()){
//                                    LightInfo.getLeftUpLightTrafficRoad().remove(0);
//                                }
//                            }
//                        }
//                    }
//                }).start();
//
//                //done!
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        while(true){
//                            if (LightInfo.getTrafficLightHorizontalLeftDown().state == LightState.GREEN
//                                    && LightInfo.getLeftDownLightTrafficRoad().size() > 0){
//                                try {
//                                    Thread.sleep(500L);
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }
//                                LightInfo.getLeftDownLightTrafficRoad().remove(0);
//                                if (LightInfo.getRightUpLightTrafficRoad().size() > 0){
//                                    LightInfo.getRightUpLightTrafficRoad().remove(0);
//                                }
//                            }
//                        }
//                    }
//                }).start();
//
//
//                //adding in time deleter
//                while (true) {
//                    System.out.println("            |    |    |     ");
//                    System.out.println("            |    |    |     ");
//                    System.out.println("           "+LightInfo.getLeftUpLightTrafficRoad().size()+        "| |  |    |"+LightInfo.getRightUpLightTrafficRoad().size());
//                    System.out.println("         "+getState(LightInfo.getTrafficLightVerticalLeftUp())+   "| ↓  |    |"+getState(LightInfo.getTrafficLightHorizontalRightUp()));
//                    System.out.println("————————————┘         └————————————");
//                    System.out.println("                       ←———         ");
//                    System.out.println("————————————           ————————————");
//                    System.out.println("        ———→                      ");
//                    System.out.println("————————————┐         ┌————————————");
//                    System.out.println("         "+getState(LightInfo.getTrafficLightHorizontalLeftDown())+"|    | ↑  |" + getState(LightInfo.getTrafficLightVerticalLeftUp()));
//                    System.out.println("           "+LightInfo.getLeftDownLightTrafficRoad().size()+       "|    | |  |" + LightInfo.getRightDownLightTrafficRoad().size());
//                    System.out.println("            |    |    |     ");
//                    System.out.println("            |    |    |     ");
//                    System.out.println();
//                    switch (random.nextInt(4)) {
//                        case 0:
//                            if (LightInfo.getRightDownLightTrafficRoad().size() < 9) {
//                                LightInfo.addRightDownLightTraffic(new Car("Bmw"));
//                            }
//                            break;
//                        case 1:
//                            if (LightInfo.getLeftUpLightTrafficRoad().size() < 9){
//                                LightInfo.addLeftUpLightTraffic(new Car("Bmw"));
//                            }
////                            if (!LightInfo.getRightDownLightTraffic().isEmpty()) {
////                                LightInfo.getRightDownLightTraffic().remove(LightInfo.getRightDownLightTraffic().size() - 1);
////                            }
//                            break;
//                        case 2:
//                            if (LightInfo.getLeftDownLightTrafficRoad().size() < 9){
//                                LightInfo.addLeftDownLightTraffic(new Car("Bmw"));
//                            }
//                            break;
//
//                        case 3:
//                            if (LightInfo.getRightUpLightTrafficRoad().size() < 9){
//                                LightInfo.addRightUpLightTraffic(new Car("Bmw"));
//                            }
//                            break;
//                    }
//
//                    try {
//                        Thread.sleep(500);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
//
//
//    }
//    static String getState(Light svet){
//        return switch (svet.state){
//            case RED -> "Red";
//            case GREEN -> "Grn";
//            case YELLOW -> "Ylw";
//            case FLASHINGGREEN -> "FlG";
//            case BROKEN -> "Throw new exception";
//        };
//    }
}
