package org.firstinspires.ftc.teamcode.subsystems.subsystemutils;

import org.firstinspires.ftc.teamcode.sensors.sensorutils.Sensor;

public interface Subsystem
{
    public void init(Sensor sensor);

    public void update();
}