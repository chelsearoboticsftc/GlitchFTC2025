package org.firstinspires.ftc.teamcode.subsystems.subsystems;
import com.qualcomm.robotcore.hardware.CRServoImplEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class shooter_Aim {
    Servo Aim;

    public shooter_Aim(HardwareMap hardwareMap) {
        //this.Aim = hardwareMap.get(Servo.class, "aim");
    }

    public void setDirection(Servo.Direction direction) {
    }


    public double getCurrentPosition() {
        return Aim.getPosition();
    }

    public void stop() {
        //index.getController().setServoPosition(1, 0.000001);
        Aim.setPosition(0);
    }
}