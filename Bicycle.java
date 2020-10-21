package bicyleCompany;

public class Bicycle {

    private String type;
    private int wheelSize;
    private boolean assembled;
    private char gender;

    Bicycle() {
        this.type = "";
        this.wheelSize = 0;
        this.assembled = false;
        this.gender = '\0';
    }


    public Bicycle(String type, int wheelSize, boolean assembled, char gender) {
        this.type = type;
        this.wheelSize = wheelSize;
        this.assembled = assembled;
        this.gender = gender;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWheelSize() {
        return wheelSize;
    }

    public void setWheelSize(int wheelSize) {
        this.wheelSize = wheelSize;
    }

    public boolean getAssembled() {
        return assembled;
    }

    public void setAssembled(boolean assembled) {
        this.assembled = assembled;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }


    @Override
    public String toString() {
        String _gender;

        if (this.gender == 'm') {
            _gender = "Male";
        } else if (this.gender == 'f') {
            _gender = "Female";
        } else {
            _gender = "inValid";
        }

        return "Bicycle [ type=" + type + ", wheelSize=" + wheelSize + ", assembled=" + assembled + ", gender=" + _gender + " ]";
    }
}
