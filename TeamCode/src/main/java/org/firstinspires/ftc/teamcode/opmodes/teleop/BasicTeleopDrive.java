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
        boolean firing = false;
        waitForStart();

        while (opModeIsActive()) {




            telemetry.update();
            if(gamepad1.left_bumper){
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


                    index.feed();
                    firing = true;

                    Thread.sleep(1000);
                    firing = false;

                    index.stop();
                    shooter.load();
                    Thread.sleep(500);
                    firing = false;
                    shooter.unload();



            }
            if (!gamepad2.b && !gamepad2.x){
                intake.stop();
            }





            if (gamepad2.y){
                indexclick += 1 ;
                shooter.fullpower(2000);
                if (indexclick < 2){
                    index.rotate(135);
                    firing = true;
                    Thread.sleep(3000);
                    firing = false;

                }
                else {
                    int pos = (indexclick*97)+135;
                    index.rotate(pos);
                    firing = true;
                    Thread.sleep(3000);
                    firing = false;
                }



                index.feed();
                firing = true;
                Thread.sleep(1000);
                firing = false;
                index.stop();






            }



            if(!firing){
                firing = false;
                double left_y = gamepad1.left_stick_y *speed;
                double left_x = gamepad1.left_stick_x *speed;
                double right_x= -gamepad1.right_stick_x*speed;
                drive.setDrivePowers(
                    new PoseVelocity2d(
                            new Vector2d(left_y,
                                    left_x),
                            right_x));}
            telemetry.addData("power", index.getpower());
            telemetry.addData("color", index.read());
            telemetry.addData("pos", shooter.whereservo());

            }
        }
        }

