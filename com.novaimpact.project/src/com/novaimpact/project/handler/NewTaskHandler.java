 
package com.novaimpact.project.handler;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.descriptor.basic.MBasicFactory;
import org.eclipse.e4.ui.model.application.descriptor.basic.MPartDescriptor;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;

import com.novaimpact.project.model.Task;
import com.novaimpact.project.part.TaskPart;
import com.novaimpact.project.service.task.TaskService;

public class NewTaskHandler {
	
	@Inject
	private TaskService taskService;
	
	@Execute
	public void execute(EPartService partService, EModelService modelService) {
		Task task = this.taskService.createTask();
		
		task.setName("new-task-name");
		
		MPartDescriptor partDescriptor = MBasicFactory.INSTANCE.createPartDescriptor();
		partDescriptor.setContributionURI("bundleclass://com.novaimpact.project/com.novaimpact.project.part.TaskPart");
		MPart part = modelService.createPart(partDescriptor);
		part.setLabel("New Task");
		part.setCloseable(true);
		
		partService.showPart(part, PartState.ACTIVATE);
		
		TaskPart taskPart = (TaskPart) part.getObject();
		taskPart.setTask(task);		
	}
		
}