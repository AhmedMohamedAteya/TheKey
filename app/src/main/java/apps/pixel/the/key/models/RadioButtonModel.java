package apps.pixel.the.key.models;

public class RadioButtonModel {

    private boolean isChecked  ;
    private int position ;

    public RadioButtonModel(boolean isChecked, int position) {
        this.isChecked = isChecked;
        this.position = position;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
