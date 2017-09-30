package com.nis.task.core.init;

import com.nis.comm.utils.AppContextUtil;
import com.nis.task.core.exec.TaskManager;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class TaskInit extends HttpServlet {
	private static final long serialVersionUID = -2274726206362496315L;

	public void init(ServletConfig config) throws ServletException {
		TaskManager taskManager = (TaskManager) AppContextUtil.getInstance().getBean(TaskManager.class);
		taskManager.init();
	}
}