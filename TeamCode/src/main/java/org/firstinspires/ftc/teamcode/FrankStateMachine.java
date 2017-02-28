package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by owner on 1/21/2017.
 */

//@Disabled
@Autonomous(name="Frank: State Machine", group="Frank")
public class FrankStateMachine extends OpMode{

    /* Declare OpMode members. */
    Robot         frank   = new Robot();   // Use Frank's hardware

    static final double FORWARD_POWER = -0.2;
    static final double TURNING_POWER = -0.1;

    OpticalDistanceSensor lightSensorFloor;
    OpticalDistanceSensor lightSensorForward;
    double distanceValue;
    static final double BUTTON_DISTANCE_LOW = 0.5;
    static final double BUTTON_DISTANCE_HIGH = 1;
    boolean readyToPress;

    //For line follower
    final double PERFECT_LIGHT_VALUE = 0.5;
    final double MOTOR_BASE_POWER = -0.075;
    double portLinePower;
    double stbdLinePower;
    double correction;

    TouchSensor touch;

    ElapsedTime time;

    static final double FORWARD_TIME = 1.0;
    static final double TURN_TIME = 1.0;
    int count = 0;

    /*
    //ranges for color values
    static final double RED_LOW = 0.5;
    static final double RED_HIGH = 1;
    static final double BLUE_LOW = 0.5;
    static final double BLUE_HIGH = 1;

    enum BeaconColor {red, blue}
    BeaconColor beaconColor;
    */

    //drivingFWD -> Driving Forward
    //drivingREV -> Driving in Reverse
    //turningLeft -> Turning Left
    //turningRight -> turningRight
    enum State {drivingFWD1, drivingFWD2, drivingFWD3, drivingREV, turningLeft, turningRight, resting1, resting2, followingLine, pushingButton, done}
    State state;

    @Override
    public void init() {
        /* Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        frank.init(hardwareMap);

        // get a reference to our Light Sensor object.
        lightSensorFloor = hardwareMap.opticalDistanceSensor.get("ods"); // MR ODS sensor. ods is pointed at floor
        lightSensorForward = hardwareMap.opticalDistanceSensor.get("ods2");  // MR ODS sensor. ods2 is pointed forward
        // turn on LED of light sensor.
        lightSensorFloor.enableLed(true);
        lightSensorForward.enableLed(true);

        touch = hardwareMap.touchSensor.get("sensor touch");

        time = new ElapsedTime();
        state = State.drivingFWD1;
    }

    @Override
    public void loop() {
        double currentTime = time.time();

        distanceValue = lightSensorForward.getLightDetected();

        if (distanceValue >= BUTTON_DISTANCE_LOW && distanceValue  <= BUTTON_DISTANCE_HIGH){
            readyToPress = true;
        } else {
            readyToPress = false;
        }

        //For line follower
        correction = (PERFECT_LIGHT_VALUE - lightSensorFloor.getLightDetected());

        //Sets the powers so they are no less than MOTOR_BASE_POWER and apply to correction
        if (correction <= 0) {
            portLinePower = MOTOR_BASE_POWER - correction;
            stbdLinePower = MOTOR_BASE_POWER;
        } else {
            portLinePower = MOTOR_BASE_POWER;
            stbdLinePower = MOTOR_BASE_POWER + correction;
        }

        switch(state) {
            case drivingFWD1:
                telemetry.addData("drivingFWD1", "Start");
//                frank.portMotor.setPower(FORWARD_POWER);
//                frank.stbdMotor.setPower(FORWARD_POWER);
//                if (currentTime > 1.4) {
//                    state = State.resting1;
//                    time.reset();
//                }
                this.fwd(1.4, State.resting1);
                telemetry.addData("drivingFWD1", "End");
                break;
            case drivingFWD2:
                telemetry.addData("drivingFWD2", "Start");
//                frank.portMotor.setPower(FORWARD_POWER);
//                frank.stbdMotor.setPower(FORWARD_POWER);
//                if (currentTime > 0.5) {
//                    state = State.followingLine;
//                    time.reset();
//                }
                this.fwd(0.5, State.followingLine);
                telemetry.addData("drivingFWD2", "End");
                break;
            case drivingFWD3:
                telemetry.addData("drivingFWD3", "Start");
//                frank.portMotor.setPower(FORWARD_POWER);
//                frank.stbdMotor.setPower(FORWARD_POWER);
//                if (currentTime > 0.1 ) {
//                    state = State.drivingREV;
//                    time.reset();
//                }
                this.fwd(0.1, State.drivingREV);
                telemetry.addData("drivingFWD3", "End");
                break;
            case drivingREV:
                telemetry.addData("drivingREV", "Start");
                frank.portMotor.setPower(-FORWARD_POWER);
                frank.stbdMotor.setPower(-FORWARD_POWER);
                if  (currentTime > 0.5) {
                    state = State.done;
                    time.reset();
                }
                telemetry.addData("drivingREV", "End");
                break;
            case turningRight:
                telemetry.addData("turningRight", "Start");
                frank.portMotor.setPower(-TURNING_POWER);
                frank.stbdMotor.setPower(TURNING_POWER);
                if (currentTime > 0.5) {
                    state = State.drivingFWD2;
                    time.reset();
                }
                telemetry.addData("turningRight", "End");
                break;
            case resting1:
                telemetry.addData("resting1", "Start");
                frank.portMotor.setPower(0);
                frank.stbdMotor.setPower(0);
                if (currentTime > 1) {
                    state = State.turningRight;
                    time.reset();
                }
                telemetry.addData("resting1", "End");
                break;
            case resting2:
                telemetry.addData("resting2", "Start");
                frank.portMotor.setPower(0);
                frank.stbdMotor.setPower(0);
                if (currentTime > 0.5) {
                    state = State.drivingFWD3;
                    time.reset();
                    readyToPress = false;
                }
                telemetry.addData("resting2", "End");
                break;
            case followingLine:
                telemetry.addData("followingLine", "Start");
                frank.portMotor.setPower(portLinePower);
                frank.stbdMotor.setPower(stbdLinePower);
                if (readyToPress) {
                    state = State.resting2;
                    time.reset();
                }
                telemetry.addData("followingLine", "End");
                break;
            case done:
                telemetry.addData("done", "done");
                frank.portMotor.setPower(0);
                frank.stbdMotor.setPower(0);
        }

    }

    private void fwd(double timeout, State nextState) {
        frank.portMotor.setPower(FORWARD_POWER);
        frank.stbdMotor.setPower(FORWARD_POWER);
        if (timeout > 1.4) {
            state = nextState;
            time.reset();
        }
    }

}

