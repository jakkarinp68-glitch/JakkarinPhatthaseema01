package com.example.jakkarinphatthaseema;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;

public class NoteMapper {
    static Gson gson = new Gson();

    public static NoteEntity toEntity(Note note) {
        NoteEntity entity = null;
        if (note instanceof TextNote textNote) {
            entity = new NoteEntity(note.title, "text", null, textNote.getSummary(), note.createdDate);
        } else if (note instanceof CheckListNote checklistNote) {
            String jsonItems = gson.toJson(checklistNote.item);
            entity = new NoteEntity(note.title, "checklist", jsonItems, null, note.createdDate);
        }

        if (entity != null && note.getUser() != null) {
            entity.userName = note.getUser().name;
            entity.userSex = note.getUser().sex;
            entity.userAge = note.getUser().age;
        }
        return entity;
    }

    public static Note fromEntity(NoteEntity entity) {
        Note note = null;
        if (entity.type.equals("text")) {
            note = new TextNote(entity.title, entity.createdDate, entity.content);
        } else if (entity.type.equals("checklist")) {
            CheckListNote checklist = new CheckListNote();
            checklist.title = entity.title;
            checklist.createdDate = entity.createdDate;
            checklist.item = gson.fromJson(entity.checklistItemsJson, new TypeToken<List<String>>(){}.getType());
            note = checklist;
        }

        if (note != null && entity.userName != null) {
            User user = new GeneralUser(entity.userName, entity.userSex, entity.userAge);
            note.setUser(user);
        }
        return note;
    }
}
