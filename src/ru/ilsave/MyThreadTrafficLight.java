package ru.ilsave;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

public class MyThreadTrafficLight extends Thread implements Closeable {

    @Override
    public void close() {
        this.interrupt();
    }

    private static final int ROAD_MINIMUM_DIFFERENCE = 7;
    private static final long TRAFFIC_LIGHT_FLASHING_GREEN = 7500L;
    private static final long TRAFFIC_LIGHT_NON_COLOR = 700L;
    private static final long TRAFFIC_LIGHT_GREEN_COLOR = 800L;

    private long firstTime = 15000L;
    private long middleTime = 5000L;
    private long lastTime = 15000L;

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
                Thread.sleep(firstTime-TRAFFIC_LIGHT_FLASHING_GREEN);
                for(int i = 0; i < 5; i++){
                    Thread.sleep(TRAFFIC_LIGHT_NON_COLOR);
                    trafficLightRightDown.state = LightState.NONE;
                    trafficLightLeftUp.state = LightState.NONE;
                    Thread.sleep(TRAFFIC_LIGHT_GREEN_COLOR);
                    trafficLightRightDown.state = LightState.GREEN;
                    trafficLightLeftUp.state = LightState.GREEN;
                }
//                trafficLightRightDown.state = LightState.FLASHINGGREEN;
//                trafficLightLeftUp.state = LightState.FLASHINGGREEN;
//                Thread.sleep(7500L);
                trafficLightRightUp.state = LightState.YELLOW;
                trafficLightLeftDown.state = LightState.YELLOW;
                trafficLightRightDown.state = LightState.YELLOW;
                trafficLightLeftUp.state = LightState.YELLOW;
                Thread.sleep(middleTime);
                trafficLightRightUp.state = LightState.GREEN;
                trafficLightLeftDown.state = LightState.GREEN;
                trafficLightRightDown.state = LightState.RED;
                trafficLightLeftUp.state = LightState.RED;
                Thread.sleep(lastTime-TRAFFIC_LIGHT_FLASHING_GREEN);
                for(int i = 0; i < 5; i++){
                    Thread.sleep(TRAFFIC_LIGHT_NON_COLOR);
                    trafficLightRightUp.state = LightState.NONE;
                    trafficLightLeftDown.state = LightState.NONE;
                    Thread.sleep(TRAFFIC_LIGHT_GREEN_COLOR);
                    trafficLightRightUp.state = LightState.GREEN;
                    trafficLightLeftDown.state = LightState.GREEN;
                }
//                trafficLightRightUp.state = LightState.FLASHINGGREEN;
//                trafficLightLeftDown.state = LightState.FLASHINGGREEN;
//                Thread.sleep(7500L);
                //checking the traffic situation
                checkTheRoads();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    void checkTheRoads() {
        int verticalTraffic =leftUpLightTrafficList.size() + rightDownLightTrafficList.size();
        int horizontalTraffic = leftDownLightTrafficList.size() + rightUpLightTrafficList.size();
        if (Math.abs(verticalTraffic - horizontalTraffic) > ROAD_MINIMUM_DIFFERENCE){
            return;
        }
        if (horizontalTraffic > 0) {
            if ((float)(verticalTraffic / horizontalTraffic) > 1.2){
                firstTime = 10000L;
                lastTime = 20000L;
                return;
            }
        }
        if (verticalTraffic > 0){
            if ((float)(horizontalTraffic / verticalTraffic) > 1.2){
                firstTime = 20000L;
                lastTime = 10000L;
                return;
            }
        }
        firstTime = 15000L;
        lastTime = 15000L;

    }

}
