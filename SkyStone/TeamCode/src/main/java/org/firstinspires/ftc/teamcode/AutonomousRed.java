package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Common.HardwareIO;
import org.firstinspires.ftc.teamcode.Sensors.BrickColorSensor;
import org.firstinspires.ftc.teamcode.Sensors.DistSensor;

@Autonomous(name="AutonomousRed", group="Linear Opmode")

public class AutonomousRed extends LinearOpMode {

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
        hook.init();
        backDistSensor.init();
        sideDistSensor.init();

        grabber.init();



        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        //while (opModeIsActive())

        {
            drive.moveForward(-10 , drive.medium);
            drive.moveRight(13, drive.medium);
            drive.moveForward(-5 , drive.medium);
            drive.moveForward(1, drive.medium);
            //sleep(4000);
            while (!colorSensor.isSkystone())
            {
                drive.moveForward(2, drive.medium);
            }

            if(sideDistSensor.getDistance() > 800 )
            {
                drive.moveRight(-1, drive.medium);
            }

            grabber.pickup();

            drive.moveRight(-3, drive.medium);
            drive.turnClockwise(2, drive.medium);
            drive.moveForward(35, drive.medium);

            while( backDistSensor.getDistance() > 400 )
            {
                if(sideDistSensor.getDistance() > 800 )
                {
                    drive.moveRight(-1, drive.medium);
                }
                drive.moveForward(4, drive.medium);
            }

            while(sideDistSensor.getDistance() < 700 )
            {
                drive.moveRight(2, drive.medium);
            }
            grabber.drop();

            drive.turnClockwise(-25, drive.medium);

            drive.moveForward(1 , drive.medium);
            hook.pullDown();
            drive.moveForward(-12 , drive.medium);
            //drive.turnClockwise(25, drive.medium);
            //drive.moveForward(10 , drive.medium);
            hook.pullUp();
            drive.moveRight(25 , drive.medium);

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }

        while (opModeIsActive())
        {
            colorSensor.getRGBValue();
            sleep(1000);
            sideDistSensor.getDistance();
            sleep(1000);
            backDistSensor.getDistance();
            sleep(1000);
        }
    }
}
