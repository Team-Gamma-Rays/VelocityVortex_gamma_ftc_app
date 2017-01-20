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

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import static java.lang.Thread.sleep;

/**
 * This file provides basic Telop driving for a Frank robot.
 * The code is structured as an Iterative OpMode
 * <p/>
 * This OpMode uses the Frank hardware class to define the devices on the robot.
 * All device access is managed through the HardwareFrank class.
 * <p/>
 * This particular OpMode executes a basic Tank Drive Teleop for a Frank
 * <p/>
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name = "Frank Tank: competition funz", group = "Frank")

public class FrankTeleOpCompetition extends OpMode {

    /* Declare OpMode members. */
    Robot frank = new Robot(); // use the class created to define Frank's hardware


    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        frank.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Frank");    //
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

    double left;
    double right;
    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {

        /*
        // test slow down thing
        if(left != 0 &&  right != 0 && gamepad1.left_stick_y == 0 && gamepad1.right_stick_y == 0) {

            // if we were going backwards
            if(left < 0 && right < 0) {
                double i;
                // slow down gradually
                for(i = left; i < 0; i+= .1 ) {
                    frank.portMotor.setPower(i);
                    frank.stbdMotor.setPower(i);
                    Thread.yield();
                    try {
                        // wait for the robot to actually slow down
                        sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // double check we are stopped
                frank.portMotor.setPower(0);
                frank.stbdMotor.setPower(0);
            }
            if(left > 0 && right > 0) {
                double i;
                for(i = left; i > 0; i -= .1 ) {
                    frank.portMotor.setPower(i);
                    frank.stbdMotor.setPower(i);
                    Thread.yield();
                    try {
                        sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                frank.portMotor.setPower(0);
                frank.stbdMotor.setPower(0);
            }
        }
        // end test slow down
        */

        // Run wheels in tank mode (note: The joystick goes negative when pushed forwards, so negate it)
        left = gamepad1.left_stick_y;
        right = gamepad1.right_stick_y;

        double lowerClip = -0.5;
        double upperClip = 0.5;

        if (left < lowerClip) {
            left = lowerClip;
        }
        if (left > upperClip) {
            left = upperClip;
        }
        if ( right < lowerClip ) {
            right = lowerClip;
        }
        if (right > upperClip) {
            right = upperClip;
        }



            if (gamepad1.dpad_up) {
            frank.forkMotor.setPower(-frank.FORK_SPEED);
        } else if (gamepad1.dpad_down) {
            frank.forkMotor.setPower(frank.FORK_SPEED);
        } else {
            frank.forkMotor.setPower(0);
        }

        // Send telemetry message to signify robot running;
        //telemetry.addData("claw",  "Offset = %.2f", fingerPosition);
        telemetry.addData("left", "%.2f", left);
        telemetry.addData("right", "%.2f", right);
        updateTelemetry(telemetry);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
