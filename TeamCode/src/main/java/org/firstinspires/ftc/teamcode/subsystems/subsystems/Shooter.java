package org.firstinspires.ftc.teamcode.subsystems.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Shooter {

    Servo shooter_Reloader;

    public Shooter(HardwareMap hardwareMap) {
        this.shooter_Reloader = hardwareMap.get(Servo.class, "shooter_Reloader");


    }


    public void init() {


    }

    public void load() {
        shooter_Reloader.setDirection(Servo.Direction.FORWARD);
        shooter_Reloader.setPosition(0.2);
    }

    public void unload() {
        shooter_Reloader.setDirection(Servo.Direction.FORWARD);
        shooter_Reloader.setPosition(0.6);

    }
    public double whereservo(){
        return shooter_Reloader.getPosition();
    }

}
