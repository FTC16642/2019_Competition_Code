package org.firstinspires.ftc.teamcode.telemetry;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.LightSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.sensors.ColorDetect;
import org.firstinspires.ftc.teamcode.sensors.sensorutils.SensorNames;
import org.firstinspires.ftc.teamcode.telemetry.telemetryutils.TelemetryItems;

public class ColorSensorData implements TelemetryItems{

    private ColorSensor colorDetect;
    private Telemetry telemetry;

    public ColorSensorData(ColorSensor colorDetect){
        this.colorDetect = colorDetect;
    }

    @Override
    public void init() {
    }

    @Override
    public void update() {
        newLine();
        newData();
    }

    @Override
    public void newData() {
        double alphaDetected = colorDetect.alpha();
        double redDetected = colorDetect.red();
        double blueDetected = colorDetect.blue();
        double greenDetected = colorDetect.green();
        telemetry.addData("Alpha Detected:", alphaDetected);
        telemetry.addData("Red Detected:", redDetected);
        telemetry.addData("Blue Detected:", blueDetected);
        telemetry.addData("Green Detected:", greenDetected);

    }

    @Override
    public void newLine() {
        String name = ColorDetect.name;
        telemetry.addLine(name);

    }
}
