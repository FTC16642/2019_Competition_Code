package org.firstinspires.ftc.teamcode.sensors.sensorutils;


public class SensorManager {
    Sensor[] sensors;
    public SensorManager(Sensor ...sensors){
        this.sensors = sensors;
    }

    public void init(){
        for (Sensor sensor:sensors){
            sensor.init();
        }
    }

    public void update(){
        for (Sensor sensor:sensors){
            sensor.update();
        }
    }
}
