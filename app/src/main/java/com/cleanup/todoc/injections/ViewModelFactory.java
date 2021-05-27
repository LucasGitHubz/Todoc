package com.cleanup.todoc.injections;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;
import com.cleanup.todoc.ui.TaskViewModel;

import java.util.concurrent.Executor;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final TaskDataRepository taskDataSource;
    private final ProjectDataRepository projectDataSource;
    private final Executor executor;
    private final LifecycleOwner owner;

    public ViewModelFactory(TaskDataRepository taskDataSource, ProjectDataRepository projectDataSource, Executor executor, LifecycleOwner owner) {
            this.taskDataSource = taskDataSource;
            this.projectDataSource = projectDataSource;
            this.executor = executor;
            this.owner = owner;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
            if (modelClass.isAssignableFrom(TaskViewModel.class)) {
                return (T) new TaskViewModel(taskDataSource, projectDataSource, executor, owner);
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
