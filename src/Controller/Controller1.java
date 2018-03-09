package Controller;

import Model.PrgState;
import Model.Statments.Tuple;
import Repository.MyRepository;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller1
{
    private MyRepository repo;
    private int flag;


    public Controller1(MyRepository repo,int flag)
    {
        this.repo=repo;
        this.flag=flag;
    }



    /*public void allSteps()
    {
        //executor= Executors.newFixedThreadPool(2);
        List<PrgState> list=repo.getPrgList();
        list=removeCompletedPrg(list);
        //List<MyIFileTable<Tuple>> tmlist=list.stream().map(PrgState::getFileT).collect(Collectors.toList());

        while(list.size()!= 0)
        {
            OneStepForAllProg(list);
            list=removeCompletedPrg(list);

        }

        //tmlist.forEach(p->p.getFileT().values().
        //stream().map(Tuple::getBf).forEach(t->{try{t.close();}catch (IOException e){e.getStackTrace();}}));
    }*/
}
