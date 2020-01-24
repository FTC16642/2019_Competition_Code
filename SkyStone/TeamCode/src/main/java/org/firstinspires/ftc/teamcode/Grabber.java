package org.firstinspires.ftc.teamcode;
/*
* Grabber handles the side attachment which helps in picking up the stone. This class is mostly
* used in autonomous and driver control period.
* */

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Common.ComponentBase;
import org.firstinspires.ftc.teamcode.Common.HardwareIO;

public class Grabber extends ComponentBase {

    //Declare objects that will be used in the program
    Servo claw;
    Servo lift;
    double servoPosition;

    //Initialize the objects
    public Grabber (HardwareIO InputOutput) {
        super(InputOutput);
        claw = IO.hardwareMap.servo.get("grabclaw");
        lift = IO.hardwareMap.servo.get("grablift");
    }

    //Runs when the player presses init
    public void init() {
        IO.telemetry.clear();
        IO.telemetry.addData("Status", "Initializing grabber");
        up();
        IO.telemetry.clear();
        IO.telemetry.addData("Status", "Grabber initialized");
    }

    //Runs repeatedly after the player presses stop
    public void loop() {
        if (IO.gamePad2.a)
            pickup();
        else if (IO.gamePad2.x)
            drop();
    }

    public void stop() {

        IO.telemetry.clear();
        IO.telemetry.addData("Status", "Grabber stopped");
    }

    //Pulls the servo up
    public void drop() {
        IO.telemetry.clear();
        IO.telemetry.addData("Status", "Unlocking grabber");
        claw.setPosition(.5);
        // sleep(5000);
        IO.telemetry.clear();
        IO.telemetry.addData("Status", "Grabber unlocked");
    }

    //Pulls the servo down
    public void pickup() {

        IO.telemetry.clear();
        IO.telemetry.addData("Status", "Locking grabber");
        IO.telemetry.clear();

        lift.setPosition(.6);
        sleep(500);
        //try to open the claw and grab twice, because sometimes the robot is not in the perfect
        // position to pickup the stone. trying to grab twice brought the stone closer and then
        // picked it.
        claw.setPosition(1);
        sleep(500);
        claw.setPosition(0);
        sleep(500);
        claw.setPosition(1);
        sleep(500);
        lift.setPosition(0.2);
        sleep(200);


        IO.telemetry.addData("Status", "Grabber locked");
    }

    public void up()
    {
        lift.setPosition(0.2);
        claw.setPosition(0.5);


    }

    public final void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
