package org.firstinspires.ftc.teamcode.subsystems.subsystems;



import static org.firstinspires.ftc.vision.opencv.PredominantColorProcessor.Swatch.ARTIFACT_GREEN;

import android.util.Size;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.PIDCoefficients;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.opencv.ImageRegion;
import org.firstinspires.ftc.vision.opencv.PredominantColorProcessor;
//theoretical index test code





public class Index {
    ColorSensor pos1, pos2, pos3;
    CRServo flapper;
    DcMotorEx index;
    PIDCoefficients pidvalues;
    public PredominantColorProcessor.Result result;
    public PredominantColorProcessor colorSensor;
    public VisionPortal portal;
    public String pos1color;
    public String pos2color;
    public  String pos3color;
    private int greenMax = 0;
    private int bluemax = 0;
    int redmax = 0;
    //DcMotorEx indexmotor;

    public Index(HardwareMap hardwareMap){
        colorSensor = new PredominantColorProcessor.Builder()
                .setRoi(ImageRegion.asUnityCenterCoordinates(-0.1, 0.1, 0.1, -0.1))
                .setSwatches(
                        ARTIFACT_GREEN,
                        PredominantColorProcessor.Swatch.ARTIFACT_PURPLE,
                        PredominantColorProcessor.Swatch.RED,
                        PredominantColorProcessor.Swatch.BLUE,
                        PredominantColorProcessor.Swatch.YELLOW,
                        PredominantColorProcessor.Swatch.BLACK,
                        PredominantColorProcessor.Swatch.WHITE)
                .build();
        portal = new VisionPortal.Builder()
                .addProcessor(colorSensor)
                .setCameraResolution(new Size(320, 240))
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .build();
        this.pos1 = hardwareMap.get(ColorSensor.class, "color1");
        this.index = hardwareMap.get(DcMotorEx.class, "index");
        this.flapper = hardwareMap.get(CRServo.class, "flapper");
        //pidvalues.p = 1.4;
       // pidvalues.i = 0.1;
        index.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        index.setPositionPIDFCoefficients(3.5);
        //index.setVelocityPIDFCoefficients(2,1,5,2);

        //this.pos2 = hardwareMap.get(ColorSensor.class, "pos2color");
        //this.pos3 = hardwareMap.get(ColorSensor.class, "pos3color");
        //this.indexmotor = hardwareMap.get(DcMotorEx.class, "indexmotor");
        //indexmotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }


    public PredominantColorProcessor.Swatch read(){
        result = colorSensor.getAnalysis();

        return result.closestSwatch;

        }


    public void sort(){

    }
    public void feed(){
        flapper.setPower(1);



    }
    public int rotate(int pos){
        //index.getController().setServoPosition(1, 0.000001);
        index.setTargetPosition(pos);



        index.setPower(-1);
        index.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        return index.getCurrentPosition();

    }
    public void stop() {
        //index.getController().setServoPosition(1, 0.000001);
        flapper.setPower(0);
    }
    public double getpower() {
        //index.getController().setServoPosition(1, 0.000001);
        return index.getCurrentPosition();
    }
    public boolean motorbusy(){
        return index.isBusy();
    }



}
