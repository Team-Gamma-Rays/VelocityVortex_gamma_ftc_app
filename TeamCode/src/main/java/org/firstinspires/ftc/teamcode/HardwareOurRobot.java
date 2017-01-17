package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This is NOT an opmode.
 *
 * This class can be used to define all the specific hardware for a single robot.
 * In this case that robot is a Pushbot.
 * See PushbotTeleopTank_Iterative and others classes starting with "Pushbot" for usage examples.
 *
 * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are lower case and some have single spaces between words.
 *
 * Motor channel:  Left  drive motor:        "left_drive"
 * Motor channel:  Right drive motor:        "right_drive"
 * Motor channel:  Manipulator drive motor:  "left_arm"
 * Servo channel:  Servo to open left claw:  "left_hand"
 * Servo channel:  Servo to open right claw: "right_hand"
 */
public class HardwareOurRobot
{
    /* Public OpMode members. */
    public DcMotor  leftfrontMotor   = null;
    public DcMotor  rightfrontMotor  = null;
    public DcMotor  leftrearMotor    = null;
    public DcMotor  rightrearMotor   = null;
    //public DcMotor  shooterMotor     = null;
    //public DcMotor  raiserMotor      = null;
    public Servo    portVertical     = null;
    public Servo    portHorizontal   = null;
    //public Servo    portElbow        = null;
    public Servo    stbdVertical     = null;
    public Servo    stbdHorizontal   = null;

    public static final double MID_SERVO          =  0.5 ;
    public static final double VERT_HOME          =  0.5 ;
    public static final double HORIZ_HOME         =  0.5 ;
    //public static final double ELBOW_HOME         =  0.5 ;
    public static final double RAISER_UP_POWER    =  0.6 ;
    //public static final double SHOOTER_POWER      =  1 ;
    public static final double RAISER_DOWN_POWER  = -0.6 ;

    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public HardwareOurRobot(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        leftfrontMotor   = hwMap.dcMotor.get("leftfront_drive");
        rightfrontMotor  = hwMap.dcMotor.get("rightfront_drive");
        leftrearMotor    = hwMap.dcMotor.get("leftrear_drive");
        rightrearMotor   = hwMap.dcMotor.get("rightrear_drive");
        //shooterMotor     = hwMap.dcMotor.get("shooter_motor");
        //raiserMotor      = hwMap.dcMotor.get("extender_motor");
        leftfrontMotor.setDirection(DcMotor.Direction.FORWARD);   // Set to REVERSE if using AndyMark motors
        rightfrontMotor.setDirection(DcMotor.Direction.REVERSE);  // Set to FORWARD if using AndyMark motors
        leftrearMotor.setDirection(DcMotor.Direction.FORWARD);    // Set to REVERSE if using AndyMark motors
        rightrearMotor.setDirection(DcMotor.Direction.REVERSE);   // Set to FORWARD if using AndyMark motors

        // Set all motors to zero power
        leftfrontMotor.setPower(0);
        rightfrontMotor.setPower(0);
        leftrearMotor.setPower(0);
        rightrearMotor.setPower(0);
        //shooterMotor.setPower(0);
        //raiserMotor.setPower(0);

        // Set all motors to run with or without encoders.
        // Use RUN_USING_ENCODERS if encoders are installed. Use RUN_WITHOUT_ENCODER if encoders are not installed.
        leftfrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightfrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftrearMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightrearMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //shooterMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //raiserMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Define and initialize ALL installed servos.
        portVertical = hwMap.servo.get("left_vert");
        portHorizontal = hwMap.servo.get("left_horiz");
        //portElbow = hwMap.servo.get("port_elbow");
        stbdVertical = hwMap.servo.get("right_vert");
        stbdHorizontal = hwMap.servo.get("right_horiz");

        portVertical.setPosition(VERT_HOME);
        portHorizontal.setPosition(HORIZ_HOME);
        //portElbow.setPosition(ELBOW_HOME);
        stbdVertical.setPosition(VERT_HOME);
        stbdHorizontal.setPosition(HORIZ_HOME);
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

