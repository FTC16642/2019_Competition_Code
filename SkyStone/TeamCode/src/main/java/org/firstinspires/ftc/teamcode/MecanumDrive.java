package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.Common.ComponentBase;
import org.firstinspires.ftc.teamcode.Common.HardwareIO;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

public class MecanumDrive extends ComponentBase
{
    double FL_power;
    double FR_power;
    double RL_power;
    double RR_power;

    float rightX;
    float leftY;
    float leftX;

    private DcMotor flfMtr = null;
    private DcMotor frgMtr = null;
    private DcMotor blfMtr = null;
    private DcMotor brgMtr = null;
    boolean formula1 = false;

    public MecanumDrive(HardwareIO InputOutput)
    {
        super(InputOutput);
    }

    public void init()
    {

        flfMtr = IO.hardwareMap.get(DcMotor.class, "leftFrontWheel" );
        frgMtr = IO.hardwareMap.get(DcMotor.class, "rightFrontWheel");
        blfMtr = IO.hardwareMap.get(DcMotor.class, "leftBackWheel" );
        brgMtr = IO.hardwareMap.get(DcMotor.class, "rightBackWheel" );

        frgMtr.setDirection(DcMotor.Direction.REVERSE);
        brgMtr.setDirection(DcMotor.Direction.REVERSE);

        flfMtr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frgMtr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        blfMtr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        brgMtr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }

    public void loop()
    {
        getInputs();
        holonomicFormula();
        setPower();
    }

    public void Move(float x, float y, float turn)
    {
        leftX = x;
        leftY = y;
        rightX = turn;

        holonomicFormula();
        setPower();
    }

    private void getInputs()
    {
        leftX = -IO.gamePad1.left_stick_x;
        leftY = -IO.gamePad1.left_stick_y;
        rightX = -IO.gamePad1.right_stick_x;

        String displayValue = String.format("LX = %.2f, LY = %.2f, RX = %.2f", leftX, leftY, rightX);
        IO.telemetry.addData("Sticks Value", displayValue);
    }

    private void setPower()
    {
        flfMtr.setPower(FL_power);
        frgMtr.setPower(FR_power);
        blfMtr.setPower(RL_power);
        brgMtr.setPower(RR_power);
        //String displayValue = String.format("FL = %.2f, FR = %.2f, RL = %.2f, RR = %.2f",
        //        FL_power, FR_power, RL_power, RR_power);
        //IO.telemetry.addData("MecanumDrive", displayValue);
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

        String displayValue = String.format("FL = %.2f, FR = %.2f, RL = %.2f, RR = %.2f",
                FL_power_raw, FR_power_raw, RL_power_raw, RR_power_raw);
        IO.telemetry.addData("MecanumDrive_Raw", displayValue);

        FL_power = Range.clip(FL_power_raw, -1, 1);
        FR_power = Range.clip(FR_power_raw, -1, 1);
        RL_power = Range.clip(RL_power_raw,-1 ,1);
        RR_power = Range.clip(RR_power_raw, -1, 1);
    }

    //region holonomic formula2 with trignometry
    private void holonomicFormula2()
    {
        double x = Math.pow(leftX, 3.0);
        double y = Math.pow(leftY, 3.0);

        double rotation = Math.pow(rightX, 3.0);
        double direction = Math.atan2(x, y);
        double speed = Math.min(1.0, Math.sqrt(x * x + y * y));

        double fld = speed * Math.sin(direction + Math.PI / 4.0) + rotation;
        double frd = speed * Math.cos(direction + Math.PI / 4.0) - rotation;
        double brd = speed * Math.cos(direction + Math.PI / 4.0) + rotation;
        double bld = speed * Math.sin(direction + Math.PI / 4.0) - rotation;

        double scale = maxAbs(1.0, fld, frd, brd, bld);
        FL_power = fld / scale;
        FR_power = frd/ scale;
        RL_power = brd / scale;
        RR_power = bld / scale;
    }

    private static double maxAbs(double... xs)
    {
        double ret = Double.MIN_VALUE;
        for (double x : xs)
        {
            if (Math.abs(x) > ret) {
                ret = Math.abs(x);
            }
        }
        return ret;
    }
    //endregion
}
