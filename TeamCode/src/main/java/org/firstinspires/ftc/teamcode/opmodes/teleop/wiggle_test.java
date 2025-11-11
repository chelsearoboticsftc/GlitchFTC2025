package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.subsystems.subsystems.Index;
@TeleOp
public class wiggle_test extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        DigitalChannel sensor = hardwareMap.get(DigitalChannel.class, "sensor");
        Index index = new Index(hardwareMap);
        sensor.setMode(DigitalChannel.Mode.INPUT);
        waitForStart();

            while (sensor.getState()){
               index.power(0.1);
            }
            while(!sensor.getState()){
                index.power(0.1);
            }
            index.stop();
        while (sensor.getState()){
            index.power(-0.02);
        }
        index.resetencoder();
        index.rotate(40);


    }
}

