package org.firstinspires.ftc.teamcode.telemetry;

import com.qualcomm.robotcore.hardware.LightSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.sensors.ColorDetect;
import org.firstinspires.ftc.teamcode.sensors.LightDetect;
import org.firstinspires.ftc.teamcode.sensors.sensorutils.Sensor;
import org.firstinspires.ftc.teamcode.sensors.sensorutils.SensorNames;
import org.firstinspires.ftc.teamcode.telemetry.telemetryutils.TelemetryItems;

public class LightSensorData implements TelemetryItems{

    private LightSensor lightDetect;
    private Telemetry telemetry;

    public LightSensorData(LightSensor lightDetect){
        this.lightDetect = lightDetect;
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
        double lightDetected = lightDetect.getLightDetected();
        telemetry.addData("Light Detected:", lightDetected);

    }

    @Override
    public void newLine() {
        String name = LightDetect.name;
        telemetry.addLine(name);

    }
}
