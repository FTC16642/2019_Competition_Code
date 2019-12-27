package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.teamcode.hardware.controls.GamepadWrapper;
import org.firstinspires.ftc.teamcode.subsystems.subsystemutils.Subsystem;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Intake implements Subsystem{

    private GamepadWrapper driveController;
    private DcMotor intakeLeft;
    private DcMotor intakeRight;

    public Claw(GamepadWrapper driveController, DcMotor intakeLeft, DcMotor intakeRight){
        this.driveController = driveController;
        this.intakeLeft = intakeLeft;
        this.intakeRight = intakeRight;
    }

    //Intake is Active
    private intakeActive(double speed){
        intakeLeft.setPower(speed);
        intakeRight.setPower(speed);
    }

    //Intake is Stopped
    private intakeStop(){
        intakeLeft.setPower(0);
        intakeRight.setPower(0);
    }

    @Override
    public void init(){

    }

    @Override
    public void update(){
        double leftTrigger = driveController.getLeftTrigger();

        if (leftTrigger>0){
            intakeActive(leftTrigger);
        }
        else{
            intakeStop();
        }
    }

}