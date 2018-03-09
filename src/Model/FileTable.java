package Model;

public class FileTable
{
    private int ide;
    private String filename;

    public FileTable(int id, String filename)
    {
        this.ide=id;
        this.filename=filename;
    }
    public int getIde() { return ide; }
    public String getFilename() { return filename; }

}
