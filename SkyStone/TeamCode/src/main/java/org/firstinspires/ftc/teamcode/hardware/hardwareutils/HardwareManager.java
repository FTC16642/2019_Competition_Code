package org.firstinspires.ftc.teamcode.hardware.hardwareutils;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class HardwareManager {

    HardwareMap hardwareMap;

    //Drivetrain Motors
    public DcMotor leftFrontDrive, rightFrontDrive, leftRearDrive, rightRearDrive;

    //Claw Servos
    public CRServo elbow, wrist, arm;

    //Pusher Servo
    public Servo pusher;

    //Intake Motors
    public DcMotor intakeLeft;
    public DcMotor intakeRight;

    public HardwareManager(HardwareMap hardwareMap)
    {
        this.hardwareMap = hardwareMap;
        initDrivetrain();
        initClaw();
        initPusher();
        initIntake();
    }

    private void initDrivetrain()
    {
        leftFrontDrive = hardwareMap.get(DcMotor.class, HardwareNames.leftFrontDrive);
        leftRearDrive = hardwareMap.get(DcMotor.class, HardwareNames.leftRearDrive);

        rightFrontDrive = hardwareMap.get(DcMotor.class, HardwareNames.rightFrontDrive);
        rightRearDrive = hardwareMap.get(DcMotor.class, HardwareNames.rightRearDrive);
        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        rightRearDrive.setDirection(DcMotor.Direction.REVERSE);
    }

    private void initClaw()
    {
        elbow = hardwareMap.get(CRServo.class, HardwareNames.elbow);
        arm = hardwareMap.get(CRServo.class, HardwareNames.arm);
        wrist = hardwareMap.get(CRServo.class, HardwareNames.wrist);    
    }

    private void initPusher()
    {
        pusher = hardwareMap.get(Servo.class, HardwareNames.pusher);
    }

    private void initIntake()
    {
        intakeLeft = hardwareMap.get(DcMotor.class, HardwareNames.intakeLeft);
        intakeRight = hardwareMap.get(DcMotor.class, HardwareNames.intakeRight);

        intakeLeft.setDirection(DcMotor.Direction.REVERSE);
    }
}