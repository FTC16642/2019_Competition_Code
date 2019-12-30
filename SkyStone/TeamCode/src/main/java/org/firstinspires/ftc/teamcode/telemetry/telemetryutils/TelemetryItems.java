package org.firstinspires.ftc.teamcode.telemetry.telemetryutils;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public interface TelemetryItems extends Telemetry.Item {

    public void init();

    public void update();

    public void newData();

    public void newLine();

}
