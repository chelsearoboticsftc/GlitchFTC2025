package org.firstinspires.ftc.teamcode.opmodes.auton;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.subsystems.Index;
import org.firstinspires.ftc.teamcode.subsystems.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.subsystems.Shooter;
import org.firstinspires.ftc.teamcode.subsystems.subsystems.Vision;

@Autonomous
public class blue_close_Auton extends LinearOpMode {
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

        // Road Runner uses inches, so we convert 10 feet to 120 inches.
        // We create an "action" to drive forward (along the X-axis) by 120 inches.
        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(0, 0, 0))


                        .lineToX(-45)


                        .build()
        );
        drive.localizer.setPose(new Pose2d(0,0,0));


        telemetry.addData("tx", limelight.getresult().getTx());
        telemetry.update();


        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(0, 0, 0))


                        .turn(Math.toRadians(-limelight.getresult().getTx()))


                        .build()
        );
        drive.localizer.setPose(new Pose2d(0,0,0));


        Thread.sleep(1000);

        for (int i = 0; i < 3; i++) {

            shooter.load();
            Thread.sleep(500);
            shooter.unload();

            index.feed();
            Thread.sleep(500);
            index.stop();
            Thread.sleep(700);

        }
        intake.in();
        index.power(0.5);

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(0, 0,0 ))

                        .lineToX(-4)
                        .turn(Math.toRadians(-130))


                        .build()
        );
        drive.localizer.setPose(new Pose2d(0,0,0));

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(0, 0,0))

                        .lineToX(-38)



                        .build()
        );
        drive.localizer.setPose(new Pose2d(0,0,0));
        double pos_needed = (355-(index.getpos() % 355));
        int final_pos_needed = (int) (index.getpos()+pos_needed);
        //index get pos
        index.rotate(final_pos_needed);
        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(0,0,0))

                        .lineToX(38)
                        .turn(Math.toRadians(130))
                        .build()

        );
        index.power(0);
        intake.stop();
        drive.localizer.setPose(new Pose2d(0,0,0));


        telemetry.addData("tx", limelight.getresult().getTx());
        telemetry.update();


        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(0, 0, 0))


                        .turn(Math.toRadians(-limelight.getresult().getTx()))


                        .build()
        );
        drive.localizer.setPose(new Pose2d(0,0,0));









        for (int i = 0; i < 3; i++) {


            index.feed();
            Thread.sleep(500);
            index.stop();

            shooter.load();
            Thread.sleep(500);

            shooter.unload();







            index.rotate((int)(index.getpos()+350));
            while (index.motorbusy()){

            }

        }
        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(0, 0, 0))


                        .strafeTo(new Vector2d(0,20))


                        .build()
        );
        drive.localizer.setPose(new Pose2d(0,0,0));

    }}