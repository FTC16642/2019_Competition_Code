package org.firstinspires.ftc.teamcode.Common;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public abstract class ComponentBase {

    // Initializes the hardware map and the gamepad
    protected HardwareMap hardwareMap = null;
    protected Gamepad gamePad = null;

    public ComponentBase(HardwareMap hdMap, Gamepad gp1, Gamepad gp2) {

        hardwareMap = hdMap;
        gamePad = gp1;
        gamePad = gp2;
    }

    // Runs once when the player presses init
     abstract public void init();

    // Runs once when the player presses start
    public void start() {

    }

    // Runs repeatedly when the player presses start until the player presses stop
    abstract public void loop();

    // Runs once when the player presses stop
    public void stop() {

    }
}
