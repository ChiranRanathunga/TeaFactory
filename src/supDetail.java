import javafx.beans.property.*;

public class supDetail {
    private final IntegerProperty TS_id;
    private final StringProperty TS_name;
    private final StringProperty TS_Add;

    public supDetail(Integer TS_id, String TS_name, String TS_Add) {
        this.TS_id = new SimpleIntegerProperty(TS_id);
        this.TS_name = new SimpleStringProperty(TS_name);
        this.TS_Add = new SimpleStringProperty(TS_Add);
    }

    public int getTS_id() {
        return TS_id.get();
    }

    public IntegerProperty TS_idProperty() {
        return TS_id;
    }

    public void setTS_id(int TS_id) {
        this.TS_id.set(TS_id);
    }

    public String getTS_name() {
        return TS_name.get();
    }

    public StringProperty TS_nameProperty() {
        return TS_name;
    }

    public void setTS_name(String TS_name) {
        this.TS_name.set(TS_name);
    }

    public String getTS_Add() {
        return TS_Add.get();
    }

    public StringProperty TS_AddProperty() {
        return TS_Add;
    }

    public void setTS_Add(String TS_Add) {
        this.TS_Add.set(TS_Add);
    }
}
