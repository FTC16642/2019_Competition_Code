package org.firstinspires.ftc.teamcode.Common;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class HardwareIO {

    public HardwareMap hardwareMap = null;
    public Gamepad gamePad1 = null;
    public Gamepad gamePad2 = null;
    public Telemetry telemetry = null;

    public HardwareIO(HardwareMap hdMap, Gamepad gp1, Gamepad gp2,Telemetry tmtry) {
        hardwareMap = hdMap;
        gamePad1=gp1;
        gamePad2=gp2;
        telemetry=tmtry;

    }
}
