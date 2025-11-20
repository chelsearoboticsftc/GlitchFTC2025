package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.subsystems.subsystems.Index;
import org.firstinspires.ftc.teamcode.subsystems.subsystems.Intake;
@TeleOp
public class intake_test extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Index index = new Index(hardwareMap);
        Intake intake = new Intake(hardwareMap);
        double power = 0.1;
        //init index and intake subsystems
        waitForStart();
        while (opModeIsActive()){
            index.power(power);
            //run index at a specifed power
            if(gamepad1.x){
                intake.in();
                //when button a pressed intake the ball
            }
            else intake.stop();
            if(gamepad1.leftBumperWasPressed()){
                power -=0.1;
                //when left bumper pressed drop speed
            }
            if(gamepad1.rightBumperWasPressed()){
                power +=0.1;
                //when right bumper pressed increase speed
            }
        }

    }
}
