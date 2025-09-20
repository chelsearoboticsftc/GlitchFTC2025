package org.firstinspires.ftc.teamcode.subsystems.example;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ColorSensor;

//theoretical index test code


public class Index {
    ColorSensor pos1, pos2, pos3;
    private String pos1color;
    private String pos2color;
    private  String pos3color;
    DcMotorEx indexmotor;

    public Index(HardwareMap hardwareMap){

        this.pos1 = hardwareMap.get(ColorSensor.class, "pos1sensor");
        this.pos2 = hardwareMap.get(ColorSensor.class, "pos2sensor");
        this.pos3 = hardwareMap.get(ColorSensor.class, "pos3sensor");
        this.indexmotor = hardwareMap.get(DcMotorEx.class, "indexmotor");
        indexmotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void read(){

        if (pos1.argb() == 300){
            //replace 300 with rgb values for green color ball
            pos1color = "green";


        }
    }

    public void sort(String color){
        indexmotor.setTargetPosition(100);
        //set pos for each correct ball color...
    }


}
