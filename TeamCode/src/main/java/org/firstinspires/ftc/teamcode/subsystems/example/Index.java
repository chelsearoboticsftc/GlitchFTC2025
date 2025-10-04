package org.firstinspires.ftc.teamcode.subsystems.example;



import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//theoretical index test code


public class Index {
    ColorSensor pos1, pos2, pos3;
    CRServo index, flapper;

    public String pos1color;
    public String pos2color;
    public  String pos3color;
    private int greenMax = 0;
    private int bluemax = 0;
    int redmax = 0;
    //DcMotorEx indexmotor;

    public Index(HardwareMap hardwareMap){

        this.pos1 = hardwareMap.get(ColorSensor.class, "color1");
        this.index = hardwareMap.get(CRServo.class, "index");
        this.flapper = hardwareMap.get(CRServo.class, "flapper");
        //this.pos2 = hardwareMap.get(ColorSensor.class, "pos2color");
        //this.pos3 = hardwareMap.get(ColorSensor.class, "pos3color");
        //this.indexmotor = hardwareMap.get(DcMotorEx.class, "indexmotor");
        //indexmotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }


    public String read(String pos1color){
        if (pos1.green() > greenMax){
            greenMax = pos1.green();
        }
        if (pos1.red() > redmax){
            redmax = pos1.red();
        }
        if (pos1.blue() > bluemax){
            bluemax = pos1.blue();
        }
        if (bluemax < 1800 && bluemax > 100 && redmax < 1000) {
            //change 2100 fine tune no magic numbers, same for 700
            //replace 300 with rgb values for green color ball
            pos1color = "green";
            bluemax = 0;
            redmax = 0;

        }
        else if (bluemax > 1000 && bluemax > 100 && redmax > 1000) {
            pos1color = "purple";
            bluemax = 0;
            redmax = 0;

            }
        else if (bluemax < 100 ) {
            pos1color = "none";
        }
        return pos1color;

        }


    public void sort(String color){
        //indexmotor.setTargetPosition(100);
        //set pos for each correct ball color...
    }
    public void feed(double power){
        flapper.setPower(power);




    }



}
