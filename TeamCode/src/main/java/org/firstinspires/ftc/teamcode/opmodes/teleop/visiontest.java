package org.firstinspires.ftc.teamcode.opmodes.teleop;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.sun.tools.javac.util.List;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.ExposureControl;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
//import org.openftc.apriltag.AprilTagDetection;

@TeleOp
public class visiontest extends LinearOpMode{
    @Override
    public void runOpMode() throws InterruptedException{

        AprilTagProcessor myAprilTagProcessor;
        myAprilTagProcessor = AprilTagProcessor.easyCreateWithDefaults();
        VisionPortal myVisionPortal;
        ArrayList<AprilTagDetection> detections = new ArrayList<>();
        int detector;

        myVisionPortal = VisionPortal.easyCreateWithDefaults(hardwareMap.get(WebcamName.class, "Webcam 1"), myAprilTagProcessor);
        String ball_order;


        waitForStart();
        myVisionPortal.resumeStreaming();
        ExposureControl exposure = myVisionPortal.getCameraControl(ExposureControl.class);
        exposure.setMode(ExposureControl.Mode.Manual);
        exposure.setExposure(0, TimeUnit.MILLISECONDS);
        
        while(opModeIsActive()){


            if (myAprilTagProcessor.getDetections().size() > 0.0){
                //myAprilTagProcessor.getDetections().;
                detections = myAprilTagProcessor.getDetections();

                detector = detections.get(0).id;
            }
            else {
                detections.clear();
                detector = 0;
            }

            telemetry.addData("id", detector);



            if (detector == (21)){
                ball_order = "gpp";
            }
            else if (detector == (22)){
                ball_order = "pgp";
            }
            else if (detector == (23)){
                ball_order = "ppg";
            }
            else {
                ball_order = null;
            }
            telemetry.addData("ball order", ball_order);
            telemetry.update();
        }
    }

}
