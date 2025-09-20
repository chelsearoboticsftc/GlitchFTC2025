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
        int bluemax;
        int redmax;
        int greenmin = 10000000;
        int bluemin;
        int redmin;

        waitForStart();
        while (opModeIsActive()){
            telemetry.addData("green", colorsensor.green());
            if (colorsensor.green() > greenmax){
                greenmax = colorsensor.green();
            }
            if (colorsensor.green() < greenmin){
                greenmin = colorsensor.green();
            }
            telemetry.addData("greenMin", greenmin);
            telemetry.addData("greenMax", greenmax);
            telemetry.addData("blue", colorsensor.blue());
            telemetry.addData("red", colorsensor.red());
            telemetry.addData("alpha", colorsensor.alpha());
            telemetry.addData("hue", colorsensor.argb());



            telemetry.update();
        }

    }
}
