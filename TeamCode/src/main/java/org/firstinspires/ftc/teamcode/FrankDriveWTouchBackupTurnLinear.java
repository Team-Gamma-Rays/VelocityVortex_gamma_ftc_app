package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by owner on 1/20/2017.
 */

@Autonomous(name="Frank: Drive w/ Touch Backup and Turn", group="Frank")
public class FrankDriveWTouchBackupTurnLinear extends LinearOpMode {

    /* Declare OpMode members. */
    Robot         frank   = new Robot();   // Use Frank's hardware
    TouchSensor touchSensor;

    private ElapsedTime runtime = new ElapsedTime();

    //Time constants
    final int BACKUP_TIME = 1000;
    final int TURN_TIME = 250;

    @Override
    public void runOpMode() throws InterruptedException {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        frank.init(hardwareMap);

        touchSensor = hardwareMap.touchSensor.get("sensor touch");

        //Wait for the start button to be pressed
        waitForStart();
    }
}
