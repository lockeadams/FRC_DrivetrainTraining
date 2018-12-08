package robot;

import robot.commands.GearShiftIn;
import robot.commands.GearShiftOut;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

	public XboxController pilot;
	
	public JoystickButton pilot9;
	public JoystickButton pilot10;
	
	public OI() {
		pilot = new XboxController(0);
		pilot9 = new JoystickButton(pilot, 9);
		pilot10 = new JoystickButton(pilot, 10);
		
		pilot9.whenPressed(new GearShiftIn());
		pilot10.whenPressed(new GearShiftOut());
	}
	
	
	
}
