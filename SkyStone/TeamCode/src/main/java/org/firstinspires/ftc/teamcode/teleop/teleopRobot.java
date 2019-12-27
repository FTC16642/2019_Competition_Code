package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardware.controls.GamepadWrapper;
import org.firstinspires.ftc.teamcode.hardware.hardwareutils.HardwareManager;
import org.firstinspires.ftc.teamcode.subsystems.Pusher;
import org.firstinspires.ftc.teamcode.subsystems.Mecanum;
import org.firstinspires.ftc.teamcode.subsystems.Claw;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Hook;
import org.firstinspires.ftc.teamcode.subsystems.subsystemutils.Subsystem;
import org.firstinspires.ftc.teamcode.subsystems.subsystemutils.SubsystemManager;

@TeleOp
public class teleopRobot extends OpMode {
    HardwareManager hardware;

    GamepadWrapper driveController; //gamepad 1;
    GamepadWrapper secondaryController; //gamepad 2;

    SubsystemManager subsystems;
    @Override
    public void init_loop() {
        // If you are using Motorola E4 phones,
        // you should send telemetry data while waiting for start.
        telemetry.addLine("Status: Waiting for Start");
    }
    @Override
    public void init() {
        //verify switch on bottom is in X pos
        //for drive controller, do Start btn + A btn
        //for manip controller, do Start btn + B btn
        hardware = new HardwareManager(hardwareMap);
        driveController = new GamepadWrapper(gamepad1);
        secondaryController = new GamepadWrapper(gamepad2);


        Subsystem drive = setUpDriveTrain();
        Subsystem claw = setUpClaw();
        Subsystem pusher = setUpPusher();
        Subsystem intake = setUpIntake();
        Subsystem hook = setUpHook();
        subsystems = new SubsystemManager(drive, claw, pusher, intake, hook);
    }
    @Override
    public void loop() {
        subsystems.update();

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
    private Subsystem setUpDriveTrain()
    {
        return new Mecanum(driveController, hardware.leftFrontDrive, hardware.rightFrontDrive, hardware.leftRearDrive, hardware.rightRearDrive);
    }
}
