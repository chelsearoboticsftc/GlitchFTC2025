package com.example.meepmeep;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeep_red_far {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(70, 70, Math.toRadians(180), Math.toRadians(180), 16)
                .build();




        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(62, 14, 0))
                .lineToX(53)
                .turn(Math.toRadians(-18))
                .waitSeconds(6)

                .strafeToLinearHeading(new Vector2d(36,30),Math.toRadians(90))

                .strafeToLinearHeading(new Vector2d(36,50),Math.toRadians(90))


                .strafeToLinearHeading(new Vector2d(60,14),Math.toRadians(-18))
                .waitSeconds(6)
                .strafeToLinearHeading(new Vector2d(12,30),Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(12,50),Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(60,14),Math.toRadians(-18))
                .waitSeconds(6)
                .strafeToLinearHeading(new Vector2d(50,20),Math.toRadians(-45))
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_DECODE_JUICE_DARK)
                .setDarkMode(true)

                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}