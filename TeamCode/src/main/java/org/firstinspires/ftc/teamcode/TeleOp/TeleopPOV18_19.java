package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Hardware.Hardware18_19_New;

@TeleOp(name="Test 18-19: Teleop POV", group="Test 18-19")
public class TeleopPOV18_19 extends LinearOpMode{

    Hardware18_19_New robot = new Hardware18_19_New();

    @Override
    public void runOpMode(){

        double leftFront;
        double rightFront;
        double leftRear;
        double rightRear;

        double left;
        double right;

        double drive;
        double turn;
        double max;

        robot.init(hardwareMap);


        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            drive = -gamepad1.left_stick_y;
            turn  = gamepad1.right_stick_x;

            // Combine drive and turn for blended motion.
            left  = drive + turn;
            right = drive - turn;

            // Normalize the values so neither exceed +/- 1.0
            max = Math.max(Math.abs(left), Math.abs(right));
            if (max > 1.0) {
                left /= max;
                right /= max;
            }

            leftFront = left;
            rightFront = right;
            leftRear = left;
            rightRear = right;

            // Output the safe vales to the motor drives.
            robot.leftFrontDrive.setPower(leftFront);
            robot.rightFrontDrive.setPower(rightFront);
            robot.leftRearDrive.setPower(leftRear);
            robot.rightRearDrive.setPower(rightRear);

        }
    }
}
