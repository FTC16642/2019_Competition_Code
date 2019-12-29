package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Common.ComponentBase;
import org.firstinspires.ftc.teamcode.Common.HardwareIO;

@TeleOp(name="Hook", group = "Iterative Opmode")

public class Hook extends ComponentBase {

    // Declare objects that will be used in the code
    private ElapsedTime runtime = null;
    Servo servo;
    double servoPosition;

    public Hook(HardwareIO InputOutput ){
        super(InputOutput);
        runtime = new ElapsedTime();

    }

    // Runs when the player presses init
    public void init() {
        IO.telemetry.addData("Status", "Initialized");


    }
    // Runs repeatedly after the player presses start
    public void loop() {

    }

    public void unlock() {

    }

}
