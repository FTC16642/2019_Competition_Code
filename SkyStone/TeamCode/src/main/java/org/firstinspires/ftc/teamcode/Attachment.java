package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


/**
 * I modified the basic Tank/POV drive program to allow for basic Arcade drive.
 * Arcade Drive allows for complete control from a single joystick, allowing
 * other buttons or joysticks to be easily used by a single driver.
 */

@TeleOp(name="Attachment", group="Linear Opmode")

public class Attachment extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor lift = null;
    private Servo elbow = null;
    private Servo wrist = null;
    
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        lift  = hardwareMap.get(DcMotor.class, "lift");
        wrist = hardwareMap.get(Servo.class, "wrist");
        elbow = hardwareMap.get(Servo.class, "elbow");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        lift.setDirection(DcMotor.Direction.FORWARD);
        wrist.setDirection(Servo.Direction.FORWARD);
        elbow.setDirection(Servo.Direction.FORWARD);
      
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        //while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            double leftPower;
            double rightPower;
            
            // Arcade Mode uses left stick y axis to go forward, and left stick x axis to turn.
            // This can be used for more natural driving, especially by one driver.
            //double drive = gamepad1.left_stick_y;
            //double turn  = -gamepad1.left_stick_x * .5;
            
            // Line 56: I halved the turn power of the robot so that turning can
            // be controlled easily, without jerking.
            
            //leftPower    = Range.clip(.5, -1.0, 1.0) ;
            //rightPower   = Range.clip(drive - turn, -1.0, 1.0) ;

            // Send calculated power to wheels
            
            elbow.setPosition(0);
            sleep(3000);
            lift.setPower(-.7);
            sleep(3000);
            wrist.setPosition(0);
            sleep(3000);
            elbow.setPosition(1);
            sleep(3000);
            wrist.setPosition(1);
            sleep(3000);
            lift.setPower(.3);
            sleep(3000);

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
            sleep(3000);
            
            // Send calculated power to wheels
            //lift.setPower(0);
          

    }
}
