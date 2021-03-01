package ru.ilsave;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args)  {


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

        MyThreadTrafficLight myThreadTrafficLight = new MyThreadTrafficLight();
        myThreadTrafficLight.setAllArgs(
                leftUpLightTraffic,
                leftDownLightTraffic,
                rightUpLightTraffic,
                rightDownLightTraffic);
        myThreadTrafficLight.setAllLightArgs(
                trafficLightVerticalLeftUp,
                trafficLightVerticalRightDown,
                trafficLightHorizontalLeftDown,
                trafficLightHorizontalRightUp);

        MyThreadPrint myThreadPrint = new MyThreadPrint();
        myThreadPrint.setAllArgs(leftUpLightTraffic, leftDownLightTraffic, rightUpLightTraffic, rightDownLightTraffic);
        myThreadPrint.setAllLightArgs(
                trafficLightVerticalLeftUp,
                trafficLightVerticalRightDown,
                trafficLightHorizontalLeftDown,
                trafficLightHorizontalRightUp);

        Thread hook = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    myThreadPrint.close();
                    myThreadRoadAutoWorker.close();
                    myThreadTrafficLight.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println("Program successfully closed. Thank you for your attention!");
            }
        });
        Runtime.getRuntime().addShutdownHook(hook);

        myThreadRoadAutoWorker.start();
        myThreadTrafficLight.start();
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
