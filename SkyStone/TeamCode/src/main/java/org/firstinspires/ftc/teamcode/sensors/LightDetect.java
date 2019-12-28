package org.firstinspires.ftc.teamcode.sensors;

import com.qualcomm.robotcore.hardware.LightSensor;

import org.firstinspires.ftc.teamcode.hardware.controls.GamepadWrapper;
import org.firstinspires.ftc.teamcode.sensors.sensorutils.Sensor;

public class LightDetect implements Sensor{
    LightSensor lightDetect;
    GamepadWrapper gamepadWrapper;

    public LightDetect(LightSensor lightDetect){
        this.lightDetect = lightDetect;
    }

    @Override
    public void init(){

    }

    @Override
    public void update(){

    }
}
