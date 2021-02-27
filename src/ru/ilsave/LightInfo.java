package ru.ilsave;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LightInfo {

    private static Light trafficLightVerticalLeftUp;
    private static Light trafficLightVerticalRightDown;
    private static Light trafficLightHorizontalLeftDown;
    private static Light trafficLightHorizontalRightUp;

    private static final List<Car> leftUpLightTrafficRoad = Collections.synchronizedList(new ArrayList<>());
    private static final List<Car> leftDownLightTrafficRoad = Collections.synchronizedList(new ArrayList<>());
    private static final List<Car> rightUpLightTrafficRoad = Collections.synchronizedList(new ArrayList<>());
    private static final List<Car> rightDownLightTrafficRoad = Collections.synchronizedList(new ArrayList<>());

    private static int verticalTraffic;
    private static int horizontalTraffic;

    public static void initProperties(){
        trafficLightVerticalLeftUp = new Light();
        trafficLightVerticalRightDown = new Light();
        trafficLightHorizontalLeftDown = new Light();
        trafficLightHorizontalRightUp = new Light();
    }


    public static Light getTrafficLightHorizontalLeftDown() {
        return trafficLightHorizontalLeftDown;
    }

    public static void setTrafficLightHorizontalLeftDown(Light trafficLightHorizontalLeftDown) {
        LightInfo.trafficLightHorizontalLeftDown = trafficLightHorizontalLeftDown;
    }

    public static Light getTrafficLightHorizontalRightUp() {
        return trafficLightHorizontalRightUp;
    }

    public static void setTrafficLightHorizontalRightUp(Light trafficLightHorizontalRightUp) {
        LightInfo.trafficLightHorizontalRightUp = trafficLightHorizontalRightUp;
    }

    public static Light getTrafficLightVerticalRightDown() {
        return trafficLightVerticalRightDown;
    }

    public static void setTrafficLightVerticalRightDown(Light trafficLightVerticalRightDown) {
        LightInfo.trafficLightVerticalRightDown = trafficLightVerticalRightDown;
    }

    public static void setVerticalTraffic(int verticalTraffic) {
        LightInfo.verticalTraffic = verticalTraffic;
    }

    public static void setHorizontalTraffic(int horizontalTraffic) {
        LightInfo.horizontalTraffic = horizontalTraffic;
    }

    public static Light getTrafficLightVerticalLeftUp() {
        return trafficLightVerticalLeftUp;
    }

    public static void setSvetoforState(LightState svetoforState) {
        LightInfo.trafficLightVerticalLeftUp.state = svetoforState;
    }

    public static void setTrafficLightVerticalLeftUp(Light trafficLightVerticalLeftUp) {
        LightInfo.trafficLightVerticalLeftUp = trafficLightVerticalLeftUp;
    }

    public static int getHorizontalTraffic() {
        return horizontalTraffic;
    }

    public static List<Car> getLeftUpLightTrafficRoad() {
        return leftUpLightTrafficRoad;
    }

    public static List<Car> getLeftDownLightTrafficRoad() {
        return leftDownLightTrafficRoad;
    }

    public static List<Car> getRightUpLightTrafficRoad() {
        return rightUpLightTrafficRoad;
    }

    public static List<Car> getRightDownLightTrafficRoad() {
        return rightDownLightTrafficRoad;
    }

    public static int getVerticalTraffic() {
        return verticalTraffic;
    }

    public static void addElementLeftUpLightTraffic(Car element) {
        LightInfo.leftUpLightTrafficRoad.add(element);
        verticalTraffic = leftUpLightTrafficRoad.size() + rightDownLightTrafficRoad.size();
    }

    public static void addLeftUpLightTraffic(Car element) {
        LightInfo.leftUpLightTrafficRoad.add(element);
        verticalTraffic = leftUpLightTrafficRoad.size() + rightDownLightTrafficRoad.size();
    }

    public static void addLeftDownLightTraffic(Car element) {
        LightInfo.leftDownLightTrafficRoad.add(element);
        horizontalTraffic = leftDownLightTrafficRoad.size() + rightUpLightTrafficRoad.size();
    }

    public static void addRightUpLightTraffic(Car element) {
        LightInfo.rightUpLightTrafficRoad.add(element);
        horizontalTraffic = rightUpLightTrafficRoad.size() + leftDownLightTrafficRoad.size();
    }

    public static void addRightDownLightTraffic(Car element) {
        LightInfo.rightDownLightTrafficRoad.add(element);
        verticalTraffic = rightDownLightTrafficRoad.size() + leftUpLightTrafficRoad.size();
    }
}
