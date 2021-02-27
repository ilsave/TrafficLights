package ru.ilsave;

import java.util.List;
import java.util.Random;

public class MyThreadTimerDeleter extends Thread {

    private List<Car> leftUpLightTrafficList;
    private List<Car> leftDownLightTrafficList;
    private List<Car> rightUpLightTrafficList;
    private List<Car> rightDownLightTrafficList;


    private Light trafficLightLeftUp;
    private Light trafficLightRightDown;
    private Light trafficLightLeftDown;
    private Light trafficLightRightUp;


    void setAllArgs(List<Car> leftUpLightTraffic, List<Car> leftDownLightTraffic,
                    List<Car> rightUpLightTraffic, List<Car> rightDownLightTraffic) {
        this.leftUpLightTrafficList = leftUpLightTraffic;
        this.leftDownLightTrafficList = leftDownLightTraffic;
        this.rightDownLightTrafficList = rightDownLightTraffic;
        this.rightUpLightTrafficList = rightUpLightTraffic;
    }

    void setAllLightArgs(Light trafficLightVerticalLeftUp, Light trafficLightVerticalRightDown,
                         Light trafficLightHorizontalLeftDown, Light trafficLightHorizontalRightUp) {
        this.trafficLightLeftUp = trafficLightVerticalLeftUp;
        this.trafficLightLeftDown = trafficLightHorizontalLeftDown;
        this.trafficLightRightUp = trafficLightHorizontalRightUp;
        this.trafficLightRightDown = trafficLightVerticalRightDown;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            try {
                Thread.sleep(500L);
                if (trafficLightLeftUp.state == LightState.GREEN
                        && !leftUpLightTrafficList.isEmpty()) {
                    leftUpLightTrafficList.remove(0);
                }
                if (trafficLightRightDown.state == LightState.GREEN
                        && !rightDownLightTrafficList.isEmpty()) {
                    rightDownLightTrafficList.remove(0);
                }
                if (trafficLightLeftDown.state == LightState.GREEN
                        && !leftDownLightTrafficList.isEmpty()) {
                    leftDownLightTrafficList.remove(0);
                }
                if (trafficLightRightUp.state == LightState.GREEN
                        && !rightUpLightTrafficList.isEmpty()) {
                    rightUpLightTrafficList.remove(0);
                }
                Thread.sleep(500L);
                switch (random.nextInt(4)) {
                    case 0:
                        leftUpLightTrafficList.add(new Car("Bmw"));
                        break;

                    case 1:
                        rightDownLightTrafficList.add(new Car("Audi"));
                        break;

                    case 2:
                        leftDownLightTrafficList.add(new Car("Lada"));
                        break;

                    case 3:
                        rightUpLightTrafficList.add(new Car("Honda"));
                        break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
