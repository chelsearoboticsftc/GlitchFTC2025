package org.firstinspires.ftc.teamcode.subsystems.example;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {

    //Declare HW objects here

    //Example declare a DcMotorEx object as part of this class called 'motorName'
    DcMotorEx intake;

    //Declare any other global variables for this class here
    private int motorSetPosition = 0;
    private double motorPower = 0;

    public Intake(HardwareMap hardwareMap){
        //Constructor for the SampleSubsystem class.  This code is called everytime you create
        //an object of this class type.  Rename to match your class name.

        //'this' keyword is to eliminate the confusion between objects/attributes which are part of and parameters
        // this class and with the same name.  It refers to objects in the SampleSubsystem class in this case

        //Device names in hardwareMap.get(class,deviceName) must match names from Control Hub
        //configuration exactly.  This is the connection with the Control Hub Config

        //Example code defining a DcMotor object to a motor in the config called "motorName"
        this.intake = hardwareMap.get(DcMotorEx.class,"intake");

        //This defines the behavior at zero power (brake or coast)


    }
    /* Standard functions.  All Chelsea Robotics subsystems shall have init() and update() these  *
     * methods defined. Leave empty if not needed!                                                */
    public void init(){
        /* Call this method at the start of your opmode logic once to execute any logic you       *
         * want to be called on initialization. If none, leave empty!                             */
    }

    public void update(){
        //Call this method each time your opmode logic loops (i.e. inside while(opModeIsActive()){}
        //to execute any logic you want to be called periodically. If none, leave empty!

        //setTargetPosition needs to be called once per loop to keep the REV watchdog happy
        //motorName.setTargetPosition(motorSetPosition);
    }

    public void in(){
        intake.setPower(-1);
    }
    public void out(){
        intake.setPower(1);
    }
    public void stop(){
        intake.setPower(0);
    }
}
