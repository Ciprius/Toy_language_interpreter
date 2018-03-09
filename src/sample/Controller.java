package sample;

import Model.Dictionary.MyDictionary;
import Model.Dictionary.MyIDictionary;
import Model.Expressions.*;
import Model.Filetable.MyFileTable;
import Model.Filetable.MyIFileTable;
import Model.Heap.MyHeap;
import Model.Heap.MyIHeap;
import Model.List.MyIList;
import Model.List.MyList;
import Model.PrgState;
import Model.Stack.MStack;
import Model.Stack.MyStack;
import Model.Statments.*;
import Repository.MyRepository;
import Repository.Repository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;


import javafx.event.ActionEvent;

public class Controller {
    @FXML
    private Button Add;
    @FXML private ListView<String> List;
    @FXML private AnchorPane ancora1;

    private MyRepository repo;
    private PrgState state;

    @FXML public  void initialize()
    {
        ancora1.setStyle("-fx-background-color: #000000");
        List.getItems().addAll("v=2;Print(v)","a=2+3*5;b=a+1;Print(b)","a=2-2 ;(IF(a)THEN(v=2)ELSE(v=4) ; Print(v))","Opening file;Reading from file; Print(g) ; Closing the file ",
        "v=10 ; New(v,20) ; New(a,22) ; WriteHeap(a,30) ; Print(a) ; (Print(HeapRead(a)) ; a=0",
        "v=6 ; While(v-4>0){(Print(v) ; v=v-1)} ; Print(v),",
                "v=10 ; New(a,22) ; fork(WriteHeap(a,30) ; v=32 ; Print(v) ; Print(HeapRead(a))); Print(v) ; Print(HeapRead(a))");
        List.setStyle("-fx-background-color: #D2691E");
    }

    @FXML
    public void progOne(ActionEvent event) throws IOException
    {
        MyStack<IStmt> stk = new MStack<>();
        MyIDictionary<String, Integer> dict = new MyDictionary<>();
        MyIList<Integer> list = new MyList<>();
        MyIFileTable<Tuple> tpl = new MyFileTable<>();
        MyIHeap<Integer> heap = new MyHeap<>();

        if (Objects.equals(List.getSelectionModel().getSelectedIndices().toString(), "[0]")) {
        Exp exp2 = new ConstExp(2);
        IStmt st1 = new CompStmt(new AssignStmt("v", exp2), new PrintStmt(new VarExp("v")));
        state = new PrgState(stk, dict, list, tpl, heap, st1, 1);
        try {
            repo = new Repository(state, "D:\\Faculta\\Map\\LastLab\\src\\fileState1.txt");
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
        else if (Objects.equals(List.getSelectionModel().getSelectedIndices().toString(), "[1]"))
        {
            IStmt st2 = new CompStmt(new AssignStmt("a", new ArithExp
                    (1,new ConstExp(2),new ArithExp(3,new ConstExp(3),new ConstExp(5)))),
                    new CompStmt(new AssignStmt("b",new ArithExp(1,new VarExp("a"),new ConstExp(1))),
                            new PrintStmt(new VarExp("b"))));
            state = new PrgState(stk, dict, list, tpl, heap, st2, 1);
            try {
                repo = new Repository(state, "D:\\Faculta\\Map\\LastLab\\src\\fileState2.txt");
            } catch (IOException e) {
                System.out.println(e.toString());
            }
        }
        else if (Objects.equals(List.getSelectionModel().getSelectedIndices().toString(), "[2]"))
        {
            IStmt st2 =new CompStmt(new AssignStmt("a",new ArithExp(2,new ConstExp(2),
                    new ConstExp(2))),new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v",new ConstExp(2)),
                    new AssignStmt("v",new ConstExp(4))),new PrintStmt(new VarExp("v"))));
            state = new PrgState(stk, dict, list, tpl, heap, st2, 1);
            try {
                repo = new Repository(state, "D:\\Faculta\\Map\\LastLab\\src\\fileState3.txt");
            } catch (IOException e) {
                System.out.println(e.toString());
            }
        }
        else if (Objects.equals(List.getSelectionModel().getSelectedIndices().toString(), "[3]"))
        {
            IStmt st2 = new CompStmt(new openRFileStmt("f","D:\\Faculta\\lab2-7\\src\\file1"),
                    new CompStmt(new readFileStmt(new VarExp("f"),"g"),
                            new CompStmt(new PrintStmt(new VarExp("g")),new closeFileStmt(new VarExp("f")))));
            state = new PrgState(stk, dict, list, tpl, heap, st2, 1);
            try {
                repo = new Repository(state, "D:\\Faculta\\Map\\LastLab\\src\\fileState4.txt");
            } catch (IOException e) {
                System.out.println(e.toString());
            }
        }
        else if (Objects.equals(List.getSelectionModel().getSelectedIndices().toString(), "[4]"))
        {
            IStmt st2 = new CompStmt(new AssignStmt("v",new ConstExp(10)),
                    new CompStmt(new NewStmt("v",new ConstExp(20)),
                            new CompStmt(new NewStmt("a",new ConstExp(22)),
                                    new CompStmt(new HeapWritingStmt("a",new ConstExp(30)),
                                            new CompStmt(new PrintStmt(new VarExp("a")),
                                                    new CompStmt(new PrintStmt(new HeadReadExp("a")),new AssignStmt("a",new ConstExp(0))))))));
            state = new PrgState(stk, dict, list, tpl, heap, st2, 1);
            try {
                repo = new Repository(state, "D:\\Faculta\\Map\\LastLab\\src\\fileState5.txt");
            } catch (IOException e) {
                System.out.println(e.toString());
            }
        }
        else if (Objects.equals(List.getSelectionModel().getSelectedIndices().toString(), "[5]"))
        {
            IStmt st2 = new CompStmt(new AssignStmt("v",new ConstExp(6)),
                    new CompStmt(new WhileStmt(new BooleanExp(new ArithExp(2,new VarExp("v"),new ConstExp(4)),new ConstExp(0),5),
                            new CompStmt(new PrintStmt(new VarExp("v")),
                                    new AssignStmt("v",new ArithExp(2,new VarExp("v"),new ConstExp(1))))),
                            new PrintStmt(new VarExp("v"))));
            state = new PrgState(stk, dict, list, tpl, heap, st2, 1);
            try {
                repo = new Repository(state, "D:\\Faculta\\Map\\LastLab\\src\\fileState6.txt");
            } catch (IOException e) {
                System.out.println(e.toString());
            }
        }
        else if (Objects.equals(List.getSelectionModel().getSelectedIndices().toString(), "[6]"))
        {
            IStmt st2 = new CompStmt(new AssignStmt("v",new ConstExp(10)),
                    new CompStmt(new NewStmt("a",new ConstExp(22)),
                            new CompStmt(new ForkStmt(new CompStmt(new HeapWritingStmt("a",new ConstExp(30)),
                                    new CompStmt(new AssignStmt("v",new ConstExp(32)),
                                            new CompStmt(new PrintStmt(new VarExp("v")),new PrintStmt(new HeadReadExp("a")))))),
                                    new CompStmt(new PrintStmt(new VarExp("v")),new PrintStmt(new HeadReadExp("a"))))));
            state = new PrgState(stk, dict, list, tpl, heap, st2, 1);
            try {
                repo = new Repository(state, "D:\\Faculta\\Map\\LastLab\\src\\fileState7.txt");
            } catch (IOException e) {
                System.out.println(e.toString());
            }
        }

        Stage stage;
        Parent root;
        stage=(Stage) Add.getScene().getWindow();
        //load up OTHER FXML document
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("mainpro.fxml"));
        root = loader.load();
        ControllerProg contprog=loader.getController();
        contprog.init(repo);
        Scene scene = new Scene(root, 620, 400);
        stage.setTitle("Program "+List.getSelectionModel().getSelectedIndices().toString());
        stage.setScene(scene);
        stage.show();
    }

}
