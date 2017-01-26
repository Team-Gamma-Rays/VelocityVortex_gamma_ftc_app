package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by owner on 1/5/2017.
 */
public class Robot {
    /* Public OpMode members. */
    public DcMotor portMotor = null;
    public DcMotor stbdMotor = null;
    public DcMotor forkMotor = null;

    public final double FORK_SPEED  = 0.075 ;                 // sets rate to move fork motor

    /* local OpMode members. */
    HardwareMap hwMap = null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public Robot() {

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        portMotor = hwMap.dcMotor.get("port motor");
        stbdMotor = hwMap.dcMotor.get("stbd motor");
        forkMotor = hwMap.dcMotor.get("fork motor");

        portMotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        stbdMotor.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors

        // Set all motors to zero power
        portMotor.setPower(0);
        stbdMotor.setPower(0);
        forkMotor.setPower(0);

        // Set all motors to run with or without encoders.
        // Use RUN_USING_ENCODERS if encoders are installed. Use RUN_WITHOUT_ENCODER if encoders are not installed.
        portMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        stbdMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        forkMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    /***
     *
     * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
     * periodic tick.  This is used to compensate for varying processing times for each cycle.
     * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
     *
     * @param periodMs  Length of wait cycle in mSec.
     * @throws InterruptedException
     */
    public void waitForTick(long periodMs) throws InterruptedException {

        long  remaining = periodMs - (long)period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0)
            Thread.sleep(remaining);

        // Reset the cycle clock for the next pass.
        period.reset();
    }
}
