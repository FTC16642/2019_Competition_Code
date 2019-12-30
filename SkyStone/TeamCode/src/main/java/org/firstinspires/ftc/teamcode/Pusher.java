package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Common.ComponentBase;
import org.firstinspires.ftc.teamcode.Common.HardwareIO;

public class Pusher extends ComponentBase {

    // Declare objects that will be used in the code
    Servo servo;
    double servoPosition;

    // Constructor class to initialize the variables
    public Pusher(HardwareIO InputOutput ){
        super(InputOutput);
        servo = IO.hardwareMap.servo.get("hookServo");
    }

    // Runs when the player presses init
    public void init() {
        IO.telemetry.addData("Status", "Initializing pusher");
        pullUp();
        IO.telemetry.addData("Status", "Initialized pusher");
    }
    // Runs repeatedly after the player presses start
    public void loop() {

        if (IO.gamePad2.dpad_down)
            pullDown();
        else if (IO.gamePad2.dpad_up)
            pullUp();

    }

    // Runs once when the player presses stop
    public void stop() {
        IO.telemetry.addData("Status", "Stopping pusher");
        servo = null;
        IO.telemetry.addData("Status", "Stopped pusher");
    }

    // A function to pull the hook up
    public void pullUp()
    {
        {
            IO.telemetry.addData("Status", "Raising pusher");
            servoPosition = 0.4;
            servo.setPosition(servoPosition);
            IO.telemetry.addData("Status", "pusher raised");
        }
    }

    // A function to pull the hook back down
    public void pullDown (){
        {
            IO.telemetry.addData("Status", "Lowering pusher");
            servoPosition = 0.0;
            servo.setPosition(servoPosition);
            IO.telemetry.addData("Status", "pusher lowered");
        }
    }


}
