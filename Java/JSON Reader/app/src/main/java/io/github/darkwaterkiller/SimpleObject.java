package io.github.darkwaterkiller;

public class SimpleObject {

    private int number;
    private String name;
    private String info;
    private int[] numList;

    public SimpleObject(int number, String name, String info, int[] numList) {
        this.number = number;
        this.name = name;
        this.info = info;
        this.numList = numList;
    }

    public void setNumber(int number){this.number = number;}
    public void setName(String name){this.name = name;}
    public void setInfo(String info){this.info = info;}
    public void setNumList(int[] numList){this.numList = numList;}

    public int getNumber(){return this.number;}
    public String getName(){return this.name;}
    public String getInfo(){return this.info;}
    public int[] getNumList(){return this.numList;}

    @Override
    public String toString() {
        String objStr = name + ":\n\t" + info + "\n\tCount: " + number + "\n\tids:";
        for(int num : numList)
            objStr += "\n\t\t" + num;
        return objStr;
    }
}