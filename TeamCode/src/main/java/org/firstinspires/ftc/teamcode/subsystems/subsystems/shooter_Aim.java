package org.firstinspires.ftc.teamcode.subsystems.subsystems;
import com.qualcomm.robotcore.hardware.CRServoImplEx;
import com.qualcomm.robotcore.hardware.Servo;

public class shooter_Aim {
    CRServoImplEx Aim;

    public void setDirection(Servo.Direction direction) {
    }


    public int getCurrentPosition() {
    }

    public void stop() {
        //index.getController().setServoPosition(1, 0.000001);
        Aim.setPower(0);
    }
}