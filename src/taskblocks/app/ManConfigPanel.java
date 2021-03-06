/*
 * Copyright (C) Jakub Neubauer, 2007
 *
 * This file is part of TaskBlocks
 *
 * TaskBlocks is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * TaskBlocks is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package taskblocks.app;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;

import taskblocks.modelimpl.ManImpl;
import taskblocks.modelimpl.TaskModelImpl;


public class ManConfigPanel extends JPanel {

	JTextField nameTF;
	JSpinner _workloadSpin;
	ManImpl _man;
	TaskModelImpl _model;

	public ManConfigPanel(ManImpl man, TaskModelImpl model) {
		_man = man;
		_model = model;
		buildGui();
	}
	
	private void buildGui() {
		// create components
		JPanel contentP = this;
		JLabel nameL = new JLabel("Name:");
		JLabel workloadL = new JLabel("Workload (%):");
		nameTF = new JTextField(15);
		_workloadSpin=new JSpinner(new SpinnerNumberModel(100, 10, 100, 10));
		
		//layout components
		contentP.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		// add labels
		gc.gridx = 0; gc.gridy = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.insets.bottom = 5;
		gc.anchor = GridBagConstraints.EAST;
		//
		contentP.add(nameL, gc);
		gc.gridy++;
		contentP.add(workloadL, gc);
		
		// add edit fields
		gc.gridx++; gc.gridy=0;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.weightx = 1;
		gc.insets.left = 8;
		//
		contentP.add(nameTF, gc);
		gc.gridy++;
		contentP.add(_workloadSpin, gc);
		
		// set component properties
		nameTF.setText(_man.getName());
		_workloadSpin.setValue((int)(_man.getWorkload()*100.0));
	}


}
