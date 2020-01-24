package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.Common.ComponentBase;
import org.firstinspires.ftc.teamcode.Common.HardwareIO;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

public class MecanumDrive extends ComponentBase
{
    private int lfPos; private int rfPos; private int lrPos; private int rrPos;

    // operational constanthttp://192.168.49.1:8080/java/editor.html?/src/org/firstinspires/ftc/teamcode/MecanumDrive.javas
    public double fast = 0.5; // Limit motor power to this value for Andymark RUN_USING_ENCODER mode
    public double medium = 0.3; // medium speed
    public double slow = 0.1; // slow speed
    private double clicksPerInch = 87.5; // empirically measured
    private double clicksPerDeg = 21.94; // empirically measured
    private double lineThreshold = 0.7; // floor should be below this value, line above
    private double redThreshold = 1.9; // red should be below this value, blue above

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

    private void initHardware()
    {

        flfMtr = IO.hardwareMap.get(DcMotor.class, "leftFrontWheel" );
        frgMtr = IO.hardwareMap.get(DcMotor.class, "rightFrontWheel");
        blfMtr = IO.hardwareMap.get(DcMotor.class, "leftBackWheel" );
        brgMtr = IO.hardwareMap.get(DcMotor.class, "rightBackWheel" );
        // The right motors need reversing
        frgMtr.setDirection(DcMotor.Direction.FORWARD);
        flfMtr.setDirection(DcMotor.Direction.REVERSE);
        brgMtr.setDirection(DcMotor.Direction.FORWARD);
        blfMtr.setDirection(DcMotor.Direction.REVERSE);
    }

    public void init()
    {
        initHardware();

        //flfMtr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //frgMtr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //blfMtr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //brgMtr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        flfMtr.setTargetPosition(0);
        frgMtr.setTargetPosition(0);
        blfMtr.setTargetPosition(0);
        brgMtr.setTargetPosition(0);
    }

    public void loop()
    {
        getInputs();
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
        frgMtr.setPower(FR_power);
        flfMtr.setPower(FL_power);
        blfMtr.setPower(RL_power);
        brgMtr.setPower(RR_power);


        //String displayValue = String.format("FL = %.2f, FR = %.2f, RL = %.2f, RR = %.2f",
        //        FL_power, FR_power, RL_power, RR_power);
        //IO.telemetry.addData("MecanumDrive1", displayValue);
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

        FL_power = Range.clip(FL_power_raw, -.5, .5);
        FR_power = Range.clip(FR_power_raw, -.5, .5);
        RL_power = Range.clip(RL_power_raw,-.5,.5);
        RR_power = Range.clip(RR_power_raw, -.5, .5);
    }

    //region autonomous code

    public void initAuto()
    {
        // Initialize the hardware variables.
        initHardware();



        // Set the drive motor run modes:
        flfMtr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frgMtr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        blfMtr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        brgMtr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        flfMtr.setTargetPosition(0);
        frgMtr.setTargetPosition(0);
        blfMtr.setTargetPosition(0);
        brgMtr.setTargetPosition(0);
        flfMtr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frgMtr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        blfMtr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        brgMtr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        // Stop all motion;
        flfMtr.setPower(0);
        frgMtr.setPower(0);
        blfMtr.setPower(0);
        brgMtr.setPower(0);
    }

    public void moveForward(int howMuch, double speed) {
        // howMuch is in inches. A negative howMuch moves backward.

        // fetch motor positions
        lfPos = flfMtr.getCurrentPosition();
        rfPos = frgMtr.getCurrentPosition();
        lrPos = blfMtr.getCurrentPosition();
        rrPos = brgMtr.getCurrentPosition();

        // calculate new targets
        lfPos += howMuch * clicksPerInch;
        rfPos += howMuch * clicksPerInch;
        lrPos += howMuch * clicksPerInch;
        rrPos += howMuch * clicksPerInch;

        // move robot to new position
        flfMtr.setTargetPosition(lfPos);
        frgMtr.setTargetPosition(rfPos);
        blfMtr.setTargetPosition(lrPos);
        brgMtr.setTargetPosition(rrPos);
        flfMtr.setPower(speed);
        frgMtr.setPower(speed);
        blfMtr.setPower(speed);
        brgMtr.setPower(speed);

        // wait for move to complete
        while (flfMtr.isBusy() && frgMtr.isBusy() &&
                blfMtr.isBusy() && brgMtr.isBusy()) {

            // Display it for the driver.
            IO.telemetry.addLine("Move Foward");
            IO.telemetry.addData("Target", "%7d :%7d", lfPos, rfPos, lrPos, rrPos);
            IO.telemetry.addData("Actual", "%7d :%7d", flfMtr.getCurrentPosition(),
                    frgMtr.getCurrentPosition(), blfMtr.getCurrentPosition(),
                    brgMtr.getCurrentPosition());
            IO.telemetry.update();
        }

        // Stop all motion;
        flfMtr.setPower(0);
        frgMtr.setPower(0);
        blfMtr.setPower(0);
        brgMtr.setPower(0);
    }

    public void moveRight(int howMuch, double speed) {
        // howMuch is in inches. A negative howMuch moves backward.

        // fetch motor positions
        lfPos = flfMtr.getCurrentPosition();
        rfPos = frgMtr.getCurrentPosition();
        lrPos = blfMtr.getCurrentPosition();
        rrPos = brgMtr.getCurrentPosition();

        // calculate new targets
        lfPos += howMuch * clicksPerInch;
        rfPos -= howMuch * clicksPerInch;
        lrPos -= howMuch * clicksPerInch;
        rrPos += howMuch * clicksPerInch;

        // move robot to new position

        flfMtr.setTargetPosition(lfPos);
        frgMtr.setTargetPosition(rfPos);
        blfMtr.setTargetPosition(lrPos);
        brgMtr.setTargetPosition(rrPos);

        flfMtr.setPower(speed);
        frgMtr.setPower(speed);
        blfMtr.setPower(speed);
        brgMtr.setPower(speed);

        // wait for move to complete
        while (flfMtr.isBusy() && frgMtr.isBusy() &&
                blfMtr.isBusy() && brgMtr.isBusy()) {

            // Display it for the driver.
            IO.telemetry.addLine("Strafe Right");
            IO.telemetry.addData("Target", "%7d :%7d", lfPos, lrPos, rrPos, rrPos);
            IO.telemetry.addData("Actual", "%7d :%7d", flfMtr.getCurrentPosition(),
                    frgMtr.getCurrentPosition(), blfMtr.getCurrentPosition(),
                    brgMtr.getCurrentPosition());
            IO.telemetry.update();
        }

        // Stop all motion;
        flfMtr.setPower(0);
        frgMtr.setPower(0);
        blfMtr.setPower(0);
        brgMtr.setPower(0);

    }

    public void turnClockwise(int whatAngle, double speed) {
        // whatAngle is in degrees. A negative whatAngle turns counterclockwise.

        // fetch motor positions
        lfPos = flfMtr.getCurrentPosition();
        rfPos = frgMtr.getCurrentPosition();
        lrPos = blfMtr.getCurrentPosition();
        rrPos = brgMtr.getCurrentPosition();

        // calculate new targets
        lfPos += whatAngle * clicksPerDeg;
        rfPos -= whatAngle * clicksPerDeg;
        lrPos += whatAngle * clicksPerDeg;
        rrPos -= whatAngle * clicksPerDeg;

        // move robot to new position
        flfMtr.setTargetPosition(lfPos);
        frgMtr.setTargetPosition(rfPos);
        blfMtr.setTargetPosition(lrPos);
        brgMtr.setTargetPosition(rrPos);
        flfMtr.setPower(speed);
        frgMtr.setPower(speed);
        blfMtr.setPower(speed);
        brgMtr.setPower(speed);

        // wait for move to complete
        while (flfMtr.isBusy() && frgMtr.isBusy() &&
                blfMtr.isBusy() && brgMtr.isBusy()) {

            // Display it for the driver.
            IO.telemetry.addLine("Turn Clockwise");
            IO.telemetry.addData("Target", "%7d :%7d", lfPos, rfPos, lrPos, rrPos);
            IO.telemetry.addData("Actual", "%7d :%7d", flfMtr.getCurrentPosition(),
                    frgMtr.getCurrentPosition(), blfMtr.getCurrentPosition(),
                    brgMtr.getCurrentPosition());
            IO.telemetry.update();
        }

        // Stop all motion;
        flfMtr.setPower(0);
        frgMtr.setPower(0);
        blfMtr.setPower(0);
        brgMtr.setPower(0);
    }

    public void stop()
    {
        // Stop all motion;
        flfMtr.setPower(0);
        frgMtr.setPower(0);
        blfMtr.setPower(0);
        brgMtr.setPower(0);
    }
    //endregion
}
