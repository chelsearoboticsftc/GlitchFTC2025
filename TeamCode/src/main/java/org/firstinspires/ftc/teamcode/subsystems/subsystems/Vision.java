package org.firstinspires.ftc.teamcode.subsystems.subsystems;
// This sample vision subsystem only includes the reading of the main apriltag for goals
// it does not include obelisk detection or sorting at this time

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import android.util.Size;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import com.qualcomm.robotcore.hardware.IMU;

public class Vision{

    Limelight3A limelight;
    LLResult result;
    IMU imu;

    public Vision(HardwareMap hardwareMap){
        this.limelight = hardwareMap.get(Limelight3A.class, "limelight");
        this.limelight.setPollRateHz(100);
        this.imu = hardwareMap.get(IMU.class, "imu");
        this.limelight.start();



        //you have to call SampleLimelight limelight; Then limelight.start() in your OpMode
    }
    public LLResult getresult(){
        return limelight.getLatestResult();
    }
    public Pose2d getRobotPos(){
        //for now just setting to zero if you can't see the apriltag.
        double x = 0;
        double y = 0;
        double heading = imu.getRobotYawPitchRollAngles().getYaw();
        if (result != null && result.isValid()) {
            Pose3D botpose = result.getBotpose();
            if (botpose != null) {
                x = botpose.getPosition().x;
                y = botpose.getPosition().y;

            }
        }
        return new Pose2d(x, y, heading);
    }
    public void setPipeLine(int pipeline){
        limelight.pipelineSwitch(pipeline);
    }



}


