package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.MecanumDrive;

@TeleOp
public class BasicTeleopDrive extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0,0,0));

        waitForStart();

        while(opModeIsActive()){
            double strafe =  -gamepad1.right_stick_x;
            if (strafe < 0.0001 || strafe > 0.0001){
                drive.leftBack.setPower(strafe);
                drive.leftFront.setPower(-strafe);
                drive.rightBack.setPower(strafe);
                drive.rightFront.setPower(-strafe);
            }


            drive.setDrivePowers(
                    new PoseVelocity2d(
                            new Vector2d(-gamepad1.left_stick_y,
                                         -gamepad1.left_stick_x),
                            -gamepad1.left_stick_x));

        }
    }
}
