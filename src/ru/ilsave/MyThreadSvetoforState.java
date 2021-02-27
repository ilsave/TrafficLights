package ru.ilsave;

import java.util.List;

public class MyThreadSvetoforState extends Thread{

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
        while(true){
            trafficLightRightUp.state = LightState.RED;
            trafficLightLeftDown.state = LightState.RED;
            trafficLightRightDown.state = LightState.GREEN;
            trafficLightLeftUp.state = LightState.GREEN;
            try {
                Thread.sleep(15000L);
                trafficLightRightUp.state = LightState.YELLOW;
                trafficLightLeftDown.state = LightState.YELLOW;
                trafficLightRightDown.state = LightState.YELLOW;
                trafficLightLeftUp.state = LightState.YELLOW;
                Thread.sleep(5000L);
                trafficLightRightUp.state = LightState.GREEN;
                trafficLightLeftDown.state = LightState.GREEN;
                trafficLightRightDown.state = LightState.RED;
                trafficLightLeftUp.state = LightState.RED;
                Thread.sleep(15000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
