package org.firstinspires.ftc.teamcode.sensors;

import com.qualcomm.robotcore.hardware.LightSensor;

import org.firstinspires.ftc.teamcode.hardware.controls.GamepadWrapper;
import org.firstinspires.ftc.teamcode.sensors.sensorutils.Sensor;
import org.firstinspires.ftc.teamcode.sensors.sensorutils.SensorNames;

public class LightDetect implements Sensor{
    LightSensor lightDetect;
    GamepadWrapper gamepadWrapper;
    public static final String name = SensorNames.lightDetect;

    public LightDetect(LightSensor lightDetect){
        this.lightDetect = lightDetect;
    }

    public void lightDetected(LightSensor lightDetect){
        lightDetect.status();
        lightDetect.enableLed(true);
        lightDetect.getLightDetected();
    }

    @Override
    public void init(){

    }

    @Override
    public void update(){
        lightDetected(lightDetect);
    }
}
