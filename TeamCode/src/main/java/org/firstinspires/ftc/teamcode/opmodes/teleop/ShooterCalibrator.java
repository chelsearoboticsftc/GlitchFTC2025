package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.subsystems.Shooter;
import org.firstinspires.ftc.teamcode.subsystems.subsystems.shooter_Aim;

@TeleOp
public class ShooterCalibrator extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Shooter shooter = new Shooter(hardwareMap);

        shooter_Aim Aim = new shooter_Aim(hardwareMap);

        waitForStart();
        double step = 6000;
        double velocity = 0;

        while (opModeIsActive()) {
            // A button presses increase velocity by "step"
            if (gamepad2.aWasPressed()) {
                velocity += step;
            }
            shooter.setMotorVelocity(velocity);
            // B button presses reset velocity to 0
            if (gamepad2.bWasPressed()) {
                shooter.fullpower();
            }
            //shooter.setMotorVelocity(velocity);
            this.telemetry.addData("Velocity", velocity);
            this.telemetry.update();
        }

        if (gamepad2.right_stick_y <= (-0.5)) {
            //Aim.setDirection(Servo.Direction.REVERSE);
        }

        if (gamepad2.right_stick_y >= (0.5))
            //Aim.setDirection(Servo.Direction.FORWARD);


        if (Aim.getCurrentPosition()<=(0)) {
            //Aim.stop();
        }
            }
            }





