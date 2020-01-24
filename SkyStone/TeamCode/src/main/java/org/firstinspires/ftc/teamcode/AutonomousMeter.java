package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import java.util.Locale;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Common.HardwareIO;
import org.firstinspires.ftc.teamcode.Grabber;
import org.firstinspires.ftc.teamcode.Hook;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.Sensors.BrickColorSensor;
import org.firstinspires.ftc.teamcode.Sensors.DistSensor;

@Autonomous(name="AutonomousMeter", group="Linear Opmode")

public class AutonomousMeter extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private Grabber grabber = null;
    private MecanumDrive drive = null;
    private BrickColorSensor colorSensor = null;
    private DistSensor backDistSensor = null;
    private DistSensor sideDistSensor = null;
    private Hook hook = null;
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        HardwareIO IO = new HardwareIO(hardwareMap, gamepad1, gamepad2, telemetry);

        grabber = new Grabber(IO);
        hook = new Hook(IO);
        backDistSensor = new DistSensor(IO.hardwareMap, IO.telemetry, "back_distance_sensor");
        sideDistSensor = new DistSensor(IO.hardwareMap, IO.telemetry, "side_distance_sensor");

        drive = new MecanumDrive(IO);

        colorSensor = new BrickColorSensor(IO.hardwareMap, IO.telemetry);

        colorSensor.init();

        drive.initAuto();
        
        backDistSensor.init();
        sideDistSensor.init();
        
        grabber.init();
        hook.init();
        

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        
        while (opModeIsActive())
        {
            colorSensor.getRGBValue();
            sleep(1000);
            sideDistSensor.getDistance();
            sleep(1000);
            backDistSensor.getDistance();
            sleep(1000);
        }

        colorSensor.uninitialize();
    }
}
