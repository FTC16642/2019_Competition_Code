package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Common.ComponentBase;
import org.firstinspires.ftc.teamcode.Common.HardwareIO;
import org.firstinspires.ftc.teamcode.Hook;

public class Pusher extends ComponentBase {

    // Declare objects that will be used in the code
    Servo servo;
    double servoPosition;



    // Constructor class to initialize the variables
    public Pusher(HardwareIO InputOutput ){
        super(InputOutput);
        servo = IO.hardwareMap.servo.get("pusher");

    }

    // Runs when the player presses init
    public void init() {
        IO.telemetry.addData("Status", "Initializing pusher");
        servo.setPosition(0);
        IO.telemetry.addData("Status", "Initialized pusher");

    }
    // Runs repeatedly after the player presses start
    public void loop() {

        /*if (IO.gamePad2.left_stick_y<0)
            pullDown();
        else if (IO.gamePad2.left_stick_y>0)
            pullUp();*/
        if (IO.gamePad2.left_stick_y == 0) {
            servoPosition = 0;

            servo.setPosition(servoPosition);
            IO.telemetry.addData("servoPosition 1", servoPosition);
            IO.telemetry.addData("joyStickPosition", IO.gamePad2.left_stick_y);
        }
        else
            move();

    }

    // Runs once when the player presses stop
    public void move()
    {

        double scaleFactor = .05;
        servoPosition += (scaleFactor * IO.gamePad2.left_stick_y);
        if (servoPosition > 1 )
            servoPosition = 1;
        else if (servoPosition < -1 )
            servoPosition = -1;
        else
            servoPosition = servoPosition;


        servo.setPosition(servoPosition);
        IO.telemetry.addData("servoPosition", servoPosition);
        IO.telemetry.addData("joyStickPosition", IO.gamePad2.left_stick_y);
    }


}
