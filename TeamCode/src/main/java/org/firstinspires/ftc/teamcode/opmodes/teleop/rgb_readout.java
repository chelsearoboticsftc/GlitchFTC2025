package org.firstinspires.ftc.teamcode.opmodes.teleop;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import kotlin.math.UMathKt;

@TeleOp
public class rgb_readout extends LinearOpMode{
    @Override
    public void runOpMode() throws InterruptedException {

        ColorSensor colorsensor = hardwareMap.get(ColorSensor.class, "color1");
        int greenmax = 0;
        int bluemax= 0;
        int redmax = 0;
        int greenmin = 1000000000;
        int bluemin = 1000000000;
        int redmin = 1000000000;

        waitForStart();
        while (opModeIsActive()){
            telemetry.addData("green", colorsensor.green());
            if (colorsensor.green() > greenmax){
                greenmax = colorsensor.green();
            }
            if (colorsensor.green() < greenmin){
                greenmin = colorsensor.green();
            }
            if (colorsensor.red() > redmax){
                redmax = colorsensor.red();
            }
            if (colorsensor.red() < redmin){
                redmin = colorsensor.red();
            }
            if (colorsensor.blue() > bluemax){
                bluemax = colorsensor.blue();
            }
            if (colorsensor.blue() < bluemin){
                bluemin = colorsensor.blue();
            }

            telemetry.addData("greenMin", greenmin);
            telemetry.addData("greenMax", greenmax);
            telemetry.addData("redMin", redmin);
            telemetry.addData("redMax", redmax);
            telemetry.addData("blueMin", bluemin);
            telemetry.addData("blueMax", bluemax);
            //telemetry.addData("blue", colorsensor.blue());
            //ptelemetry.addData("red", colorsensor.red());
            //telemetry.addData("alpha", colorsensor.alpha());
            //telemetry.addData("hue", colorsensor.argb());



            telemetry.update();
        }

    }
}
