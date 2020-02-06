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
            drive.moveForward(9 , drive.medium);
            //straf to the right to go near the stone.
            drive.moveRightRaw(1400, drive.medium);
            //since the starf doesn't move in a straight line due tow eihgt,
            //we move front agian to align straight again.
            drive.moveForward(5 , drive.medium);
            // move back a little to get the color sensor in the middle of second stone.
            //drive.moveForward(-1, drive.medium);

            moveNext(160);
            //if the above 2nd stone is NOT a skystone
            moveNext(220);
            //if the above 3rd stone is NOT a skystone
            moveNext(180);
            //if the above 4th stone is NOT a skystone
            moveNext(220);
            //if the above 5th stone is NOT a skystone
            moveNext(200);
            // if none of the stones are skystone grab the last.


            sleep(100);
            //if the robot is too close to the stone
            //move away a litte to pick the stone correctly
            if(colorSensor.getDistanceValue() < 10 )
            {
                drive.moveRightRaw(-30, drive.medium);
                sleep(200);
            }
            if(colorSensor.getDistanceValue() > 23 )
            {
                drive.moveRightRaw(40, drive.medium);
                sleep(100);
            }
            //Pick the stone
            grabber.pickup();
            //Move to the right to not bump into the bridge holder
            drive.moveRight(-4, drive.medium);

            // Turn a little to adjust the strafing issue
            drive.turnClockwise(2, drive.medium);

            //since the distance sensor is on the front, use it
            //to until the last stone using the distance and then move
            //move closer to the back wall
            while( backDistSensor.getDistance() < 900 )
            {
                drive.moveForward(-4, drive.medium);
            }

            //sleep(2000);

            // Move forward to the other side to a certain position
            //until the back distance sensor fetches a value
            drive.moveForward(-26, drive.medium);

            // corrects strafing
            /*if(sideDistSensor.getDistance() > 800 )
            {
                drive.moveRight(1, drive.medium);
            }*/
            //sleep(300);
            //moves towards the foundation
            while(sideDistSensor.getDistance() < 900 )
            {
                drive.moveRightRaw(100, drive.medium);
            }
            //drops the brick
            grabber.drop();

            // move little forward for the hook to grab.
            drive.moveForward(-2, drive.medium);

            hook.pullUp();

            //turns around completely to place hook.
            drive.turnClockwise(-26, drive.medium);

            drive.moveForward(1, drive.medium);
            hook.pullDown();
            sleep(500);
            drive.moveForward(-18 , drive.medium);

            hook.pullUp();

            drive.moveRight(-30, drive.medium);

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

    private void moveNext (int moveCnt)
    {
        if(bFoundSky)
        {
            return;
        }

        if (!colorSensor.isSkystone())
        {
            // move to third brick.
            drive.moveForwardRaw(-moveCnt, drive.medium);
            sleep(500);
        }
        else
        {
            bFoundSky = true;
        }
    }
}
