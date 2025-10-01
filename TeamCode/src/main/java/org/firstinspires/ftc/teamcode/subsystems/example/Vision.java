package org.firstinspires.ftc.teamcode.subsystems.example;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.hardware.limelightvision.LLResult;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.limelightvision.LLResultTypes;


import java.util.List;

public class Vision {

    //Declare HW objects here

    //Example declare a DcMotorEx object as part of this class called 'motorName'

    Limelight3A limelight;


    public Vision(HardwareMap hardwareMap) {
        //Constructor for the SampleSubsystem class.  This code is called everytime you create
        //an object of this class type.  Rename to match your class name.

        //'this' keyword is to eliminate the confusion between objects/attributes which are part of and parameters
        // this class and with the same name.  It refers to objects in the SampleSubsystem class in this case

        //Device names in hardwareMap.get(class,deviceName) must match names from Control Hub
        //configuration exactly.  This is the connection with the Control Hub Config

        //Example code defining a DcMotor object to a motor in the config called "motorName"

        this.limelight = hardwareMap.get(Limelight3A.class, "limelight");

    }

    /* Standard functions.  All Chelsea Robotics subsystems shall have init() and update() these  *
     * methods defined. Leave empty if not needed!                                                */
    public void init() {


        limelight.setPollRateHz(100); // This sets how often we ask Limelight for data (100 times per second)
        limelight.start(); // This tells Limelight to start looking!
    }
    public void read() {
        LLResult result = limelight.getLatestResult();
        List<LLResultTypes.FiducialResult> fiducials = result.getFiducialResults();
        for (LLResultTypes.FiducialResult fr : fiducials) {
            telemetry.addData("ID:", fr.getFiducialId());

        }
    }

}


