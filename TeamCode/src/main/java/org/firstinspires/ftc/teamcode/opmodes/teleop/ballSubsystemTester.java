package org.firstinspires.ftc.teamcode.opmodes.teleop;
import org.firstinspires.ftc.teamcode.subsystems.example.Index;
import org.firstinspires.ftc.teamcode.subsystems.example.SampleSubsystem;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
@TeleOp
public class ballSubsystemTester extends LinearOpMode{
    @Override
    public void runOpMode() throws InterruptedException{
        Index index = new Index(hardwareMap);
        waitForStart();
        while (opModeIsActive()){
            String color = index.read(index.pos1color);
            telemetry.addData("color", color);
            telemetry.update();

        }

    }
}
