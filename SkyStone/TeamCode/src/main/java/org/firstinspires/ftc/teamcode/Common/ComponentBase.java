package org.firstinspires.ftc.teamcode.Common;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import javax.sql.CommonDataSource;
//import org.firstinspires.ftc.teamcode.Common.HardwareIO;
public abstract class ComponentBase {

    // Initializes the hardware map and the gamepad
    protected HardwareIO IO;

    public ComponentBase(HardwareIO InputOutput) {

        IO=InputOutput;
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
