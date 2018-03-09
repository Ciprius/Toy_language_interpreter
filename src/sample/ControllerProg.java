package sample;

import Model.FileTable;
import Model.HeapTable;
import Model.PrgState;
import Model.Statments.Tuple;
import Model.SymTable;
import Repository.MyRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.*;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class ControllerProg
{
    private MyRepository repository;
    private ExecutorService executor= Executors.newFixedThreadPool(2);

    @FXML private ListView<String> Stack;
    @FXML private ListView<String> outlist;
    @FXML private TableView<HeapTable> heap;
    @FXML private TableColumn<HeapTable,Integer> Addr;
    @FXML private TableColumn<HeapTable,Integer> ValueH;
    @FXML private TableView<FileTable> filetable;
    @FXML private TableColumn<FileTable,Integer> Identifier;
    @FXML private TableColumn<FileTable,String> FileName;
    @FXML private TableView<SymTable> symtable;
    @FXML private TableColumn<SymTable,String> VarN;
    @FXML private TableColumn<SymTable,Integer> ValueS;
    @FXML private Button change,shoB,OneStep;
    @FXML private AnchorPane ancora;
    @FXML private TextField TStack,TSym,TOut,THeap,TFile,progID;


    public void init(MyRepository repo)
    {
        ancora.setStyle("-fx-background-color: #11033E ; -fx-border-color: #000000");
        TStack.setStyle("-fx-background-color: #D2691E ; -fx-border-color: #000000");
        TSym.setStyle("-fx-background-color: #D2691E ; -fx-border-color: #000000");
        TOut.setStyle("-fx-background-color: #D2691E ; -fx-border-color: #000000");
        THeap.setStyle("-fx-background-color: #D2691E ; -fx-border-color: #000000");
        TFile.setStyle("-fx-background-color: #D2691E ; -fx-border-color: #000000");
        change.setStyle("-fx-background-color: #FFD700 ; -fx-border-color: #000000");
        shoB.setStyle("-fx-background-color: #FFD700 ; -fx-border-color: #000000");
        OneStep.setStyle("-fx-background-color: #FFD700 ; -fx-border-color: #000000");
        progID.setStyle("-fx-border-color: #000000");
        Stack.setStyle("-fx-border-color: #FFD700");
        outlist.setStyle("-fx-border-color: #FFD700");
        heap.setStyle("-fx-border-color: #FFD700");
        symtable.setStyle("-fx-border-color: #FFD700");
        filetable.setStyle("-fx-border-color: #FFD700");
        Addr.setStyle("-fx-border-color: #000000");
        ValueH.setStyle("-fx-border-color: #000000");
        Identifier.setStyle("-fx-border-color: #000000");
        FileName.setStyle("-fx-border-color: #000000");
        VarN.setStyle("-fx-border-color: #000000");
        ValueS.setStyle("-fx-border-color: #000000");
        repository=repo;
        shoB.setDisable(true);
        Stack.getItems().add(repository.getPrgList().get(0).getStk().toString());
        progID.setText(repository.getPrgList().get(0).getIDString());
    }

    private List<PrgState> removeCompletedPrg(List<PrgState> prog)
    {
        return prog.stream().filter(PrgState::isNotCompleted).collect(Collectors.toList());
    }

    private Map<Integer,Integer> conservativeGarbageCollector(Collection<Integer> dictValues, Map<Integer,Integer> heap)
    {
        return heap.entrySet().stream().filter(e->dictValues.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
    }

    private void OneStepForAllProg(List<PrgState> prog)
    {
        //prog.forEach(p->repository.LogProgState(p));
        List<Callable<PrgState>> callList=prog.stream().map((PrgState p) -> (Callable<PrgState>)(p::oneStep))
                .collect(Collectors.toList());

        List<PrgState> newPrgList=null;
        try {
            newPrgList = executor.invokeAll(callList). stream()
                    . map(future -> {
                        try {
                            return future.get();
                        } catch (InterruptedException | ExecutionException e) { return null;}
                    }).filter(p -> p!=null).collect(Collectors.toList());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        prog.addAll(newPrgList);
        prog.forEach(p->repository.LogProgState(p));
        prog.forEach(p->{progID.setText(p.getIDString());
                         Stack.getItems().clear();
                         Stack.getItems().add(p.getStk().toString());

                         outlist.getItems().clear();
                         outlist.getItems().add(p.getList().toString());

                         symtable.getItems().clear();
                         VarN.setCellValueFactory(new PropertyValueFactory<>("varN"));
                         ValueS.setCellValueFactory(new PropertyValueFactory<>("valS"));
                         ObservableList<SymTable> lista1= FXCollections.observableArrayList();
                         for(String j:p.getDict().getDict().keySet())
                         {
                             SymTable dict=new SymTable(j,p.getDict().getDict().get(j));
                             lista1.add(dict);
                         }
                         symtable.setItems(lista1);

                         filetable.getItems().clear();
                         Identifier.setCellValueFactory(new PropertyValueFactory<>("ide"));
                         FileName.setCellValueFactory(new PropertyValueFactory<>("filename"));
                         ObservableList<FileTable> lista2= FXCollections.observableArrayList();
                         for (int i:p.getFileT().getFileT().keySet())
                         {
                             Tuple tpl=p.getFileT().get(i);
                             FileTable file=new FileTable(i,tpl.getName());
                             lista2.add(file);
                         }
                         filetable.setItems(lista2);

                         heap.getItems().clear();
                         Addr.setCellValueFactory(new  PropertyValueFactory<>("ID"));
                         ValueH.setCellValueFactory(new PropertyValueFactory<>("Value"));
                         ObservableList<HeapTable> lista3= FXCollections.observableArrayList();
                         for(int i:p.getHeap().getHeapmap().keySet())
                         {
                             HeapTable hpt=new HeapTable(i,p.getHeap().get(i));
                             lista3.add(hpt);
                         }
                         heap.setItems(lista3);

                        });
        repository.setPrgList(prog);
    }

    @FXML
    public void allSteps()
    {
        List<PrgState> list=repository.getPrgList();
        list=removeCompletedPrg(list);
        if (list.size()!= 0)
        {
            OneStepForAllProg(list);
            list=removeCompletedPrg(list);
        }
        else {
            executor.shutdown();
            repository.CloseFile();
            repository.getPrgList().forEach((PrgState p) -> p.getFileT().getFileT().values().
                    stream().map(Tuple::getBf).forEach(h -> {
                try {
                    h.close();
                } catch (IOException e) {
                    e.getStackTrace();
                }
            }));
            repository.setPrgList(list);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Ooops, the program just finished!");
            alert.showAndWait();
            shoB.setDisable(false);
        }

    }

    @FXML
    public void Change() throws IOException {
        repository.SpecialClose();
        Stage stage;
        stage=(Stage) change.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("progs.fxml"));
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Program select");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void Show() throws IOException {
        java.awt.Desktop.getDesktop().edit(new File(repository.GetFileName()));
    }
}
