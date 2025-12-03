package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.acmerobotics.roadrunner.AngularVelConstraint;
import com.acmerobotics.roadrunner.MinVelConstraint;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.VelConstraint;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.subsystems.Index;
import org.firstinspires.ftc.teamcode.subsystems.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.subsystems.Shooter;
import org.firstinspires.ftc.teamcode.subsystems.subsystems.Vision;
import org.firstinspires.ftc.vision.opencv.PredominantColorProcessor;

import java.util.Arrays;

import kotlin.Unit;

@TeleOp
public class BasicTeleopDrive extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Vision limelight = new Vision(hardwareMap);
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        Intake intake = new Intake(hardwareMap);
        DigitalChannel sensor = hardwareMap.get(DigitalChannel.class, "sensor");
        DigitalChannel green = hardwareMap.get(DigitalChannel.class, "green");
        DigitalChannel red = hardwareMap.get(DigitalChannel.class, "red");
        sensor.setMode(DigitalChannel.Mode.INPUT);
        red.setMode(DigitalChannel.Mode.OUTPUT);
        green.setMode(DigitalChannel.Mode.OUTPUT);

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
        int i = 0;
        VelConstraint baseVelConstraints = new MinVelConstraint(Arrays.asList(
                new TranslationalVelConstraint(10.0),
                new AngularVelConstraint(Math.PI/3)
        ));

        waitForStart();
        shooter.fullpower(3000);


        while (opModeIsActive()) {
            green.setState(true);
            if(gamepad1.dpad_down){
                while (sensor.getState()) {
                    index.power(0.1);
                }
                index.stop();
                telemetry.addLine("done step1");
                telemetry.update();

                while (!sensor.getState()) {
                    index.power(0.1);
                }
                telemetry.addLine("done step2");
                telemetry.update();

                index.stop();
                while (sensor.getState()) {
                    index.power(-0.1);
                }telemetry.addLine("done step3");
                telemetry.update();
                index.stop();



                index.resetencoder();

                index.rotate(20);
                while (index.motorbusy()){

                }
                index.resetencoder();
            }
            //shooter.fullpower(velocity);
            telemetry.update();
            if(gamepad1.leftBumperWasPressed()){
                speed = 1.0;
            }
            else {
                speed = 0.75;
            }
            if (gamepad2.x) {

                intake.in();
                index.power(0.5);
                indexclick=0;

            }
            if(gamepad1.rightBumperWasPressed()){
                shooter.shoot_vel(limelight.getresult().getBotposeAvgDist());

                for (int c = 0; c < 2; c++) {

                    Actions.runBlocking(
                            drive.actionBuilder(new Pose2d(0, 0, 0))


                                    .turn(Math.toRadians(-limelight.getresult().getTx()))

                                    .build()
                    );
                }


            }
            i = 0;
            if(gamepad2.y){
                intake.out();
            }
            if(gamepad2.yWasReleased()){
                intake.stop();
            }
            if (gamepad2.b) {

                    index.feed();
                    load_start = getRuntime();
            }
            if (gamepad2.xWasReleased()){
                intake.stop();
                index.power(0);
                double pos_needed = (355-(index.getpos() % 355));
                int final_pos_needed = (int) (index.getpos()+pos_needed);
                //index get pos
                index.rotate(final_pos_needed);

            }

            if(gamepad2.leftBumperWasPressed()){
                velocity-= 20;
            }
            if (gamepad2.rightBumperWasPressed()){
                velocity+= 20;
            }

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
            if(gamepad2.aWasPressed()){

                while (sensor.getState()) {
                    index.power(0.3);
                }
                index.stop();
                telemetry.addLine("done step1");
                telemetry.update();

                while (!sensor.getState()){
                    index.power(0.3);

                }
                double current_time = getRuntime();
                double start_time = getRuntime();
                while (current_time-start_time <0.3){
                    current_time = getRuntime();
                    index.power(0.4);
                }
                telemetry.addLine("done step2");
                telemetry.update();

                index.stop();
                while (sensor.getState()) {
                    index.power(-0.3);
                }telemetry.addLine("done step3");
                telemetry.update();
                index.stop();





                index.rotate((int)(index.getpos()+50));
                while (index.motorbusy()){

                }



            }


            double left_y = -gamepad1.left_stick_y *0.75;
            double right_x= -gamepad1.right_stick_x*0.75;
            double left_x = -gamepad1.left_stick_x*0.75;
            drive.setDrivePowers(
                new PoseVelocity2d(
                        new Vector2d(left_y,
                                left_x),
                        right_x));
            telemetry.addData("pos", index.getpos());
            telemetry.addData("color", index.read());
            telemetry.addData("pos", shooter.whereservo());
            telemetry.addData("velocity", shooter.getvelocity());
            telemetry.addData("tx", limelight.getresult().getTx());
            telemetry.addData("distance", limelight.getresult().getBotposeAvgDist());
            telemetry.addData("velocity_command", velocity);
            }
        }
        }

