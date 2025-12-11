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
public class MeepMeep_red_far extends LinearOpMode {
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
        shooter.fullpower(2200);

        if (isStopRequested()) return;
        Actions.runBlocking(drive.actionBuilder(new Pose2d(62, 14, 0))
                .lineToX(53)
                .turn(Math.toRadians(-18))
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
                    }
                })

                .strafeToLinearHeading(new Vector2d(36, 30), Math.toRadians(90))

                .strafeToLinearHeading(new Vector2d(36, 50), Math.toRadians(90))


                .strafeToLinearHeading(new Vector2d(60, 14), Math.toRadians(-18))
                .stopAndAdd(new InstantFunction() {
                    @Override
                    public void run() {
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
        
                .strafeToLinearHeading(new Vector2d(12, 30), Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(12, 50), Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(60, 14), Math.toRadians(-18))
                .stopAndAdd(new InstantFunction() {
                    @Override
                    public void run() {
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
                .strafeToLinearHeading(new Vector2d(50, 20), Math.toRadians(-45))
                .build());
    }
}
