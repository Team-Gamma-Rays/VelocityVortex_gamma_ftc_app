package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

/**
 * Created by Owner on 1/19/2017.
 */

//Followed Intellitek Tutorial - Module 3 - Lesson 16 - Following a Line
//Assumes that the sensor is mounted on the left side of the robot (in our case on the "front", or fork side), placing the robot on the right side of the line.
//This OpMode will shimmy the robot back and forth, for  a smoother driving experience, try the method proposed in the Modern Robotics ODS Video that uses a gradient value.

@Autonomous(name = "Frank: Line Follower", group = "Frank")
@Disabled
public class FrankFollowLine extends OpMode {

    /* Declare OpMode members. */
    Robot         frank   = new Robot();   // Use Frank's hardware

    OpticalDistanceSensor ods;

    static final double TURN_SPEED = -0.2;

    //Optical Distance Sensor Values
    //Currently dummy values, change to real values, then delete this comment
    static final double FLOOR_VALUE = 0.5;
    static final double LINE_VALUE = 0.25;

    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        frank.init(hardwareMap);

        ods = hardwareMap.opticalDistanceSensor.get("ods");

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Frank");    //
        updateTelemetry(telemetry);
    }

    @Override
    public void loop() {

        double reflectance = ods.getLightDetected();

        //Less than is used for a white line on gray floor; gray reflects less light than white
        //Use greater than for a white line on dark floor.
        if (reflectance <= LINE_VALUE ) {
            frank.portMotor.setPower(0);
            frank.stbdMotor.setPower(-TURN_SPEED);
        }
        else {
            frank.portMotor.setPower(-TURN_SPEED);
            frank.stbdMotor.setPower(0);
            telemetry.addData("Reflectance", reflectance);
        }
    }


}
