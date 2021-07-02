package com.cleanup.todoc.ui;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class TaskViewModel extends ViewModel implements Observer<List<Task>> {
    // REPOSITORIES
    private final TaskDataRepository taskDataSource;
    private final ProjectDataRepository projectDataSource;
    private final Executor executor;
    private OnUpdateTasksListener owner;
    private List<Task> tasks = new ArrayList<>();
    private List<Project> projects = new ArrayList<>();

    public TaskViewModel(TaskDataRepository taskDataSource, ProjectDataRepository projectDataSource, Executor executor, OnUpdateTasksListener owner) {
        this.taskDataSource = taskDataSource;
        this.projectDataSource = projectDataSource;
        this.executor = executor;
        this.owner = owner;
        this.getTasksLiveData().observe(owner, this);
        this.getProjectsLiveData().observe(owner, this::onChangedProject);
    }

    private LiveData<List<Project>> getProjectsLiveData() { return projectDataSource.getProjects(); }
    public List<Project> getProjects() { return this.projects; }

    // -------------
    // FOR TASK
    // -------------

    public LiveData<List<Task>> getTasksLiveData() {
        return taskDataSource.getTasks();
    }
    public List<Task> getTasks() { return tasks; }

    public void createTask(Task task) {
        executor.execute(() -> {
            taskDataSource.createTask(task);
        });
    }

    public void deleteTask(long taskId) {
        executor.execute(() -> {
            taskDataSource.deleteTask(taskId);
        });
    }

    @Override
    public void onChanged(@Nullable List<Task> tasks) {
        this.tasks = tasks;
        owner.updateTasks();
    }

    public void onChangedProject(@Nullable List<Project> projects) {
        this.projects = projects;
    }
}
