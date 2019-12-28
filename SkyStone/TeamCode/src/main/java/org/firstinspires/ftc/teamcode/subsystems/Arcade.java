package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.hardware.controls.GamepadWrapper;
import org.firstinspires.ftc.teamcode.subsystems.subsystemutils.Subsystem;

public class Arcade implements Subsystem {
    DcMotor frontLeftDrive, frontRightDrive, backLeftDrive, backRightDrive;
    GamepadWrapper driveController;


    public void setPowerArcade(double rightStickX, double rightStickY){
        double right = rightStickY-rightStickX;
        double left = rightStickY+rightStickX;
        double fld = left;
        double frd = right;
        double bld = left;
        double brd = right;
        double max = Math.max(Math.max(Math.abs(fld), Math.abs(frd)), Math.max(Math.abs(bld), Math.abs(brd)));
        if (max>1){
            fld = fld/max;
            frd = frd/max;
            brd = brd/max;
            bld = bld/max;
        }
        frontLeftDrive.setPower(fld);
        frontRightDrive.setPower(frd);
        backLeftDrive.setPower(bld);
        backRightDrive.setPower(brd);
    }


    public Arcade(GamepadWrapper driveController, DcMotor frontLeftDrive, DcMotor frontRightDrive, DcMotor backLeftDrive, DcMotor backRightDrive){
        this.driveController = driveController;
        this.frontLeftDrive = frontLeftDrive;
        this.frontRightDrive = frontRightDrive;
        this.backLeftDrive = backLeftDrive;
        this.backRightDrive = backRightDrive;
    }

    @Override
    public void init(){

    }

    @Override
    public void update(){
        setPowerArcade(driveController.getRightStickX(), driveController.getRightStickY());
    }


}