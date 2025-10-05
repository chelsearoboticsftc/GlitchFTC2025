package org.firstinspires.ftc.teamcode.opmodes.teleop;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.sun.tools.javac.util.List;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.ArrayList;
//import org.openftc.apriltag.AprilTagDetection;

@TeleOp
public class visiontest extends LinearOpMode{
    @Override
    public void runOpMode() throws InterruptedException{

        AprilTagProcessor myAprilTagProcessor;
        myAprilTagProcessor = AprilTagProcessor.easyCreateWithDefaults();
        VisionPortal myVisionPortal;
        ArrayList<AprilTagDetection> detections = new ArrayList<>();
        myVisionPortal = VisionPortal.easyCreateWithDefaults(hardwareMap.get(WebcamName.class, "Webcam 1"), myAprilTagProcessor);
        String ball_order;
        waitForStart();
        while(opModeIsActive()){


            if (myAprilTagProcessor.getDetections().size() > 0.0){
                myAprilTagProcessor.getDetections();
                detections = myAprilTagProcessor.getDetections();


            }
            else {
                detections.clear();
            }
            telemetry.addData("id", detections);
            telemetry.update();


            if (detections.contains(21)){
                ball_order = "gpp";
            }
            if (detections.contains(22)){
                ball_order = "pgp";
            }
            if (detections.contains(23)){
                ball_order = "ppg";
            }
        }
    }

}
