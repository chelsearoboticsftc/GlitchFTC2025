package org.firstinspires.ftc.teamcode.opmodes.auton;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.subsystems.Index;
import org.firstinspires.ftc.teamcode.subsystems.subsystems.Shooter;

@Autonomous
public class blue_close_Auton extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // Initialize MecanumDrive. The starting pose is (0, 0) with a 0-degree heading.
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        Shooter shooter = new Shooter(hardwareMap);
        Index index = new Index(hardwareMap);
        // Wait for the driver to press start
        waitForStart();
        index.rotate(0);
        shooter.fullpower(2250);

        Thread.sleep((3000));

        if (isStopRequested()) return;

        // Road Runner uses inches, so we convert 10 feet to 120 inches.
        // We create an "action" to drive forward (along the X-axis) by 120 inches.
        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(0, 0, 0))



                        .lineToX(-80)

                        .build()
        );
        for (int i = 0; i < 4; i++) {

            shooter.load();
            Thread.sleep(500);
            shooter.unload();
            Thread.sleep(500);
            index.feed();
            Thread.sleep(1000);
            index.stop();

        }

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(0, 0, 0))


                        .turn(Math.toRadians(-20))
                        .lineToX(-30)


                        .build()
        );

    }
}
