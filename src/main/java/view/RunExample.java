package view;

import Exceptions.EmptyTableException;
import Exceptions.MyException;
import ctrl.Controller;

import java.io.IOException;

public class RunExample extends Command{

    private Controller ctrl;
    public RunExample(String k, String d, Controller ctr) {
        super(k, d);
        ctrl = ctr;
    }

    @Override
    public void execute() {
        try{
            ctrl.allSteps();
        }catch (MyException | IOException e){

        } catch (EmptyTableException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Controller getCtrl(){return this.ctrl;}

    public String toString(){
        return this.getKey() + " " + this.getDescription();
    }
}
