package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardware.controls.GamepadWrapper;
import org.firstinspires.ftc.teamcode.hardware.hardwareutils.HardwareManager;
import org.firstinspires.ftc.teamcode.sensors.ColorDetect;
import org.firstinspires.ftc.teamcode.sensors.DistanceDetect;
import org.firstinspires.ftc.teamcode.sensors.LightDetect;
import org.firstinspires.ftc.teamcode.sensors.sensorutils.Sensor;
import org.firstinspires.ftc.teamcode.sensors.sensorutils.SensorManager;
import org.firstinspires.ftc.teamcode.subsystems.Arcade;
import org.firstinspires.ftc.teamcode.subsystems.Pusher;
import org.firstinspires.ftc.teamcode.subsystems.Mecanum;
import org.firstinspires.ftc.teamcode.subsystems.Claw;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Hook;
import org.firstinspires.ftc.teamcode.subsystems.subsystemutils.Subsystem;
import org.firstinspires.ftc.teamcode.subsystems.subsystemutils.SubsystemManager;
import org.firstinspires.ftc.teamcode.telemetry.ColorSensorData;
import org.firstinspires.ftc.teamcode.telemetry.DistanceSensorData;
import org.firstinspires.ftc.teamcode.telemetry.LightSensorData;
import org.firstinspires.ftc.teamcode.telemetry.telemetryutils.TelemetryItems;
import org.firstinspires.ftc.teamcode.telemetry.telemetryutils.TelemetryManager;

@TeleOp
public class teleopRobot extends OpMode {
    HardwareManager hardware;

    GamepadWrapper driveController; //gamepad 1;
    GamepadWrapper secondaryController; //gamepad 2;

    SubsystemManager subsystems;

    SensorManager sensors;

    TelemetryManager telemetries;

    @Override
    public void init_loop() {
        // If you are using Motorola E4 phones,
        // you should send telemetry data while waiting for start.
        telemetry.addLine("Status: Waiting for Start");
        //telemetry
    }
    @Override
    public void init() {
        //verify switch on bottom is in X pos
        //for drive controller, do Start btn + A btn
        //for secondary controller, do Start btn + B btn
        hardware = new HardwareManager(hardwareMap);
        driveController = new GamepadWrapper(gamepad1);
        secondaryController = new GamepadWrapper(gamepad2);

        Subsystem driveMecanum = setUpDriveTrainMecanum();
        Subsystem driveArcade = setUpDriveTrainArcade();
        Subsystem claw = setUpClaw();
        Subsystem pusher = setUpPusher();
        Subsystem intake = setUpIntake();
        Subsystem hook = setUpHook();

        if (1==2) {
            subsystems = new SubsystemManager(claw, pusher, intake, hook, driveArcade);
        }
        else{
            subsystems = new SubsystemManager(driveMecanum, claw, pusher, intake, hook);
        }

        Sensor color = setUpColor();
        //Sensor light = setUpLight();
        //Sensor distance = setUpDistance();
        sensors = new SensorManager(color);
        //sensors = new SensorManager(color, light, distance);

        TelemetryItems colorData = setUpColorData();
        //TelemetryItems lightData = setUpLightData();
        //TelemetryItems distanceData = setUpDistanceData();
        telemetries = new TelemetryManager(colorData);
        //telemetries = new TelemetryManager(colorData, lightData, distanceData);
    }
    @Override
    public void loop() {
        subsystems.update();
        sensors.update();

    }

    private Subsystem setUpClaw() {
      return new Claw(secondaryController, hardware.elbow, hardware.wrist, hardware.lift);
    }
    private Subsystem setUpPusher()
    {
        return new Pusher(secondaryController, hardware.pusher);
    }
    private Subsystem setUpHook()
    {
        return new Hook(secondaryController, hardware.hook);
    }
    private Subsystem setUpIntake()
    {
        return new Intake(driveController, hardware.intakeLeft, hardware.intakeRight);
    }
    private Subsystem setUpDriveTrainMecanum()
    {
        return new Mecanum(driveController, hardware.leftFrontDrive, hardware.rightFrontDrive, hardware.leftRearDrive, hardware.rightRearDrive);
    }
    private Subsystem setUpDriveTrainArcade()
    {
        return new Arcade(driveController, hardware.leftFrontDrive, hardware.rightFrontDrive, hardware.leftRearDrive, hardware.rightRearDrive);
    }



    private Sensor setUpColor()
    {
        return new ColorDetect(hardware.colorSensor);
    }
    private Sensor setUpLight()
    {
        return new LightDetect(hardware.lightSensor);
    }
    private Sensor setUpDistance()
    {
        return new DistanceDetect(hardware.distanceSensor);
    }


    private TelemetryItems setUpColorData()
    {
        return new ColorSensorData(hardware.colorSensor);
    }
    private TelemetryItems setUpLightData()
    {
        return new LightSensorData(hardware.lightSensor);
    }
    private TelemetryItems setUpDistanceData()
    {
        return new DistanceSensorData(hardware.distanceSensor);
    }

}
