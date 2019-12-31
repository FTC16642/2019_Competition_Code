package org.firstinspires.ftc.teamcode.hardware.hardwareutils;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.sensors.LightDetect;
import org.firstinspires.ftc.teamcode.sensors.sensorutils.SensorNames;

public class HardwareManager {

    HardwareMap hardwareMap;

    //Drivetrain Motors
    public DcMotor leftFrontDrive, rightFrontDrive, leftRearDrive, rightRearDrive;

    //Claw Servos
    public Servo elbow, wrist;
    public DcMotor lift;

    //Pusher Servo
    public Servo pusher;

    //Intake Motors
    public DcMotor intakeLeft;
    public DcMotor intakeRight;

    //Hook Servo
    public Servo hook;

    //Sensors
    public LightSensor lightSensor;
    public ColorSensor colorSensor;
    public DistanceSensor distanceSensor;


    public HardwareManager(HardwareMap hardwareMap)
    {
        this.hardwareMap = hardwareMap;
        initDrivetrain();
        initClaw();
        initPusher();
        initIntake();
        initHook();
        initColor();
        //initDistance();
        //initLight();
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
        elbow = hardwareMap.get(Servo.class, HardwareNames.elbow);
        lift = hardwareMap.get(DcMotor.class, HardwareNames.lift);
        wrist = hardwareMap.get(Servo.class, HardwareNames.wrist);
    }

    private void initHook(){
        hook = hardwareMap.get(Servo.class, HardwareNames.hook);
    }

    private void initPusher()
    {
        pusher = hardwareMap.get(Servo.class, HardwareNames.pusher);
    }

    private void initIntake()
    {
        intakeLeft = hardwareMap.get(DcMotor.class, HardwareNames.intakeLeft);
        intakeRight = hardwareMap.get(DcMotor.class, HardwareNames.intakeRight);

        intakeRight.setDirection(DcMotor.Direction.REVERSE);
    }

    private void initColor(){
        colorSensor = hardwareMap.get(ColorSensor.class, SensorNames.colorDetect);
    }

    private void initDistance(){
        distanceSensor = hardwareMap.get(DistanceSensor.class, SensorNames.distanceDetect);
    }

    private void initLight(){
        lightSensor = hardwareMap.get(LightSensor.class, SensorNames.lightDetect);
    }
}