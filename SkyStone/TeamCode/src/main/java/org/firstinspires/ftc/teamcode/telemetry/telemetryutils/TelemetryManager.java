package org.firstinspires.ftc.teamcode.telemetry.telemetryutils;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TelemetryManager extends OpMode {

    Telemetry.Item[] telemetries;
    public TelemetryManager(Telemetry.Item ...telemetries){
        this.telemetries = telemetries;
    }

    public void init(){
        for (Telemetry.Item telemetry:telemetries){
            telemetry.setRetained(true);
        }
    }



    public void update(){
        telemetry.update();
    }

    @Override
    public void loop() {

    }
}
