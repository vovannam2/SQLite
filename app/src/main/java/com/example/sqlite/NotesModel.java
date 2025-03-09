package com.example.sqlite;

import java.io.Serializable;

public class NotesModel implements Serializable {
    private int IdNode ;
    private String NameNote;

    public NotesModel(String nameNote, int idNode) {
        NameNote = nameNote;
        IdNode = idNode;
    }
    public int getIdNode(){
        return IdNode;
    }

    public void setIdNode(int idNode) {
        IdNode = idNode;
    }

    public String getNameNote() {
        return NameNote;
    }

    public void setNameNodte(String nameNote) {
        NameNote = nameNote;
    }
}
