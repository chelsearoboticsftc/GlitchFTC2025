package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.subsystems.Index;
import org.firstinspires.ftc.teamcode.subsystems.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.subsystems.Shooter;
import org.firstinspires.ftc.vision.opencv.PredominantColorProcessor;

import kotlin.Unit;

@TeleOp
public class BasicTeleopDrive extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        Intake intake = new Intake(hardwareMap);

        Index index = new Index(hardwareMap);
        Shooter shooter = new Shooter(hardwareMap);
        int indexclick = 0;
        double speed;
        int velocity = 2220;
        boolean firing = false;
        double load_elapsed;
        double load_start=0;
        double reload_start= 0;
        double reload_elapsed;
        double index_start=0;
        double index_relapsed;
        double index_feed_start=0;
        double index_feed_elapsed;

        waitForStart();
        shooter.fullpower(3000);


        while (opModeIsActive()) {
            shooter.fullpower(velocity);




            telemetry.update();
            if(gamepad1.leftBumperWasPressed()){
                speed = 1.0;
            }
            else {
                speed = 0.75;
            }
            if (gamepad2.x) {
                index.rotate(0);
                intake.in();
                indexclick=0;
            }
            if (gamepad2.b) {
                    index.rotate(0);
                    index.feed();


                    load_start = getRuntime();





            }
            if (!gamepad2.b && !gamepad2.x){
                intake.stop();
            }






            if(gamepad2.leftBumperWasPressed()){
                velocity-= 100;
            }
            if (gamepad2.rightBumperWasPressed()){
                velocity+= 100;
            }
            index.holdpos();










            if (index.read() == PredominantColorProcessor.Swatch.WHITE){
                index.rotate(0);


            }

            index_feed_elapsed = getRuntime()-index_feed_start;
            load_elapsed = getRuntime()-load_start;

            index_relapsed = getRuntime() - index_start;

            if(load_elapsed > 1 && load_start !=0){

                index.stop();
                load_start = 0;
                shooter.load();
                 Thread.sleep(500);
                 shooter.unload();
            }
            reload_elapsed = getRuntime() - reload_start;
            telemetry.addData("time",reload_elapsed);
            telemetry.update();
            if(reload_elapsed > 0.5 && reload_start !=0){

                shooter.unload();
            }


            double left_y = -gamepad1.left_stick_y *0.5;
            double right_x= gamepad1.right_stick_x*0.5;
            double left_x = -gamepad1.left_stick_x*0.5;
            drive.setDrivePowers(
                new PoseVelocity2d(
                        new Vector2d(left_y,
                                left_x),
                        right_x));
            telemetry.addData("power", index.getpower());
            telemetry.addData("color", index.read());
            telemetry.addData("pos", shooter.whereservo());
            telemetry.addData("velocity", shooter.getvelocity());
            }
        }
        }

