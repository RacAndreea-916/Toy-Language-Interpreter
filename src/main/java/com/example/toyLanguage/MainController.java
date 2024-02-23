package com.example.toyLanguage;



import Exceptions.EmptyTableException;
import Exceptions.MyException;
import Model.HeapEntry;
import Model.SymTableEntry;
import Model.MyIDictionary;
import Model.MyIList;
import Model.MyIStack;
import State.Heap;
import State.PrgState;
import Statement.IStmt;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.StringConverter;
import value.StringValue;
import value.Value;
import view.RunExample;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class MainController {
    @FXML
    private SelectController controller;
    private RunExample selectedExample;
    private PrgState selectedProgram;


    @FXML
    private TableView<HeapEntry>heapTableView;
    @FXML
    private TableColumn<HeapEntry,String> heapAddressColumn;
    @FXML
    private TableColumn<HeapEntry,String> heapValueColumn;

    @FXML
    private ListView<StringValue>fileTableListView;

    @FXML
    private TableView<SymTableEntry>symTableView;
    @FXML
    private TableColumn<SymTableEntry,String> symTableVarColumn;
    @FXML
    private TableColumn<SymTableEntry,String>symTableValueColumn;

    @FXML

    private ListView<PrgState>programStateListView;
    @FXML


    private ListView<IStmt>executionStackListView;
    @FXML

    private ListView<Value>outputListView;
    @FXML
    private TextField numberOfProgramStateField;

    @FXML
    private Button executeOneStepButton;

    public void setController(SelectController controller){
        this.controller=controller;
        //this.prgStatesIdentifiers.getSelectionModel().selectedItemProperty().addListener((a,b,ex)->this.showDataForSelectedExample(ex));

        this.controller.getProgramListView().getSelectionModel().selectedItemProperty().addListener((a,b,ex)->this.showDataForSelectedExample(ex));
    }


    public void initialize(){
        this.numberOfProgramStateField.setEditable(false);

        this.heapAddressColumn.setCellValueFactory(new PropertyValueFactory<HeapEntry,String>("heapAddress"));
        this.heapValueColumn.setCellValueFactory(new PropertyValueFactory<HeapEntry,String>("heapValue"));

        this.symTableVarColumn.setCellValueFactory(new PropertyValueFactory<SymTableEntry,String>("varName"));
        this.symTableValueColumn.setCellValueFactory(new PropertyValueFactory<SymTableEntry,String>("Value"));


        this.outputListView.setCellFactory(TextFieldListCell.forListView(new StringConverter<Value>() {
            @Override
            public String toString(Value value) {
                return value.toString();
            }

            @Override
            public Value fromString(String s) {
                return null;
            }
        }));
        this.fileTableListView.setCellFactory(TextFieldListCell.forListView(new StringConverter<StringValue>() {
            @Override
            public String toString(StringValue stringValue) {
                return stringValue.toString();
            }

            @Override
            public StringValue fromString(String s) {
                return null;
            }
        }));

        this.executionStackListView.setCellFactory(TextFieldListCell.forListView(new StringConverter<IStmt>() {
            @Override
            public String toString(IStmt iStmt) {
                return iStmt.toString();
            }

            @Override
            public IStmt fromString(String s) {
                return null;
            }

        }));
        this.programStateListView.setCellFactory(TextFieldListCell.forListView(new StringConverter<PrgState>() {
            @Override
            public String toString(PrgState prgState) {
                return Integer.toString(prgState.getId());
            }

            @Override
            public PrgState fromString(String s) {
                return null;
            }
        } ));

        this.programStateListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        this.programStateListView.getSelectionModel().selectedItemProperty().addListener((a,b,state)->{
            if(state != null)
                showDataForSelectedProgram(state);
        });

        this.executeOneStepButton.setOnAction(actionEvent -> runOneStep(this.controller.getProgramListView().getSelectionModel().getSelectedItems().get(0)));



    }

    private void showDataForSelectedExample(RunExample runExample){
        this.heapTableView.getItems().clear();
        this.symTableView.getItems().clear();
        this.executionStackListView.getItems().clear();
        this.fileTableListView.getItems().clear();
        this.outputListView.getItems().clear();
        this.programStateListView.getItems().clear();

        List<PrgState>programStates = runExample.getCtrl().getRepository().getPrgList();

        if(programStates.size()!=0)
            this.selectedProgram=programStates.get(0);


        Heap<Integer,Value>sharedHeap=this.selectedProgram.getHeap();
        MyIDictionary<StringValue, BufferedReader>fileTable = this.selectedProgram.getFileTable();
        MyIList<Value>output = this.selectedProgram.getList();

        sharedHeap.getContent().forEach((address,value)->this.heapTableView.getItems().add(new HeapEntry(address.toString(),value.toString())));
        fileTable.getContent().forEach((filename,path)->this.fileTableListView.getItems().add(filename));
        output.getAll().forEach((value)->this.outputListView.getItems().add(value));

        programStates.forEach((prgState)->this.programStateListView.getItems().add(prgState));
        this.numberOfProgramStateField.setText(Integer.toString(programStateListView.getItems().size()));

        this.executionStackListView.getItems().clear();
        this.symTableView.getItems().clear();

        MyIDictionary<String, Value>symTable = this.selectedProgram.getDictionary();
        MyIStack<IStmt>stk = this.selectedProgram.getStack();

        symTable.getContent().forEach((name,value)->this.symTableView.getItems().add(new SymTableEntry(name.toString(), value.toString())));
        stk.getStack().forEach((statement)->this.executionStackListView.getItems().add(statement));


    }
    private void showDataForSelectedProgram(PrgState prgState){

        this.executionStackListView.getItems().clear();
        this.symTableView.getItems().clear();

        MyIDictionary<String, Value>symTable = prgState.getDictionary();
        MyIStack<IStmt>stk = prgState.getStack();

        symTable.getContent().forEach((name,value)->this.symTableView.getItems().add(new SymTableEntry(name.toString(), value.toString())));
        stk.getStack().forEach((statement)->this.executionStackListView.getItems().add(statement));


    }



    private void runOneStep(RunExample ex){

        try{
            ex.getCtrl().oneStep();
        } catch (InterruptedException | MyException | IOException | EmptyTableException e) {
            throw new RuntimeException(e);
        }
        //changeProgramState();
        showDataForSelectedExample(ex);

        if (selectedProgram.getStack().isEmpty()) {
            showAlert("Stack is Empty", "The execution stack is now empty.");
        }


    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }





}