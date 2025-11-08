package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.LLStatus;
import com.qualcomm.hardware.limelightvision.Limelight3A;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.subsystems.subsystems.Vision;

@TeleOp

public class LimelightTeleOpTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException{
        Vision limelight = new Vision(hardwareMap);


        waitForStart();

        while(opModeIsActive()){

            Pose2d botpose = limelight.getRobotPos();
            if(limelight.getresult() != null){
                if(limelight.getresult().isValid()){
                    telemetry.addData("Pose2d that the limelight gives", botpose.toString());
                    telemetry.addData("tx",limelight.getTx());
                    telemetry.addData("ty", limelight.getTy());
                    telemetry.update();
                }
            }

        }


    }



}