package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

/**
 * Created by Owner on 1/19/2017.
 */

//Assumes that the sensor is mounted on the left side of the robot (in our case on the "front", or fork side), placing the robot on the right side of the line.

@Autonomous(name = "Frank: Line Follower w/ Gradient", group = "Frank")
public class FrankFollowLineGradient extends OpMode {

    /* Declare OpMode members. */
    Robot         frank   = new Robot();   // Use Frank's hardware

    OpticalDistanceSensor ods;

    static final double TURN_SPEED = -0.2;
    static final double FORWARD_SPEED = -0.5;

    //Optical Distance Sensor Values
    //Currently dummy values, change to real values, then delete this comment
    static final double FLOOR_VALUE = 0.5;
    static final double LINE_VALUE = 0.25;
    static final double GRADIENT_VALUE = 0.5;

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
        else if (reflectance == GRADIENT_VALUE){
            frank.portMotor.setPower(FORWARD_SPEED);
            frank.stbdMotor.setPower(FORWARD_SPEED);
        }
        else {
            frank.portMotor.setPower(-TURN_SPEED);
            frank.stbdMotor.setPower(0);
            telemetry.addData("Reflectance", reflectance);
        }
    }


}
