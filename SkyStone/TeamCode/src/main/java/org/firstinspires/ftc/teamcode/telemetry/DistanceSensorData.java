package org.firstinspires.ftc.teamcode.telemetry;

import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.LightSensor;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.sensors.DistanceDetect;
import org.firstinspires.ftc.teamcode.sensors.LightDetect;
import org.firstinspires.ftc.teamcode.telemetry.telemetryutils.TelemetryItems;

public class DistanceSensorData implements TelemetryItems{

    private DistanceSensor distanceDetect;
    private Telemetry telemetry;

    public DistanceSensorData(DistanceSensor distanceDetect){
        this.distanceDetect = distanceDetect;
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
        double distanceDetected = distanceDetect.getDistance(DistanceUnit.CM);
        telemetry.addData("Distance Detected (CM):", distanceDetected);

    }

    @Override
    public void newLine() {
        String name = DistanceDetect.name;
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
