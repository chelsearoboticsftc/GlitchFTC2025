package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.example.Index;
import org.firstinspires.ftc.teamcode.subsystems.example.Intake;
import org.firstinspires.ftc.teamcode.subsystems.example.Shooter;
import org.firstinspires.ftc.vision.opencv.PredominantColorProcessor;

@TeleOp
public class BasicTeleopDrive extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        Intake intake = new Intake(hardwareMap);
        Index index = new Index(hardwareMap);
        Shooter shooter = new Shooter(hardwareMap);
        waitForStart();

        while (opModeIsActive()) {
            drive.setDrivePowers(
                    new PoseVelocity2d(
                            new Vector2d(-gamepad1.left_stick_y,
                                    -gamepad1.left_stick_x),
                            -gamepad1.right_stick_x));
            telemetry.addData("power", index.getpower());
            telemetry.addData("color", index.read());
            telemetry.addData("pos", shooter.whereservo());


            telemetry.update();

            if (gamepad1.x) {
                intake.in();
            }
            if (gamepad1.b) {
                shooter.load();
                sleep(5000);
                shooter.unload();

            }
            if (!gamepad1.b && !gamepad1.x){
                intake.stop();
            }
            if (gamepad1.a) {
                index.feed();
                sleep(1000);
                index.feed();


                }
            if (gamepad1.y){
                index.rotate();


            }
            if(index.result.closestSwatch.equals(PredominantColorProcessor.Swatch.ARTIFACT_GREEN)){
                sleep(2500);
                index.feed();
                sleep(1000);
                index.stop();
            }

            }
        }
        }

