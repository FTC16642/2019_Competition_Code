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
    private DcMotor fldMtr = null;
    private DcMotor frdMtr = null;
    private DcMotor bldMtr = null;
    private DcMotor brdMtr = null;
    boolean formula1 = false;

    public MecanumDrive(HardwareIO InputOutput)
    {
        super(InputOutput);
    }

    public void init()
    {

        fldMtr = IO.hardwareMap.get(DcMotor.class, "leftFrontWheel" );
        frdMtr = IO.hardwareMap.get(DcMotor.class, "rightFrontWheel");
        bldMtr = IO.hardwareMap.get(DcMotor.class, "leftBackWheel" );
        brdMtr = IO.hardwareMap.get(DcMotor.class, "rightBackWheel" );
        frdMtr.setDirection(DcMotor.Direction.REVERSE);
        brdMtr.setDirection(DcMotor.Direction.REVERSE);

        fldMtr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frdMtr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bldMtr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        brdMtr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }

    public void loop()
    {
        getInputs();
        if(IO.gamePad1.b)
        {
            formula1 = !formula1;
        }

        if (formula1)
        {
            IO.telemetry.addData("Status", "Formula1");
            holonomicFormula();
        }
        else
        {
            IO.telemetry.addData("Status", "Formula2,");
            holonomicFormula2();
        }
    }

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
    
        setMtrPow(fld, frd, brd, bld);

    }

    private void setMtrPow(double _fld, double _frd, double _brd, double _bld)
    {
        double scale = maxAbs(1.0,_fld, _frd, _brd, _bld);
        fldMtr.setPower(_fld / scale);
        frdMtr.setPower(_frd/ scale);
        brdMtr.setPower(_brd / scale);
        bldMtr.setPower(_bld / scale);
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

    private void setPower()
    {
        fldMtr.setPower(FL_power);
        frdMtr.setPower(FR_power);
        bldMtr.setPower(RL_power);
        brdMtr.setPower(RR_power);
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

        setPower();
    }

}
