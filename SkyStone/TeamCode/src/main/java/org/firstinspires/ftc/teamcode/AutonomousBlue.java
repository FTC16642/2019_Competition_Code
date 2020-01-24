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

@Autonomous(name="AutonomousBlue", group="Linear Opmode")

public class AutonomousBlue extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private Grabber grabber = null;
    private MecanumDrive drive = null;
    private BrickColorSensor colorSensor = null;
    private DistSensor backDistSensor = null;
    private DistSensor sideDistSensor = null;
    private Hook hook = null;
    private boolean bFoundSky = false;

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

        {
            //move forward to align to the top stone.
            drive.moveForward(-10 , drive.medium);
            //straf to the right to go near the stone.
            drive.moveRight(13, drive.medium);
            //since the starf doesn't move in a straight line due tow eihgt,
            //we move front agian to align straight again.
            drive.moveForward(-5 , drive.medium);
            // move back a little to get the color sensor in the middle of second stone.
            drive.moveForward(1, drive.medium);

            //sleep(2000);

            boolean bFound = false;

            //if the 2nd stone is NOT a skystone
            moveNext(2);

            //if the 3rd stone is NOT a skystone
            moveNext(3);

            //if the 4th stone is NOT a skystone
            moveNext(2);

            //if the 5th stone is NOT a skystone
            moveNext(3);
            // if none of the stones are skystone grab the last.


            /*int blockCount=2;
            while (!colorSensor.isSkystone())
            {
                drive.moveForward(2, drive.medium);
                sleep(2000);
                blockCount++;
                if (blockCount>7)
                    break;

            }*/

            //if the robot is too close to the stone
            //move away a litte to pick the stone correctly
            if(sideDistSensor.getDistance() > 800 )
            {
                drive.moveRight(-1, drive.medium);
            }
            //Pick the stone
            grabber.pickup();
            //Move to the right to not bump into the bridge holder
            drive.moveRight(-3, drive.medium);
            // Turn a little to adjust the strafing issue
            drive.turnClockwise(2, drive.medium);
            // Move forward to the other side to a certain position
            //until the back distance sensor fetches a value
            drive.moveForward(35, drive.medium);

            //move closer to the back wall
            while( backDistSensor.getDistance() > 400 )
            {
                // corrects strafing
                if(sideDistSensor.getDistance() > 800 )
                {
                    drive.moveRight(-1, drive.medium);
                }
                drive.moveForward(4, drive.medium);
            }

            //moves towards the foundation
            while(sideDistSensor.getDistance() < 700 )
            {
                drive.moveRight(2, drive.medium);
            }
            //drops the brick
            grabber.drop();

            //turns around completely
            drive.turnClockwise(-25, drive.medium);
            //
            drive.moveForward(1 , drive.medium);
            hook.pullDown();
            sleep(500);
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

        //colorSensor.uninitialize();
    }

    private void moveNext (int moveCnt)
    {
        if (!colorSensor.isSkystone())
        {
            // move to third brick.
            drive.moveForward(moveCnt, drive.medium);
            sleep(500);
        }
        else
        {
            bFoundSky = true;
        }
    }
}
