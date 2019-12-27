package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.hardware.controls.GamepadWrapper;
import org.firstinspires.ftc.teamcode.subsystems.subsystemutils.Subsystem;

public class Mecanum implements Subsystem {
    DcMotor frontLeftDrive, frontRightDrive, backLeftDrive, backRightDrive;
    GamepadWrapper driveController;


    public void setPower(double leftStickY, double leftStickX, double rightStickX){
    double magnitude = Math.hypot(leftStickX, leftStickY);
    double robotAngle = Math.atan2(leftStickY, leftStickX) - Math.PI / 4;
    double rightX = rightStickX;
    double fld = magnitude * Math.cos(robotAngle) + rightX;
    double frd = magnitude * Math.sin(robotAngle) - rightX;
    double bld = magnitude * Math.sin(robotAngle) + rightX;
    double brd = magnitude * Math.cos(robotAngle) - rightX;
    double max = Math.max(Math.abs(fld), Math.abs(frd), Math.abs(bld), Math.abs(brd));
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

    public Mecanum(GamepadWrapper driveController, DcMotor frontLeftDrive, DcMotor frontRightDrive, DcMotor backLeftDrive, DcMotor backRightDrive){
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
        setPower(driveController.getLeftStickY(), driveController.getLeftStickX(), driveController.getRightStickX());
    }
}