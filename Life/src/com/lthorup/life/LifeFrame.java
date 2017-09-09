package com.lthorup.life;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class LifeFrame extends JFrame {

	private JPanel contentPane;
	private LifeView lifeView;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LifeFrame frame = new LifeFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LifeFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 399, 458);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lifeView = new LifeView();
		lifeView.setBounds(6, 6, 386, 384);
		contentPane.add(lifeView);
		
		JButton btnRestart = new JButton("Restart");
		btnRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((LifeView)lifeView).restart();
			}
		});
		btnRestart.setBounds(16, 401, 117, 29);
		contentPane.add(btnRestart);
		
		JLabel lblPopulation = new JLabel("0");
		lblPopulation.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPopulation.setBounds(321, 406, 60, 16);
		contentPane.add(lblPopulation);
		((LifeView)lifeView).setPopulationLabel(lblPopulation);
		
		JLabel lblPopulation_1 = new JLabel("Population:");
		lblPopulation_1.setBounds(239, 406, 80, 16);
		contentPane.add(lblPopulation_1);
	}
}
