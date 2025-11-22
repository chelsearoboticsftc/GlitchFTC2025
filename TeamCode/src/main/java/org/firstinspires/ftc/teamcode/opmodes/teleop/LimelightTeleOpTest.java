package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.LLStatus;
import com.qualcomm.hardware.limelightvision.Limelight3A;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.subsystems.Vision;
@Disabled
@TeleOp

public class LimelightTeleOpTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException{
        Vision limelight = new Vision(hardwareMap);
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));


        waitForStart();

        while(opModeIsActive()){

            Pose2d botpose = limelight.getRobotPos();
            if(limelight.getresult() != null){
                if(limelight.getresult().isValid()){

                    telemetry.addData("Pose2d that the limelight gives", botpose);
                    telemetry.addData("tx",limelight.getresult().getTx());
                    telemetry.addData("ty", limelight.getresult().getTy());
                    telemetry.addData("pos",botpose.position);
                    telemetry.addData("heading",botpose.heading);
                    telemetry.update();

                }

            }
            if(gamepad1.aWasPressed()){
                while(Math.abs(limelight.getresult().getTx())> 0.1){
                    drive.setDrivePowers( new PoseVelocity2d(
                            new Vector2d(0,
                                    0),
                            0.1* limelight.getresult().getTx()));
                }
                drive.setDrivePowers( new PoseVelocity2d(
                        new Vector2d(0,
                                0),
                        0));
                }
            }

        }


    }



