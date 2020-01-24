package org.firstinspires.ftc.teamcode;
/*
* Lift handles the side attachment which is used to lift the stone and deliver/place it on the
* platform. This is used during, Autonomous, Driver control, and Endgame periods.
* */

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Common.ComponentBase;
import org.firstinspires.ftc.teamcode.Common.HardwareIO;

public class Lift extends ComponentBase {

    private DcMotor liftMtr = null;
    private Servo wristSrv = null;
    private Servo grabSrv = null;
    double motorPower;


    public Lift (HardwareIO InputOutput ){
        super(InputOutput);
    }

    // Runs when the player presses init
    public void init() {
        //IO.telemetry.addData("Status", "Initializing");
        liftMtr = IO.hardwareMap.get(DcMotor.class, "lift" );
        wristSrv = IO.hardwareMap.get(Servo.class, "wrist");
        grabSrv = IO.hardwareMap.get(Servo.class, "grab");
        IO.telemetry.addData("Status", "Initialized");
    }
    // Runs repeatedly after the player presses start
    public void loop()
    {
        lift();
        moveWrist();
        grab();
    }

    // Runs once when the player presses stop
    public void stop() {

        IO.telemetry.addData("Status", "Stopped");
    }

    public void lift ()
    {
        if (IO.gamePad2.left_trigger > 0)
            liftMtr.setPower(IO.gamePad2.left_trigger * .8);
        else if (IO.gamePad2.right_trigger > 0)
            liftMtr.setPower(IO.gamePad2.right_trigger * -.8);
    }

    public void grab()
    {
        if (IO.gamePad2.left_bumper == true)
            grabSrv.setPosition(0);
        else if(IO.gamePad2.left_bumper == false)
            grabSrv.setPosition(1);
    }

    public void moveWrist()
    {
        if (IO.gamePad2.right_bumper == true)
            wristSrv.setPosition(.1);
        else if(IO.gamePad2.right_bumper == false)
            wristSrv.setPosition(1);
    }

}

