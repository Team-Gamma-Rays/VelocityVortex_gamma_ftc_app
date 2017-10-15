package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.TouchSensor;



/**
 * Created by owner on 1/19/2017.
 */
@TeleOp(name = "Frank Finger: TouchSensor", group = "Frank")
@Disabled
public class DrivingTouchSensor extends OpMode {

    /* Declare OpMode members. */
    Robot frank = new Robot(); // use the class created to define Frank's hardware
    TouchSensor touch;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        frank.init(hardwareMap);

        touch = hardwareMap.touchSensor.get("sensor touch");
    }

    public void loop() {
        if (touch.isPressed()) {
            // if Sensor pressed Motors // STOPSHIP: 1/19/2017
            frank.portMotor.setPower(0);
            frank.stbdMotor.setPower(0);
        } else {
            // Keep driving if touch sensor is not pressed
            frank.portMotor.setPower(0.5);
            frank.stbdMotor.setPower(0.5);
        }
    }

}
