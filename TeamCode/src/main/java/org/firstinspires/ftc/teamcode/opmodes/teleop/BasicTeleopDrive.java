package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.subsystems.Index;
import org.firstinspires.ftc.teamcode.subsystems.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.subsystems.Shooter;
import org.firstinspires.ftc.vision.opencv.PredominantColorProcessor;

@TeleOp
public class BasicTeleopDrive extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        Intake intake = new Intake(hardwareMap);
        Index index = new Index(hardwareMap);
        Shooter shooter = new Shooter(hardwareMap);
        int indexclick = 0;
        double speed = 0.75;

        waitForStart();

        while (opModeIsActive()) {

            double left_y = gamepad1.left_stick_y *speed;
            double left_x = gamepad1.left_stick_x *speed;
            double right_x= -gamepad1.right_stick_x*speed;
            drive.setDrivePowers(
                    new PoseVelocity2d(
                            new Vector2d(left_y,
                                    left_x),
                            right_x));
            telemetry.addData("power", index.getpower());
            telemetry.addData("color", index.read());
            telemetry.addData("pos", shooter.whereservo());


            telemetry.update();
            if(gamepad1.left_bumper){
                speed = 1.0;
            }
            else {
                speed = 0.75;
            }
            if (gamepad2.x) {
                intake.in();
            }
            if (gamepad2.b) {


                    index.feed();
                    Thread.sleep(1000);
                    index.stop();
                    shooter.load();
                    Thread.sleep(500);
                    shooter.unload();



            }
            if (!gamepad2.b && !gamepad2.x){
                intake.stop();
            }





            if (gamepad2.y){
                indexclick += 1 ;
                shooter.fullpower();
                if (indexclick < 2){
                    index.rotate(135);
                    Thread.sleep(3000);

                }
                else {
                    int pos = (indexclick*97)+135;
                    index.rotate(pos);
                    Thread.sleep(3000);
                }



                index.feed();
                Thread.sleep(1000);
                index.stop();
                shooter.load();
                Thread.sleep(500);
                shooter.unload();





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

