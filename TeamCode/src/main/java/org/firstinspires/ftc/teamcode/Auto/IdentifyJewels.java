package org.firstinspires.ftc.teamcode.Auto;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;

/**
 * Created by ACHS-Gamma-Rays on 1/6/2018.
 */

@TeleOp(name = "Identify Jewels", group = "Sensor")
//@Disabled
public class IdentifyJewels extends LinearOpMode{

    ColorSensor colorSensor;

    private enum JewelColor {RED, BLUE};
    JewelColor color;

    static final double RED_LOW   = 2.0;
    static final double RED_HIGH  = 10.0;
    static final double BLUE_LOW  = 200.0;
    static final double BLUE_HIGH = 250.0;

    @Override
    public void runOpMode() {
        // hsvValues is an array that will hold the hue, saturation, and value information.
        float hsvValues[] = {0F,0F,0F};

        // values is a reference to the hsvValues array.
        final float values[] = hsvValues;

        // get a reference to the RelativeLayout so we can change the background
        // color of the Robot Controller app to match the hue detected by the RGB sensor.
        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);

        // get a reference to our ColorSensor object.
        colorSensor = hardwareMap.get(ColorSensor.class, "color_sensor");

        // Set the LED in the beginning
        colorSensor.enableLed(true);

        // wait for the start button to be pressed.
        waitForStart();

        while(opModeIsActive()) {

            // convert the RGB values to HSV values.
            Color.RGBToHSV(colorSensor.red() * 8, colorSensor.green() * 8, colorSensor.blue() * 8, hsvValues);

            double hue = hsvValues[0];

            if (hue >= RED_LOW && hue <= RED_HIGH) {
                color = JewelColor.RED;
            } else if (hue >= BLUE_LOW && hue <= BLUE_HIGH) {
                color = JewelColor.BLUE;
            }

            telemetry.addData("Jewel Color", color);

            // change the background color to match the color detected by the RGB sensor.
            // pass a reference to the hue, saturation, and value array as an argument
            // to the HSVToColor method.
            relativeLayout.post(new Runnable() {
                public void run() {
                    relativeLayout.setBackgroundColor(Color.HSVToColor(0xff, values));
                }
            });

            telemetry.update();

        }

        // Set the panel back to the default color
        relativeLayout.post(new Runnable() {
            public void run() {
                relativeLayout.setBackgroundColor(Color.WHITE);
            }
        });

    }
}
