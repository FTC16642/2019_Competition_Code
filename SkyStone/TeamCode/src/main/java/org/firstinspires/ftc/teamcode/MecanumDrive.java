package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.Common.ComponentBase;
import org.firstinspires.ftc.teamcode.Common.HardwareIO;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

public class MecanumDrive extends ComponentBase
{

    float FL_power;
    float FR_power;
    float RL_power;
    float RR_power;
    float rightX;
    float leftY;
    float leftX;
    private DcMotor fld = null;
    private DcMotor frd = null;
    private DcMotor bld = null;
    private DcMotor brd = null;
    public MecanumDrive(HardwareIO InputOutput)
    {
        super(InputOutput);
    }

    public void init()
    {
        fld = IO.hardwareMap.get(DcMotor.class, "leftFrontWheel" );
        frd = IO.hardwareMap.get(DcMotor.class, "rightFrontWheel");
        bld = IO.hardwareMap.get(DcMotor.class, "leftBackWheel" );
        brd = IO.hardwareMap.get(DcMotor.class, "rightBackWheel" );
        frd.setDirection(DcMotor.Direction.REVERSE);
        brd.setDirection(DcMotor.Direction.REVERSE);

        fld.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frd.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bld.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        brd.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }

    public void loop()
    {

        getInputs();
        holonomicFormula();
        setPower();

    }

    private void setPower()
    {
        fld.setPower(FL_power);
        frd.setPower(FR_power);
        bld.setPower(RL_power);
        brd.setPower(RR_power);
        String displayValue = String.format("FL = %.2f, FR = %.2f, RL = %.2f, RR = %.2f",
                FL_power, FR_power, RL_power, RR_power);
        IO.telemetry.addData("MecanumDrive", displayValue);
    }

    private void getInputs()
    {
        leftY = IO.gamePad1.left_stick_y;
        leftX = IO.gamePad1.left_stick_x;
        rightX = IO.gamePad1.right_stick_x;

    }

    private void holonomicFormula()
    {
        float FL_power_raw;
        float FR_power_raw;
        float RL_power_raw;
        float RR_power_raw;

        FL_power_raw = leftY - leftX - rightX;
        FR_power_raw = leftY + leftX + rightX;
        RL_power_raw = leftY + leftX - rightX;
        RR_power_raw = leftY - leftX + rightX;

        FL_power = Range.clip(FL_power_raw, -1, 1);
        FR_power = Range.clip(FR_power_raw, -1, 1);
        RL_power = Range.clip(RL_power_raw,-1 ,1);
        RR_power = Range.clip(RR_power_raw, -1, 1);
    }

}
