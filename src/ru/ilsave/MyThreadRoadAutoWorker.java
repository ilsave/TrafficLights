package ru.ilsave;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class MyThreadRoadAutoWorker extends Thread implements Closeable {

    @Override
    public void close() {
        this.interrupt();
    }

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
                if ((trafficLightLeftUp.state == LightState.GREEN || trafficLightLeftUp.state == LightState.FLASHINGGREEN)
                        && !leftUpLightTrafficList.isEmpty()) {
                    leftUpLightTrafficList.remove(0);
                }
                if ((trafficLightRightDown.state == LightState.GREEN || trafficLightRightDown.state == LightState.FLASHINGGREEN)
                        && !rightDownLightTrafficList.isEmpty()) {
                    rightDownLightTrafficList.remove(0);
                }
                if ((trafficLightLeftDown.state == LightState.GREEN || trafficLightLeftDown.state == LightState.FLASHINGGREEN)
                        && !leftDownLightTrafficList.isEmpty()) {
                    leftDownLightTrafficList.remove(0);
                }
                if ((trafficLightRightUp.state == LightState.GREEN || trafficLightRightUp.state == LightState.FLASHINGGREEN)
                        && !rightUpLightTrafficList.isEmpty()) {
                    rightUpLightTrafficList.remove(0);
                }
                Thread.sleep(500L);
                switch (random.nextInt(4)) {
                    case 0:
                        if(leftUpLightTrafficList.size() < 9){
                            leftUpLightTrafficList.add(new Car("Bmw"));
                        }
                        if(leftDownLightTrafficList.size() < 9){
                            leftDownLightTrafficList.add(new Car("Kamaz"));
                        }
                        break;
                    case 1:
//                        if(rightDownLightTrafficList.size() < 9){
                            rightDownLightTrafficList.add(new Car("Audi"));
//                        }
//                        if(rightUpLightTrafficList.size() < 9){
                            rightUpLightTrafficList.add(new Car("ds"));
//                        }
                        break;

                    case 2:
//                        if(leftDownLightTrafficList.size() < 9){
                            leftDownLightTrafficList.add(new Car("Lada"));
//                        }
//                        if(leftUpLightTrafficList.size() < 9){
                            leftUpLightTrafficList.add(new Car("Bmw"));
//                        }
                        break;

                    case 3:
//                        if(rightUpLightTrafficList.size() < 9){
                            rightUpLightTrafficList.add(new Car("Honda"));
//                        }
//                        if(rightDownLightTrafficList.size() < 9){
                            rightDownLightTrafficList.add(new Car("Mazda"));
//                        }
                        break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
