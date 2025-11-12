package org.firstinspires.ftc.teamcode.subsystems.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.utils.LookupTable;

public class Shooter {

    Servo shooter_Reloader;
    DcMotorEx motor, motor2;
    private final LookupTable distanceToVelocity = new LookupTable(ShooterConstants.LOOKUP_TABLE);
    public Shooter(HardwareMap hardwareMap) {
        this.shooter_Reloader = hardwareMap.get(Servo.class, "shooter_Reloader");
        this.motor = hardwareMap.get(DcMotorEx.class, "shooter");

        motor.setZeroPowerBehavior(ShooterConstants.ZERO_POWER_BEHAVIOR);


        //This defines the motor direction (forward or reversed)

        //This defines the motor direction (forward or reversed)
        motor.setDirection(ShooterConstants.MOTOR_DIRECTION);

        /* This defines the motor velocity PIDF gains.  Velocity PIDF values determine control    *
         * around a target velocity (setTargetVelocity) OR how fast the system responds to a      *
         * change in set position (setTargetPosition).                                            */
        motor.setVelocityPIDFCoefficients(
                ShooterConstants.VELOCITY_P, //Proportional Gain
                ShooterConstants.VELOCITY_I, //Integral Gain
                ShooterConstants.VELOCITY_D, //Derivative Gain
                ShooterConstants.VELOCITY_F);//Feed Forward Gain


        /* This defines the motor position PID P gain. Position control only needs P gain since   *
         * once the system reaches the target position since once at position you're only         *
         * disturbances in the system                                                             */
        motor.setPositionPIDFCoefficients(
                ShooterConstants.POSITION_P);//Proportional Gain




    }


    public void init() {


    }

    public void load() {
        shooter_Reloader.setDirection(Servo.Direction.FORWARD);
        shooter_Reloader.setPosition(0.2);
    }

    public void unload() {
        shooter_Reloader.setDirection(Servo.Direction.FORWARD);
        shooter_Reloader.setPosition(0.6);

    }
    public double whereservo(){
        return shooter_Reloader.getPosition();
    }

    public void shoot(double distance) {
        double velocity = distanceToVelocity.interpolate(distance);
        this.setMotorVelocity(velocity);

        // TODO - is ball already engaged, or does it need to be dropped,
        // maybe after a short delay to allow the motor to spin up?
    }

    public void setMotorVelocity(double angularRate) {

        this.motor.setVelocity(angularRate);

    }
    public void fullpower(int velocity){
        this.motor.setVelocity(velocity);


    }
    public double getvelocity(){
        return this.motor.getVelocity();
    }
}

