package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;


/**
 * Created by owner on 1/21/2017.
 */

@Autonomous(name="Frank: Proportional Line Follower", group="Frank")
public class FrankProportionalFollowLine extends LinearOpMode {

    /* Declare OpMode members. */
    Robot         frank   = new Robot();   // Use Frank's hardware

    double portPower, stbdPower, correction;
    final double PERFECT_LIGHT_VALUE = 0.5;
    final double MOTOR_BASE_POWER = -0.075;
    OpticalDistanceSensor lightSensor;   // Alternative MR ODS sensor

    @Override
    public void runOpMode() {

        /* Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        frank.init(hardwareMap);

        // get a reference to our Light Sensor object.
        lightSensor = hardwareMap.opticalDistanceSensor.get("ods");  // MR ODS sensor.

        // turn on LED of light sensor.
        lightSensor.enableLed(true);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // This loop displays values recieved from the ODS sensor on the driver station phone.
        while (true) {
            correction = (PERFECT_LIGHT_VALUE - lightSensor.getLightDetected());

            //Sets the powers so they are no less than MOTOR_BASE_POWER and apply to correction
            if (correction <= 0) {
                portPower = MOTOR_BASE_POWER - correction;
                stbdPower = MOTOR_BASE_POWER;
            } else {
                portPower = MOTOR_BASE_POWER;
                stbdPower = MOTOR_BASE_POWER + correction;
            }

            //Sets the powers to the motors
            frank.portMotor.setPower(portPower);
            frank.stbdMotor.setPower(stbdPower);

            // Display the light level while we are waiting to start
            telemetry.addData("Light Level", lightSensor.getLightDetected());
            telemetry.update();
        }

    }
}
