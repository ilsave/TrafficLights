package ru.ilsave;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LightInfo {

    private static Light svetoforHorizontal = new Light();
    private static Light svetoforVertical = new Light();

    private static final List<Car> leftUpLightTraffic = Collections.synchronizedList(new ArrayList<>());
    private static final List<Car> leftDownLightTraffic = Collections.synchronizedList(new ArrayList<>());
    private static final List<Car> rightUpLightTraffic = Collections.synchronizedList(new ArrayList<>());
    private static final List<Car> rightDownLightTraffic = Collections.synchronizedList(new ArrayList<>());

    private static int verticalTraffic;
    private static int horizontalTraffic;

    public static Light getSvetoforVertical() {
        return svetoforVertical;
    }

    public static void setSvetoforVertical(Light svetoforVertical) {
        LightInfo.svetoforVertical = svetoforVertical;
    }

    public static void setVerticalTraffic(int verticalTraffic) {
        LightInfo.verticalTraffic = verticalTraffic;
    }

    public static void setHorizontalTraffic(int horizontalTraffic) {
        LightInfo.horizontalTraffic = horizontalTraffic;
    }

    public static Light getSvetoforHorizontal() {
        return svetoforHorizontal;
    }

    public static void setSvetoforState(LightState svetoforState) {
        LightInfo.svetoforHorizontal.state = svetoforState;
    }

    public static void setSvetoforHorizontal(Light svetoforHorizontal) {
        LightInfo.svetoforHorizontal = svetoforHorizontal;
    }

    public static int getHorizontalTraffic() {
        return horizontalTraffic;
    }

    public static List<Car> getLeftUpLightTraffic() {
        return leftUpLightTraffic;
    }

    public static List<Car> getLeftDownLightTraffic() {
        return leftDownLightTraffic;
    }

    public static List<Car> getRightUpLightTraffic() {
        return rightUpLightTraffic;
    }

    public static List<Car> getRightDownLightTraffic() {
        return rightDownLightTraffic;
    }

    public static int getVerticalTraffic() {
        return verticalTraffic;
    }

    public static void addElementLeftUpLightTraffic(Car element) {
        LightInfo.leftUpLightTraffic.add(element);
        verticalTraffic = leftUpLightTraffic.size() + rightDownLightTraffic.size();
    }

    public static void addLeftUpLightTraffic(Car element) {
        LightInfo.leftUpLightTraffic.add(element);
        verticalTraffic = leftUpLightTraffic.size() + rightDownLightTraffic.size();
    }

    public static void addLeftDownLightTraffic(Car element) {
        LightInfo.leftDownLightTraffic.add(element);
        horizontalTraffic = leftDownLightTraffic.size() + rightUpLightTraffic.size();
    }

    public static void addRightUpLightTraffic(Car element) {
        LightInfo.rightUpLightTraffic.add(element);
        horizontalTraffic = rightUpLightTraffic.size() + leftDownLightTraffic.size();
    }

    public static void addRightDownLightTraffic(Car element) {
        LightInfo.rightDownLightTraffic.add(element);
        verticalTraffic = rightDownLightTraffic.size() + leftUpLightTraffic.size();
    }
}
