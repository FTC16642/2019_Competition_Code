package org.firstinspires.ftc.teamcode.telemetry;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.LightSensor;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.sensors.ColorDetect;
import org.firstinspires.ftc.teamcode.sensors.sensorutils.SensorNames;
import org.firstinspires.ftc.teamcode.telemetry.telemetryutils.TelemetryItems;
import org.firstinspires.ftc.teamcode.telemetry.telemetryutils.TelemetryManager;

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
