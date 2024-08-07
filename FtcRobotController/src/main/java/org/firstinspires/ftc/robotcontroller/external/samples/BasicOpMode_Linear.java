package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Basic: Linear OpMode", group="Linear OpMode")
@Disabled
public class BasicOpMode_Linear extends LinearOpMode implements RobotOpMode {

    private static final double POWER_MIN = -1.0;
    private static final double POWER_MAX = 1.0;
    
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;

    @Override
    public void runOpMode() {
        initialize();
        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {
            execute();
        }
    }

    @Override
    public void initialize() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        leftDrive = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");

        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);
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
        telemetry.update();
    }
}
