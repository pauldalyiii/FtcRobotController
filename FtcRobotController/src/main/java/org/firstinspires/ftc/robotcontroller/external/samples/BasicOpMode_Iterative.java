package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Basic: Iterative OpMode", group="Iterative OpMode")
@Disabled
public class BasicOpMode_Iterative extends OpMode implements RobotOpMode {

    private static final double POWER_MIN = -1.0;
    private static final double POWER_MAX = 1.0;

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;

    @Override
    public void init() {
        initialize();
    }

    @Override
    public void init_loop() {}

    @Override
    public void start() {
        runtime.reset();
        start();
    }

    @Override
    public void loop() {
        execute();
    }

    @Override
    public void stop() {
        stop();
    }

    @Override
    public void initialize() {
        telemetry.addData("Status", "Initialized");

        leftDrive = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");

        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);

        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void execute() {
        double drive = -gamepad1.left_stick_y;
        double turn = gamepad1.right_stick_x;
        double leftPower = Range.clip(drive + turn, POWER_MIN, POWER_MAX);
        double rightPower = Range.clip(drive - turn, POWER_MIN, POWER_MAX);

        leftDrive.setPower(leftPower);
        rightDrive.setPower(rightPower);

        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
    }
}
