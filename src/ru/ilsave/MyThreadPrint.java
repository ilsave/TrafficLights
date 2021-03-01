package ru.ilsave;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.Observable;

public class MyThreadPrint extends Thread implements Closeable {

    @Override
    public void close() {
        this.interrupt();
    }

    private static final long BASIC_DELAY = 1000L;

    private List<Car> leftUpLightTrafficList;
    private List<Car> leftDownLightTrafficList;
    private List<Car> rightUpLightTrafficList;
    private List<Car> rightDownLightTrafficList;


    private Light trafficLightLeftUp;
    private Light trafficLightRightDown;
    private Light trafficLightLeftDown;
    private Light trafficLightRightUp;

    void setAllArgs(
            List<Car> leftUpLightTraffic,
            List<Car> leftDownLightTraffic,
            List<Car> rightUpLightTraffic,
            List<Car> rightDownLightTraffic
    ) {
        this.leftUpLightTrafficList = leftUpLightTraffic;
        this.leftDownLightTrafficList = leftDownLightTraffic;
        this.rightDownLightTrafficList = rightDownLightTraffic;
        this.rightUpLightTrafficList = rightUpLightTraffic;
    }

    void setAllLightArgs(
            Light trafficLightVerticalLeftUp,
            Light trafficLightVerticalRightDown,
            Light trafficLightHorizontalLeftDown,
            Light trafficLightHorizontalRightUp
    ) {
        this.trafficLightLeftUp = trafficLightVerticalLeftUp;
        this.trafficLightLeftDown = trafficLightHorizontalLeftDown;
        this.trafficLightRightUp = trafficLightHorizontalRightUp;
        this.trafficLightRightDown = trafficLightVerticalRightDown;
    }

    @Override
    public void run() {
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            try {
                Thread.sleep(BASIC_DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stringBuilder.append("            |    |    |     ").append("\n");
            stringBuilder.append("            |    |    |     ").append("\n");
            stringBuilder.append("           ").append(customListSize(leftUpLightTrafficList)).append("| |  |    |").append(customListSize(rightUpLightTrafficList)).append("\n");
            stringBuilder.append("         ").append(getState(trafficLightLeftUp)).append("| ↓  |    |").append(getState(trafficLightRightUp)).append("\n");
            stringBuilder.append("————————————┘         └————————————").append("\n");
            stringBuilder.append("                       ←———         ").append("\n");
            stringBuilder.append("————————————           ————————————").append("\n");
            stringBuilder.append("        ———→                      ").append("\n");
            stringBuilder.append("————————————┐         ┌————————————").append("\n");
            stringBuilder.append("         ").append(getState(trafficLightLeftDown)).append("|    | ↑  |").append(getState(trafficLightRightDown)).append("\n");
            stringBuilder.append("           ").append(customListSize(leftDownLightTrafficList)).append("|    | |  |").append(customListSize(rightDownLightTrafficList)).append("\n");
            stringBuilder.append("            |    |    |     ").append("\n");
            stringBuilder.append("            |    |    |     ").append("\n");
            stringBuilder.append(" ").append("\n");
            System.out.println(stringBuilder);
        }
    }

    private int customListSize(List<Car> list){
        if (list.size() < 9) return list.size();
        else return 9;
    }

    static String getState(Light svet){
        return switch (svet.state) {
            case RED -> "Red";
            case GREEN -> "Grn";
            case YELLOW -> "Ylw";
            case FLASHINGGREEN -> "FlG";
            case NONE ->  "NON";
            case BROKEN -> "It's STATE OF EMERGENCY, CALL THE SERVICE, RIGHT NOW!";
        };
    }

}
