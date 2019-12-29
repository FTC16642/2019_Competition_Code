package org.firstinspires.ftc.teamcode.sensors;

import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.teamcode.hardware.controls.GamepadWrapper;
import org.firstinspires.ftc.teamcode.hardware.hardwareutils.HardwareNames;
import org.firstinspires.ftc.teamcode.sensors.sensorutils.Sensor;
import org.firstinspires.ftc.teamcode.sensors.sensorutils.SensorNames;

public class ColorDetect implements Sensor{
    ColorSensor colorDetect;
    GamepadWrapper gamepadWrapper;
    public static final String name = SensorNames.colorDetect;

    public ColorDetect(ColorSensor colorDetect){
        this.colorDetect = colorDetect;
    }

    public void colorDetected(ColorSensor colorDetect){
        colorDetect.enableLed(true);
        colorDetect.alpha();
        colorDetect.red();
        colorDetect.blue();
        colorDetect.green();
        colorDetect.enableLed(false);
    }

    @Override
    public void init(){

    }

    @Override
    public void update(){
        colorDetected(colorDetect);
    }
}
