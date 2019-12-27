package org.firstinspires.ftc.teamcode.subsystems.subsystemutils;

import org.firstinspires.ftc.teamcode.subsystems.subsystemutils.Subsystem;

public class SubsystemManager {
    Subsystem[] subsystems;
    public SubsystemManager(Subsystem ...subsystems){
        this.subsystems = subsystems;
    }

    public void init(){
        for (Subsystem subsystem : subsystems){
            subsystem.init();
        }
    }

    public void update(){
        for (Subsystem subsystem : subsystems){
            subsystem.update();
        }
    }
}