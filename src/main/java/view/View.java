//package view;
//
//import Exceptions.MyException;
//import Model.*;
//import State.PrgState;
//import Statement.*;
//import ctrl.Controller;
//import exp.ArithExp;
//import exp.ValueExp;
//import exp.VarExp;
//import repo.IRepository;
//import repo.Repository;
//import type.BoolType;
//import type.IntType;
//import value.BoolValue;
//import value.IntValue;
//import value.Value;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class View {
//
//
//    private void options(){
//        System.out.println("Menu");
//        System.out.println("1.Choose the ex1");
//        System.out.println("2.Choose the ex2");
//        System.out.println("3.Choose the ex3");
//        System.out.println("4.Exit");
//    }
//    public void menu() throws MyException {
//
//        Scanner scanner = new Scanner(System.in);
//        int option;
//
//        MyIStack<IStmt> exeStack = new ExeStack<>();
//        MyIDictionary<String, value.Value> symTable = new SymTable<>();
//        MyIList<Value> out = new Out<>();
//
//        List<PrgState> programStates = new ArrayList<>();
//
//        IRepository repository = new Repository(programStates);
//
//        Controller ctrl = new Controller(repository);
//        while(true){
//
//            options();
//            System.out.println("your option:");
//            option = scanner.nextInt();
//            try {
//                programStates.clear();
//                switch (option) {
//
//                    case 1:
//                        IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
//
//                                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
//                        MyIStack<IStmt> exeStack1 = new ExeStack<>();
//                        MyIDictionary<String, value.Value> symTable1 = new SymTable<>();
//                        MyIList<Value> out1 = new Out<>();
//                        PrgState prg1 = new PrgState(exeStack1, symTable1, out1, ex1);
//                        ctrl.allSteps();
//                        break;
//                    case 2:
//                        IStmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()),
//                                new CompStmt(new VarDeclStmt("b", new IntType()),
//                                        new CompStmt(new AssignStmt("a", new ArithExp(new ValueExp(new IntValue(2)), new ArithExp(new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5)), 3), 1)),
//                                                new CompStmt(new AssignStmt("b", new ArithExp(new VarExp("a"), new ValueExp(new IntValue(1)), 1)), new PrintStmt(new VarExp("b"))))));
//                        MyIStack<IStmt> exeStack2 = new ExeStack<>();
//                        MyIDictionary<String, value.Value> symTable2 = new SymTable<>();
//                        MyIList<Value> out2 = new Out<>();
//                        PrgState prg2 = new PrgState(exeStack2, symTable2, out2, ex2);
//                        ctrl.allSteps(prg2);
//                        break;
//                    case 3:
//                        IStmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()),
//                                new CompStmt(new VarDeclStmt("v", new IntType()),
//                                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
//                                                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new VarExp("v"))))));
//                        MyIStack<IStmt> exeStack3 = new ExeStack<>();
//                        MyIDictionary<String, value.Value> symTable3 = new SymTable<>();
//                        MyIList<Value> out3 = new Out<>();
//                        PrgState prg3 = new PrgState(exeStack3, symTable3, out3, ex3);
//                        ctrl.allSteps(prg3);
//                        break;
//                    case 4:
//                        scanner.close();
//                        System.exit(0);
//                    default:
//                        System.out.println("Invalid choice. Please select a valid option.");
//                }
//
//            }catch (MyException e){
//                System.out.println("An exception occurred");
//            }
//        }
//    }
//}
