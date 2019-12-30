package org.firstinspires.ftc.teamcode.telemetry;

import com.qualcomm.robotcore.hardware.LightSensor;

import org.firstinspires.ftc.robotcore.external.Func;
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

    @Override
    public String getCaption() {
        return null;
    }

    @Override
    public Telemetry.Item setCaption(String caption) {
        return null;
    }

    @Override
    public Telemetry.Item setValue(String format, Object... args) {
        return null;
    }

    @Override
    public Telemetry.Item setValue(Object value) {
        return null;
    }

    @Override
    public <T> Telemetry.Item setValue(Func<T> valueProducer) {
        return null;
    }

    @Override
    public <T> Telemetry.Item setValue(String format, Func<T> valueProducer) {
        return null;
    }

    @Override
    public Telemetry.Item setRetained(Boolean retained) {
        return null;
    }

    @Override
    public boolean isRetained() {
        return false;
    }

    @Override
    public Telemetry.Item addData(String caption, String format, Object... args) {
        return null;
    }

    @Override
    public Telemetry.Item addData(String caption, Object value) {
        return null;
    }

    @Override
    public <T> Telemetry.Item addData(String caption, Func<T> valueProducer) {
        return null;
    }

    @Override
    public <T> Telemetry.Item addData(String caption, String format, Func<T> valueProducer) {
        return null;
    }
}
