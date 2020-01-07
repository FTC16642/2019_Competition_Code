package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Common.ComponentBase;
import org.firstinspires.ftc.teamcode.Common.HardwareIO;

public class Intake extends ComponentBase
{
    private DcMotor rintakeMtr = null;
    private DcMotor lintakeMtr = null;

    public Intake(HardwareIO InputOutput)
    {
        super(InputOutput);
    }

    @Override
    public void init()
    {
        rintakeMtr = IO.hardwareMap.get(DcMotor.class, "rightIntake");
        lintakeMtr = IO.hardwareMap.get(DcMotor.class, "leftIntake");
        IO.telemetry.addData("Status", "Initializing Intake");
    }

    @Override
    public void loop()
    {
        if (IO.gamePad1.a)
        {
            rintakeMtr.setPower(1);
            lintakeMtr.setPower(1);
        }
        else
        {
            rintakeMtr.setPower(0);
            lintakeMtr.setPower(0);
        }

    }

    public void stop()
    {
        IO.telemetry.addData("Status", "Stopped");
    }
}