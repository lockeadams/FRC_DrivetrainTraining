package robot.commands.tuning;

import java.io.FileWriter;
import java.io.IOException;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.opencsv.CSVWriter;
import edu.wpi.first.wpilibj.command.Command;
import robot.RobotMap;

public class RecordVelocity extends Command {

    WPI_TalonSRX left = RobotMap.left;
    WPI_TalonSRX right = RobotMap.right;
    CSVWriter writer;
    double voltage;

    public RecordVelocity() {
    }

    @Override
    protected void initialize() {
        voltage = 0;
        try {
            writer = new CSVWriter(new FileWriter("velocityData.csv"));
            String[] header = {"Voltage", "Velocity"}; 
            writer.writeNext(header); 
        } catch (IOException e) {
			e.printStackTrace();
        }
    }

    @Override
    protected void execute() {
        left.set(voltage);
        right.set(voltage);

        voltage = voltage + (0.25 / 50); // ramp voltage at 0.25 V/s

        double actualVoltage = left.getMotorOutputVoltage();
        double velocity = (left.getSelectedSensorVelocity(0) / RobotMap.TICKS_PER_REV) * RobotMap.WHEEL_DIAMETER * Math.PI;

        String[] data = {Double.toString(actualVoltage), Double.toString(velocity)};
        writer.writeNext(data);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void interrupted() {
    }
}