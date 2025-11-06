package org.firstinspires.ftc.teamcode.opmodes.teleop;
import org.firstinspires.ftc.teamcode.subsystems.subsystems.Index;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
@TeleOp
@Disabled
public class ballSubsystemTester extends LinearOpMode{
    @Override
    public void runOpMode() throws InterruptedException{
        Index index = new Index(hardwareMap);
        waitForStart();
        while (opModeIsActive()){


            telemetry.update();

        }

    }
}
