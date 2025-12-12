package org.firstinspires.ftc.teamcode.opmodes.auton;

import com.acmerobotics.roadrunner.AngularVelConstraint;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.InstantFunction;
import com.acmerobotics.roadrunner.MinVelConstraint;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.VelConstraint;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.subsystems.Index;
import org.firstinspires.ftc.teamcode.subsystems.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.subsystems.Shooter;
import org.firstinspires.ftc.teamcode.subsystems.subsystems.Vision;

import java.util.Arrays;

@Autonomous
public class MeepMeep_blue_far extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // Initialize MecanumDrive. The starting pose is (0, 0) with a 0-degree heading.
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        Shooter shooter = new Shooter(hardwareMap);
        DigitalChannel sensor = hardwareMap.get(DigitalChannel.class, "sensor");
        Index index = new Index(hardwareMap);
        Intake intake = new Intake(hardwareMap);
        Vision limelight = new Vision(hardwareMap);
        double heading;
        int t = 0;

        // Wait for the driver to press start
        waitForStart();
        drive.localizer.setPose(new Pose2d(0,0,0));

        shooter.fullpower(2370);

        if (isStopRequested()) return;
        Actions.runBlocking(drive.actionBuilder(new Pose2d(0, 0,0))
                .strafeToLinearHeading(new Vector2d(10,0),0)
                .turn(Math.toRadians(20))
                .stopAndAdd(new InstantFunction() {
                    @Override
                    public void run() {

                        for (int i = 0; i < 3; i++) {
                            shooter.load();
                            sleep(500);
                            shooter.unload();
                            index.feed();
                            sleep(500);
                            index.stop();
                            sleep(700);
                        }
                        intake.in();
                        index.power(0.5);
                    }
                })

                .strafeToLinearHeading(new Vector2d(32, 16), Math.toRadians(270))

                .strafeToLinearHeading(new Vector2d(32, 36), Math.toRadians(270))

                .strafeToLinearHeading(new Vector2d(10, 0), Math.toRadians(18))

                .stopAndAdd(new InstantFunction() {
                    @Override
                    public void run() {
                        index.power(0);
                        intake.stop();
                        if (limelight.getresult().isValid()){

                            drive.localizer.update();
                            Pose2d pose = drive.localizer.getPose();

                            for (int c = 0; c < 2; c++) {

                                Actions.runBlocking(
                                        drive.actionBuilder(pose)


                                                .turn(Math.toRadians(-limelight.getresult().getTx()))

                                                .build()
                                );

                            }}
                        for (int i = 0; i < 3; i++) {
                            index.feed();
                            sleep(500);
                            index.stop();
                            shooter.load();
                            sleep(500);
                            shooter.unload();
                            index.rotate((int)(index.getpos()+350));
                            while (index.motorbusy()){}
                        }
                    }
                })

                .strafeToLinearHeading(new Vector2d(55, 30), Math.toRadians(-90))
                .strafeToLinearHeading(new Vector2d(55, 50), Math.toRadians(-90))
                .strafeToLinearHeading(new Vector2d(10, 14), Math.toRadians(18))
                .stopAndAdd(new InstantFunction() {
                    @Override
                    public void run() {


                        index.power(0);
                        intake.stop();
                        if (limelight.getresult().isValid()) {

                            drive.localizer.update();
                            Pose2d pose = drive.localizer.getPose();

                            for (int c = 0; c < 2; c++) {

                                Actions.runBlocking(
                                        drive.actionBuilder(pose)


                                                .turn(Math.toRadians(-limelight.getresult().getTx()))

                                                .build()
                                );

                            }
                        }
                        while (sensor.getState()) {
                            index.power(0.15);
                        }
                        index.stop();
                        telemetry.addLine("done step1");
                        telemetry.update();

                        while (!sensor.getState()) {
                            index.power(0.15);
                        }
                        telemetry.addLine("done step2");
                        telemetry.update();

                        index.stop();

                        while (sensor.getState()) {

                            index.power(-0.2);
                        }
                        telemetry.addLine("done step3");
                        telemetry.update();
                        index.stop();


                        index.resetencoder();
                        for (int i = 0; i < 3; i++) {
                            index.feed();
                            sleep(1000);
                            index.stop();
                            shooter.load();
                            sleep(500);
                            shooter.unload();
                            index.rotate((int) (index.getpos() + 350));
                            while (index.motorbusy()) {
                            }
                            sleep(100);

                        }
                        index.power(0.5);
                        intake.in();







                        }

                })
                .strafeToLinearHeading(new Vector2d(50, 20), Math.toRadians(-45))
                .build());
    }
}