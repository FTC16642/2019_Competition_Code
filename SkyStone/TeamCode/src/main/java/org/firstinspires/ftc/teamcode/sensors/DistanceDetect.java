package org.firstinspires.ftc.teamcode.sensors;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.hardware.controls.GamepadWrapper;
import org.firstinspires.ftc.teamcode.sensors.sensorutils.Sensor;
import org.firstinspires.ftc.teamcode.sensors.sensorutils.SensorNames;

public class DistanceDetect implements Sensor{
    DistanceSensor distanceDetect;
    GamepadWrapper gamepadWrapper;
    public static final String name = SensorNames.distanceDetect;

    public DistanceDetect(DistanceSensor distanceDetect){
        this.distanceDetect = distanceDetect;
    }

    public void distanceDetected(DistanceSensor distanceDetect){
        distanceDetect.getDistance(DistanceUnit.CM);
    }

    @Override
    public void init(){

    }

    @Override
    public void update(){
        distanceDetected(distanceDetect);
    }
}
