package ru.ilsave;

import java.util.List;

public class MyThreadPrint extends Thread{

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
        while (true) {
            System.out.println("            |    |    |     ");
            System.out.println("            |    |    |     ");
            System.out.println("           " + leftUpLightTrafficList.size() + "| |  |    |" + rightUpLightTrafficList.size());
            System.out.println("         " + getState(trafficLightLeftUp) + "| ↓  |    |" + getState(trafficLightRightUp));
            System.out.println("————————————┘         └————————————");
            System.out.println("                       ←———         ");
            System.out.println("————————————           ————————————");
            System.out.println("        ———→                      ");
            System.out.println("————————————┐         ┌————————————");
            System.out.println("         " + getState(trafficLightLeftDown) + "|    | ↑  |" + getState(trafficLightRightDown));
            System.out.println("           " + leftDownLightTrafficList.size() + "|    | |  |" + rightDownLightTrafficList.size());
            System.out.println("            |    |    |     ");
            System.out.println("            |    |    |     ");
            System.out.println();
        }
    }

    static String getState(Light svet){
        return switch (svet.state){
            case RED -> "Red";
            case GREEN -> "Grn";
            case YELLOW -> "Ylw";
            case FLASHINGGREEN -> "FlG";
            case BROKEN -> "Throw new exception";
        };
    }
}
