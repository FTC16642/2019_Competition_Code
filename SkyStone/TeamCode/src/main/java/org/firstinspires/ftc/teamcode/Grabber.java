package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Common.ComponentBase;
import org.firstinspires.ftc.teamcode.Common.HardwareIO;

public class Grabber extends ComponentBase {

    //Declare objects that will be used in the program
    Servo servo;
    double servoPosition;

    //Initialize the objects
    public Grabber (HardwareIO InputOutput) {
        super(InputOutput);
        servo = IO.hardwareMap.servo.get("skystone");
    }

    //Runs when the player presses init
    public void init() {
        IO.telemetry.clear();
        IO.telemetry.addData("Status", "Initializing grabber");
        unlock();
        IO.telemetry.clear();
        IO.telemetry.addData("Status", "Grabber initialized");
    }

    //Runs repeatedly after the player presses stop
    public void loop() {

    }

    public void stop() {
        IO.telemetry.clear();
        IO.telemetry.addData("Status", "Stopping grabber");
        servo = null;
        IO.telemetry.clear();
        IO.telemetry.addData("Status", "Grabber stopped");
    }

    //Pulls the servo up
    public void unlock() {
        IO.telemetry.clear();
        IO.telemetry.addData("Status", "Unlocking grabber");
        servoPosition = 0.0;
        servo.setPosition(servoPosition);
        IO.telemetry.clear();
        IO.telemetry.addData("Status", "Grabber unlocked");
    }

    //Pulls the servo down
    public void lock() {
        IO.telemetry.clear();
        IO.telemetry.addData("Status", "Locking grabber");
        servoPosition = 0.45;
        IO.telemetry.clear();
        servo.setPosition(servoPosition);
        IO.telemetry.addData("Status", "Grabber locked");
    }
}