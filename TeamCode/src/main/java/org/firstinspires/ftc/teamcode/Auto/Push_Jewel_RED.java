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
package org.firstinspires.ftc.teamcode.Auto;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * ADDED BY GAMMA RAYS:
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

@TeleOp(name="Push Jewel RED", group="Testing")
//@Disabled
public class Push_Jewel_RED extends LinearOpMode {

    Servo servoLift   = null;
    Servo servoRotate = null;

    ColorSensor colorSensor;

    private enum JewelColor {RED, BLUE};
    JewelColor color;

    static final double RED_MIN   = 2.0;
    static final double RED_MAX   = 10.0;
    static final double BLUE_MIN  = 200.0;
    static final double BLUE_MAX  = 250.0;

    @Override
    public void runOpMode() {

        servoLift   = hardwareMap.get(Servo.class, "jewel_lift");
        servoRotate = hardwareMap.get(Servo.class, "jewel_rotate");

        final double SERVO_DELTA = 0.1; //Set amount to move servo

        final double LIFT_HOME   = 0.0;
        final double ROTATE_HOME = 0.0;
        final double PUSH_RIGHT_POSITION = 1.0;
        final double PUSH_LEFT_POSITION  = 0.0;

        // hsvValues is an array that will hold the hue, saturation, and value information.
        float hsvValues[] = {0F,0F,0F};

        // values is a reference to the hsvValues array.
        final float values[] = hsvValues;

        // get a reference to our ColorSensor object.
        colorSensor = hardwareMap.get(ColorSensor.class, "color_sensor");

        // Set the LED in the beginning
        colorSensor.enableLed(true);

        servoLift.setPosition(LIFT_HOME);
        servoRotate.setPosition(ROTATE_HOME);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Auto", "Ready to Start");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            double liftPosition   = servoLift.getPosition();
            double rotatePosition = servoRotate.getPosition();

            // convert the RGB values to HSV values.
            Color.RGBToHSV(colorSensor.red() * 8, colorSensor.green() * 8, colorSensor.blue() * 8, hsvValues);

            double hue = hsvValues[0];

            //Send telemetry messages corresponding to servo positions.
            telemetry.addData("jewel_lift Position: ", liftPosition);
            telemetry.addData("jewel_rotate Position: ", rotatePosition);
            telemetry.addData("Jewel Color: ", color);
            telemetry.update();

        }

        servoLift.setPosition(1.0);
        servoRotate.setPosition(1.0);
        // convert the RGB values to HSV values.
        Color.RGBToHSV(colorSensor.red() * 8, colorSensor.green() * 8, colorSensor.blue() * 8, hsvValues);
        double hue = hsvValues[0];
        if (hue >= RED_MIN && hue <= RED_MAX) {
            color = JewelColor.RED;
        } else if (hue >= BLUE_MIN && hue <= BLUE_MAX) {
            color = JewelColor.BLUE;
        }
        if (color == JewelColor.RED) {
            servoRotate.setPosition(PUSH_RIGHT_POSITION);
            idle();
        } else if (color == JewelColor.BLUE) {
            servoRotate.setPosition(PUSH_LEFT_POSITION);
            idle();
        }
        //wait
        sleep(1000);
        //move pusher back to home
        servoLift.setPosition(LIFT_HOME);
        servoRotate.setPosition(ROTATE_HOME);
        //let the operator know the program is complete
        telemetry.addData("Auto", "Complete");
        telemetry.update();

    }
}
