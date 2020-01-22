package org.firstinspires.ftc.teamcode.Sensors;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;

import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class DistSensor
{
    private HardwareMap hardwareMap = null;
    private Telemetry telemetry = null;
    private DistanceSensor sensorRange;
    private String sensorName;

    public DistSensor(HardwareMap hrdMap, Telemetry tele, String name)
    {
        hardwareMap = hrdMap;
        telemetry = tele;
        sensorName = name;
    }

    public void init()
    {
        sensorRange = hardwareMap.get(DistanceSensor.class, sensorName);
    }

    public double getDistance()
    {
        // generic DistanceSensor methods.
        telemetry.addData("deviceName", sensorName);
        telemetry.addData("range", String.format("%.01f mm", sensorRange.getDistance(DistanceUnit.MM)));
        //telemetry.addData("range", String.format("%.01f cm", sensorRange.getDistance(DistanceUnit.CM)));
        //telemetry.addData("range", String.format("%.01f m", sensorRange.getDistance(DistanceUnit.METER)));
        //telemetry.addData("range", String.format("%.01f in", sensorRange.getDistance(DistanceUnit.INCH)));

        // you can also cast this to a Rev2mDistanceSensor if you want to use added
        // methods associated with the Rev2mDistanceSensor class.
        //Rev2mDistanceSensor sensorTimeOfFlight = (Rev2mDistanceSensor)sensorRange;
        // Rev2mDistanceSensor specific methods.
        //telemetry.addData("ID", String.format("%x", sensorTimeOfFlight.getModelID()));
        //telemetry.addData("did time out", Boolean.toString(sensorTimeOfFlight.didTimeoutOccur()));

        telemetry.update();
        return sensorRange.getDistance(DistanceUnit.MM);
    }




}
