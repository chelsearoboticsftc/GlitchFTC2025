package org.firstinspires.ftc.teamcode.subsystems.example;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Shooter {

    Servo shooter_Reloader;

    public Shooter(HardwareMap hardwareMap) {
        this.shooter_Reloader = hardwareMap.get(Servo.class, "shooter_Reloader");
    }
}

