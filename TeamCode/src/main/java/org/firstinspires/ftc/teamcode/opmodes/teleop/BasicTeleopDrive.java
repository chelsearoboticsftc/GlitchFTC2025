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
            double forward = -gamepad1.left_stick_y;
            double turn = -gamepad1.left_stick_x;
            if (strafe < 0.001 || strafe > 0.001){
                drive.leftBack.setPower(strafe);
                drive.leftFront.setPower(-strafe);
                drive.rightBack.setPower(-strafe);
                drive.rightFront.setPower(strafe);
            }
            if (forward != 0){
                drive.leftBack.setPower(forward);
                drive.leftFront.setPower(forward);
                drive.rightBack.setPower(forward);
                drive.rightFront.setPower(forward);

            }
            if (turn != 0){
                drive.leftBack.setPower(turn);
                drive.leftFront.setPower(turn);
                drive.rightBack.setPower(-turn);
                drive.rightFront.setPower(-turn);
            }



        }
    }
}
