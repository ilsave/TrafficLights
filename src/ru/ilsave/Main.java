package ru.ilsave;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

        MyThreadRoadAutoWorker myThreadRoadAutoWorker = new MyThreadRoadAutoWorker();
        myThreadRoadAutoWorker.setAllArgs(leftUpLightTraffic,
                leftDownLightTraffic,
                rightUpLightTraffic,
                rightDownLightTraffic);
        myThreadRoadAutoWorker.setAllLightArgs(trafficLightVerticalLeftUp,
                trafficLightVerticalRightDown,
                trafficLightHorizontalLeftDown,
                trafficLightHorizontalRightUp);
        myThreadRoadAutoWorker.start();

        MyThreadTrafficLight myThreadTrafficLight = new MyThreadTrafficLight();
        myThreadTrafficLight.setAllArgs(
                leftUpLightTraffic, leftDownLightTraffic, rightUpLightTraffic, rightDownLightTraffic);
        myThreadTrafficLight.setAllLightArgs(
                trafficLightVerticalLeftUp,
                trafficLightVerticalRightDown,
                trafficLightHorizontalLeftDown,
                trafficLightHorizontalRightUp);
        myThreadTrafficLight.start();

        MyThreadPrint myThreadPrint = new MyThreadPrint();
        myThreadPrint.setAllArgs(leftUpLightTraffic, leftDownLightTraffic, rightUpLightTraffic, rightDownLightTraffic);
        myThreadPrint.setAllLightArgs(
                trafficLightVerticalLeftUp,
                trafficLightVerticalRightDown,
                trafficLightHorizontalLeftDown,
                trafficLightHorizontalRightUp);
        myThreadPrint.start();

        try {
            myThreadRoadAutoWorker.join();
            myThreadPrint.join();
            myThreadTrafficLight.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
