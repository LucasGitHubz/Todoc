package com.cleanup.todoc.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * <p>Models for project in which tasks are included.</p>
 *
 * @author Gaëtan HERFRAY
 */
@Entity
public class Project {
    @PrimaryKey
    private final long id;

    @NonNull
    private final String name;

    @ColorInt
    private final int color;

    public Project(long id, @NonNull String name, int color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    @NonNull
    /*public static Project[] getAllProjects() {
        return new Project[]{
                new Project(1L, "Projet Tartampion", 0xFFEADAD1),
                new Project(2L, "Projet Lucidia", 0xFFB4CDBA),
                new Project(3L, "Projet Circus", 0xFFA3CED2),
        };
    }*/
    /*public static Project getProjectById(long id) {
        for (Project project : getAllProjects()) {
            if (project.id == id)
                return project;
        }
        return null;
    }*/

    // --- GETTER ---
    public long getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }

    @Override
    public String toString() {
        return name;
    }
}
