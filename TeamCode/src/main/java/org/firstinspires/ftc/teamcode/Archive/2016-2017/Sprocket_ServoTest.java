/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * ADDED BY GAMMA RAYS:
 * This OpMode mode is based upon the PushbotTeleopPOV_Linear class.
 * It uses the common HardwareOurRobot hardware class to define the devices on the robot.
 *
 * This OpMode is designed to test the servos in Sprocket's(our team's robot) original arm configuration.
 * It can be used to determine the optimal servo position values for initial positions
 * and preset servo movements in both Teleop and Autonomous OpModes.
 * In this mode:
 * The X and Y axes of the Left stick move the Horizontal and Vertical arm servos on the Port side of the robot respectively.
 * The Right stick performs the same function, but for arm servos on the Starboard side.
 *
 * All analog stick values and servo positions are sent to the telemetry display on the Driver Station phone.
 */

/**
 * ORIGINAL COMMENT:
 * This OpMode uses the common Pushbot hardware class to define the devices on the robot.
 * All device access is managed through the HardwarePushbot class.
 * The code is structured as a LinearOpMode
 *
 * This particular OpMode executes a POV Game style Teleop for a PushBot
 * In this mode the left stick moves the robot FWD and back, the Right stick turns left and right.
 * It raises and lowers the claw using the Gampad Y and A buttons respectively.
 * It also opens and closes the claws slowly using the left and right Bumper buttons.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Sprocket: ServoTest", group="Sprocket Testing")
@Disabled
public class Sprocket_ServoTest extends LinearOpMode {

    /* Declare OpMode members. */
    HardwareOurRobot robot           = new HardwareOurRobot();   // Uses Our Team's custom hardware scheme.

    @Override
    public void runOpMode() throws InterruptedException {

        float servoLeftX;  //Define variables to store Gamepad1's left stick values
        float servoLeftY;
        float servoRightX; //Define variables to store Gamepad1's right stick values
        float servoRightY;

        double portHorizontalPos; //Define variables to store positions of servos.
        double portVerticalPos;
        double stbdHorizontalPos;
        double stbdVerticalPos;

        final double horizontalSpeed = 0.1; //Set constant speed of servos
        final double verticalSpeed = 0.1;

        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            //Assigns raw controller analog stick values to variables.
            servoLeftX = gamepad1.left_stick_x;
            servoLeftY = gamepad1.left_stick_y;
            servoRightX = gamepad1.right_stick_x;
            servoRightY = gamepad1.right_stick_y;

            //Assigns servo positions to variables.
            portHorizontalPos = robot.portHorizontal.getPosition();
            portVerticalPos = robot.portVertical.getPosition();
            stbdHorizontalPos = robot.stbdHorizontal.getPosition();
            stbdVerticalPos = robot.stbdVertical.getPosition();


            if (servoLeftX > 0.25)
                robot.portHorizontal.setPosition(portHorizontalPos + horizontalSpeed);
            else if (servoLeftX < -0.25)
                robot.portHorizontal.setPosition(portHorizontalPos - horizontalSpeed);

            if (servoLeftY > 0.25)
                robot.portVertical.setPosition(portVerticalPos + verticalSpeed);
            else if (servoLeftY < -0.25)
                robot.portVertical.setPosition(portVerticalPos - verticalSpeed);

            if (servoRightX > 0.25)
                robot.stbdHorizontal.setPosition(stbdHorizontalPos + horizontalSpeed);
            else if (servoRightX < -0.25)
                robot.stbdHorizontal.setPosition(stbdHorizontalPos - horizontalSpeed);

            if (servoRightY > 0.25)
                robot.stbdVertical.setPosition(stbdVerticalPos + verticalSpeed);
            else if (servoRightY < -0.25)
                robot.stbdVertical.setPosition(stbdVerticalPos - verticalSpeed);


            // Send telemetry messages corresponding to controller analog stick values.
            telemetry.addData("Left Stick X:",  "%.2f", servoLeftX);
            telemetry.addData("Left Stick Y:", "%.2f", servoLeftY);
            telemetry.addData("Right Stick X:", "%.2f", servoRightX);
            telemetry.addData("Right Stick Y:", "%.2f", servoRightY);
            telemetry.update();

            //Send telemetry messages corresponding to servo positions.
            telemetry.addData("portHorizontal:", "%.2f", portHorizontalPos);
            telemetry.addData("portVertical:", "%.2f", portVerticalPos);
            telemetry.addData("stbdHorizontal:", "%.2f", stbdHorizontalPos);
            telemetry.addData("stbdVertical:", "%.2f", stbdVerticalPos);
            telemetry.update();

            // Pause for metronome tick.  40 mS each cycle = update 25 times a second.
            robot.waitForTick(40);
        }
    }
}
