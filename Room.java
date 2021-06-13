import java.io.Serializable;

public class Room implements Serializable {
    private static final long serialVersionUID = 1L;




    private String name;



    private int length;
    private int width;
    private int numOfWindows;


    public Room(String name, int length, int width, int numOfWindows) {
        this.name = name;
        this.length = length;
        this.width = width;
        this.numOfWindows = numOfWindows;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getNumOfWindows() {
        return numOfWindows;
    }

    public void setNumOfWindows(int numOfWindows) {
        this.numOfWindows = numOfWindows;
    }

    public int getArea() {
        return length * width;
    }

}