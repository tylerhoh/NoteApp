package DTO;
import java.util.*;

public class NoteObject {
    public final String data;
    public final String _id;
    public final Date date;

    public NoteObject(String data, String _id, Date date){
        this.data = data;
        this._id = _id;
        this.date = date;
    }
}
