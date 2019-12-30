package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Common.ComponentBase;
import org.firstinspires.ftc.teamcode.Common.HardwareIO;

public class Hook extends ComponentBase {

    // Declare objects that will be used in the code
    private ElapsedTime runtime;
    Servo servo;
    double servoPosition;

    // Constructor class to initialize the variables
    public Hook(HardwareIO InputOutput ){
        super(InputOutput);
        runtime = new ElapsedTime();
        servo = IO.hardwareMap.servo.get("hookServo");

    }

    // Runs when the player presses init
    public void init() {
        IO.telemetry.addData("Status", "Initializing hook");
        pullUp();
        IO.telemetry.addData("Status", "Initialized hook");
    }
    // Runs repeatedly after the player presses start
    public void loop() {

        //while(1==1) {
            if (IO.gamePad1.left_bumper)
                pullDown();
            else if (IO.gamePad1.right_bumper)
                pullUp();
        //}

    }

    // Runs once when the player presses stop
    public void stop() {
        IO.telemetry.addData("Status", "Stopping hook");
        runtime = null;
        servo = null;
        IO.telemetry.addData("Status", "Stopped hook");
    }

    // A function to pull the hook up
    public void pullUp()
    {
        {
            IO.telemetry.addData("Status", "Raising hook");
            servoPosition = 0.4;
            servo.setPosition(servoPosition);
            IO.telemetry.addData("Status", "Hook raised");
        }
    }

    // A function to pull the hook back down
    public void pullDown (){
        {
            IO.telemetry.addData("Status", "Lowering hook");
            servoPosition = 0.0;
            servo.setPosition(servoPosition);
            IO.telemetry.addData("Status", "Hook lowered");
        }
    }


}
