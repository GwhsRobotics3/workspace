package org.usfirst.frc.team5507.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	RobotDrive myRobot = new RobotDrive(0, 1);
	Joystick stick = new Joystick(0);
	Timer timer = new Timer();
	Compressor c = new Compressor(0);
	DoubleSolenoid exampleDouble = new DoubleSolenoid(0, 1);
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		exampleDouble.set(DoubleSolenoid.Value.kOff);
		
	}

	/**
	 * This function is run once each time the robot enters autonomous mode
	 */
	@Override
	public void autonomousInit() {
		timer.reset();
		timer.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		// Drive for 2 seconds
		if (timer.get() < 2.0) {
			myRobot.drive(-0.5, 0.0); // drive forwards half speed
		} else {
			myRobot.drive(0.0, 0.0); // stop robot
		}
	}

	/**
	 * This function is called once each time the robot enters tele-operated
	 * mode
	 */
	@Override
	public void teleopInit() { 
		c.setClosedLoopControl(true);
		
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		myRobot.arcadeDrive(stick);
		if(stick.getRawButton(5)){
			exampleDouble.set(DoubleSolenoid.Value.kForward);
		}
		else if (stick.getRawButton(6)){
			exampleDouble.set(DoubleSolenoid.Value.kReverse);
		}
		else{
			exampleDouble.set(DoubleSolenoid.Value.kOff);
		}
		
		 System.out.println("cenable");
		 System.out.println(c.enabled());
		 
		 System.out.println("Pressures Switch Value");
		 System.out.println(c.getPressureSwitchValue());
		 
		 System.out.println("Compressor Current");
		 System.out.println(c.getCompressorCurrent());
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
