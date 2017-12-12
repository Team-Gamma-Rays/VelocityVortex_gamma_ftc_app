package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.robot.Robot;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by owner on 1/5/2017.
 */

@TeleOp(name="Testing: Motor Controllers", group="Testing")
@Disabled
public class MotorTestOp extends OpMode{

    /* Declare OpMode members. */
    MotorTestMap robot = new MotorTestMap();

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
        updateTelemetry(telemetry);

    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        float motor1;
        float motor2;

        // Run wheels in tank mode (note: The joystick goes negative when pushed forwards, so negate it)
        motor1 = -gamepad1.left_stick_y;
        motor2 = -gamepad1.right_stick_y;

        motor1 = Range.clip(motor1, -1, 1);
        motor2 = Range.clip(motor2, -1, 1);

        robot.motor1.setPower(motor1);
        robot.motor2.setPower(motor2);
    }

    /*
    * Code to run ONCE after the driver hits STOP
    */
    @Override
    public void stop() {
    }

}
