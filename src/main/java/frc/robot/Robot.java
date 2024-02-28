// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;

import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

/** This is a demo program showing how to use Mecanum control with the MecanumDrive class. */
public class Robot extends TimedRobot {
  private static final int kFrontLeftChannel = 22;
  private static final int kRearLeftChannel = 21;
  private static final int kFrontRightChannel = 1;
  private static final int kRearRightChannel = 3;

  private static final int kJoystickChannel = 1;

  private MecanumDrive m_robotDrive;
  //private Joystick m_stick;
  private XboxController m_controller;

  @Override
  public void robotInit() {
    CANSparkMax frontLeft = new CANSparkMax(kFrontLeftChannel, MotorType.kBrushless);
    CANSparkMax rearLeft = new CANSparkMax(kRearLeftChannel, MotorType.kBrushless);
    CANSparkMax frontRight = new CANSparkMax(kFrontRightChannel, MotorType.kBrushless);
    CANSparkMax rearRight = new CANSparkMax(kRearRightChannel, MotorType.kBrushless);

    SendableRegistry.addChild(m_robotDrive, frontLeft);
    SendableRegistry.addChild(m_robotDrive, rearLeft);
    SendableRegistry.addChild(m_robotDrive, frontRight);
    SendableRegistry.addChild(m_robotDrive, rearRight);

    // Invert the right side motors.
    // You may need to change or remove this to match your robot.
    frontRight.setInverted(true);
    rearRight.setInverted(true);

    m_robotDrive = new MecanumDrive(frontLeft::set, rearLeft::set, frontRight::set, rearRight::set);

    //m_stick = new Joystick(kJoystickChannel);
    m_controller = new XboxController(kJoystickChannel);

  }

  @Override
  public void teleopPeriodic() {
    // Use the joystick Y axis for forward movement, X axis for lateral
    // movement, and Z axis for rotation.
    //m_robotDrive.driveCartesian(-m_stick.getY() * 0.2, -m_stick.getX() * 0.2, -m_stick.getZ() * 0.2);
    m_robotDrive.driveCartesian(-m_controller.getLeftY() * 0.05, m_controller.getLeftX() * 0.05, m_controller.getRightX() * 0.05);
  }
}
