package org.berendeev.roma.simplerealm.domain.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Note extends RealmObject {
    public static final String NAME = "name";
    @PrimaryKey
    private String name;

    public static final String FIELD = "field";
    @Required
    private String field;

    public Note() {
    }

    public Note(String name, String field) {
        this.name = name;
        this.field = field;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Note)) return false;

        Note note = (Note) o;

        if (!name.equals(note.name)) return false;
        return field.equals(note.field);

    }

    @Override public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + field.hashCode();
        return result;
    }
}
