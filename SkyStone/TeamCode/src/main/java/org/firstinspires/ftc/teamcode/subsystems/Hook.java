package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.teamcode.hardware.controls.GamepadWrapper;
import org.firstinspires.ftc.teamcode.subsystems.subsystemutils.Subsystem;
import com.qualcomm.robotcore.hardware.Servo;

public class Hook implements Subsystem{

    private GamepadWrapper secondaryController;
    private Servo hook;

    public Claw(GamepadWrapper secondaryController, Servo hook){
        this.secondaryController = secondaryController;
        this.hook = hook;
    }

    //Pusher is Active
    private hookActive(){
        pusher.setPosition(.5);
    }

    //Pusher is Retracted
    private hookRetract(){
        pusher.setPosition(0);
    }

    @Override
    public void init(){

    }

    @Override
    public void update(){
        boolean buttonX = secondaryController.getBtnX();
        boolean dPadUp = secondaryController.getDPadUp();
        boolean dPadDown = secondaryController.getDPadDown();


        if (buttonA){
            if (dPadDown){
                hookActive();
            }
            else if (dPadUp){
                hookRetract();
            }
        }
    }

}