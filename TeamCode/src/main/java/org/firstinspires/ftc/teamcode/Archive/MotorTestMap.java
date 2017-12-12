package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by owner on 1/5/2017.
 */
public class MotorTestMap {
    /* Public OpMode members. */
    public DcMotor motor1 = null;
    public DcMotor motor2 = null;

    /* local OpMode members. */
    HardwareMap hwMap = null;

    /* Constructor */
    public MotorTestMap() {

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        motor1 = hwMap.dcMotor.get("portMotor");
        motor2 = hwMap.dcMotor.get("stbdMotor");

        // Set all motors to zero power
        motor1.setPower(0);
        motor2.setPower(0);

        // Set all motors to run with or without encoders.
        // Use RUN_USING_ENCODERS if encoders are installed. Use RUN_WITHOUT_ENCODER if encoders are not installed.
        motor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}
