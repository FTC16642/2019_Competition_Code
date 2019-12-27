package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.teamcode.hardware.controls.GamepadWrapper;
import org.firstinspires.ftc.teamcode.subsystems.subsystemutils.Subsystem;
import com.qualcomm.robotcore.hardware.CRServo;

public class Claw implements Subsystem{

    private GamepadWrapper secondaryController;
    private CRServo elbow, wrist, arm;

    public Claw(GamepadWrapper secondaryController, CRServo elbow, CRServo wrist, CRServo arm){
        this.secondaryController = secondaryController;
        this.arm = arm;
        this.elbow = elbow;
        this.wrist = wrist;
    }

    //Write Out the Low Position Sequence with Sleeps
    private clawLow(){
        elbow.setPosition(0);
        arm.setPosition(0);
        wrist.setPosition(0);
    }

    //Write Out the Med Position Sequence with Sleeps
    private clawMed(){
        elbow.setPosition(.5);
        arm.setPosition(.5);
        wrist.setPosition(.5);
    }
    
    //Write Out the High Position Sequence with Sleeps
    private clawHigh(){
        elbow.setPosition(1);
        arm.setPosition(1);
        wrist.setPosition(1);
    }
    
    //Write Out the Reset Sequence with Sleeps
    private clawReset(){
        elbow.setPosition(.3);
        arm.setPosition(.6);
        wrist.setPosition(.9);
    }


    @Override
    public void init(){

    }

    @Override
    public void update(){
        boolean buttonB = secondaryController.getBtnB();
        boolean dPadUp = secondaryController.getDPadUp();
        boolean dPadDown = secondaryController.getDPadDown();
        boolean dPadLeft = secondaryController.getDPadLeft();
        boolean dPadRight = secondaryController.getDPadRight();


        if (buttonB){
            if (dPadDown){
                clawLow();
            }
            else if (dPadRight){
                clawMed();
            }
            else if (dPadUp){
                clawHigh();
            }
            else if (dPadLeft){
                clawReset();
            }
        }
    }

}