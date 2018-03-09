package Model.Statments;

import java.io.BufferedReader;

public class Tuple
{
    private String name;
    private BufferedReader bf;

    public Tuple(String name,BufferedReader bf)
    {
        this.bf=bf;
        this.name=name;
    }

    public String getName()
    {
        return this.name;
    }
    public BufferedReader getBf(){return this.bf;}

}
