package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.teamcode.hardware.controls.GamepadWrapper;
import org.firstinspires.ftc.teamcode.subsystems.subsystemutils.Subsystem;
import com.qualcomm.robotcore.hardware.Servo;

public class Claw implements Subsystem{

    private GamepadWrapper secondaryController;
    private Servo pusher;

    public Claw(GamepadWrapper secondaryController, Servo pusher){
        this.secondaryController = secondaryController;
        this.pusher = pusher;
    }

    //Pusher is Active
    private pusherActive(){
        pusher.setPosition(.5);
    }

    //Pusher is Retracted
    private pusherRetract(){
        pusher.setPosition(0);
    }

    @Override
    public void init(){

    }

    @Override
    public void update(){
        boolean buttonA = secondaryController.getBtnA();
        boolean dPadUp = secondaryController.getDPadUp();
        boolean dPadDown = secondaryController.getDPadDown();


        if (buttonA){
            if (dPadDown){
                pusherActive();
            }
            else if (dPadUp){
                pusherRetract();
            }
        }
    }

}