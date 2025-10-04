package org.firstinspires.ftc.teamcode.opmodes.teleop;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
//import org.openftc.apriltag.AprilTagDetection;

@TeleOp
public class visiontest extends LinearOpMode{
    @Override
    public void runOpMode() throws InterruptedException{

        AprilTagProcessor myAprilTagProcessor;
        myAprilTagProcessor = AprilTagProcessor.easyCreateWithDefaults();
        VisionPortal myVisionPortal;
        myVisionPortal = VisionPortal.easyCreateWithDefaults(hardwareMap.get(WebcamName.class, "Webcam 1"), myAprilTagProcessor);
        int myAprilTagIdCode;
        waitForStart();
        while(opModeIsActive()){


            if (myAprilTagProcessor.getDetections().size() > 0.0){
                AprilTagDetection myAprilTagDetection = myAprilTagProcessor.getDetections().get(0);
                myAprilTagIdCode = myAprilTagDetection.id;
                
            }
            else {
                myAprilTagIdCode = 0;
            }
            telemetry.addData("id", myAprilTagIdCode);
            telemetry.update();



        }
    }

}
