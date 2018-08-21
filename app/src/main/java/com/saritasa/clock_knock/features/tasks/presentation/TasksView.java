package com.saritasa.clock_knock.features.tasks.presentation;

import com.saritasa.clock_knock.base.presentation.BaseView;

import java.util.List;

public interface TasksView extends BaseView{

    void showTasks();

    void updateView(List<TasksObject> aTasksDomains);
}
