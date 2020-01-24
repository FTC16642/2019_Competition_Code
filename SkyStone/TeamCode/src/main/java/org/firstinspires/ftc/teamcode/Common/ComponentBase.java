package org.firstinspires.ftc.teamcode.Common;
/*
* ComponentBase is the base class for all the feature classes. Any new feature should
* derive from this class, and implement init, loop and stop methods
* */

public abstract class ComponentBase {

    // Initializes the hardware map and the gamepad
    protected HardwareIO IO;

    public ComponentBase(HardwareIO InputOutput) {

        IO=InputOutput;
    }

    // Runs once when the player presses init
     abstract public void init();


    // Runs repeatedly when the player presses start until the player presses stop
    abstract public void loop();

    // Runs once when the player presses stop
    abstract public void stop();
}
