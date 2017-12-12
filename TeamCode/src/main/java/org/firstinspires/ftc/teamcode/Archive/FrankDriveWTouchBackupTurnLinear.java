package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by owner on 1/20/2017.
 */

@Autonomous(name="Frank: Drive w/ Touch Backup and Turn", group="Frank")
@Disabled
public class FrankDriveWTouchBackupTurnLinear extends LinearOpMode {

    /* Declare OpMode members. */
    Robot         frank   = new Robot();   // Use Frank's hardware
    TouchSensor touchSensor;

    private ElapsedTime runtime = new ElapsedTime();

    //Time constants
    final long BACKUP_TIME = 1000;
    final long TURN_BASE_TIME = 250;
    final long MAX_RANDOM_TIME = 1000;

    //Motor power constants
    //Values are negative because in this OpMode we want to drive Frank's motors forward on the bumper side,
    //which is normally the back.
    final double FORWARD_POWER = -0.5;
    final double TURN_POWER = -0.25;
    final double BACKWARD_POWER = -0.25;

    @Override
    public void runOpMode() throws InterruptedException {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        frank.init(hardwareMap);

        touchSensor = hardwareMap.touchSensor.get("sensor touch");

        //Wait for the start button to be pressed
        waitForStart();

        while(true) {
            //Back up and turn if the touch sensor is pressed
            if(touchSensor.isPressed()) {
            //Set the motors to drive in backwards at power defined in BACKWARDS_POWER
                frank.portMotor.setPower(-BACKWARD_POWER);
                frank.stbdMotor.setPower(-BACKWARD_POWER);
                telemetry.addData("State", "Backing Up");
                sleep(BACKUP_TIME);

                //Set the motors to turn the robot right at power defined in TURN_POWER
                frank.portMotor.setPower(TURN_POWER);
                frank.stbdMotor.setPower(-TURN_POWER);
                long randomTime = (long) (TURN_BASE_TIME + Math.random()*MAX_RANDOM_TIME);
                telemetry.addData("Turn Time", randomTime);
                sleep(randomTime);
            } else {
                //Set the motors to drive forward at power defined in FORWARD_POWER
                frank.portMotor.setPower(FORWARD_POWER);
                frank.stbdMotor.setPower(FORWARD_POWER);
                telemetry.addData("State", "Driving Forward");
            }

            /*
            //Wait for a hardware cycle to allow other processes to run
            waitOneHardwareCycle();
            */
        }
    }
}
