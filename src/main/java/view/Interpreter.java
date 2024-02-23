package view;

import Exceptions.MyException;
import Model.*;
import State.Heap;
import State.MyHeap;
import State.PrgState;
import Statement.*;
import Statement.HeapStatements.HeapWritingStatement;
import Statement.HeapStatements.NewStatement;
import ctrl.Controller;
import exp.*;
import repo.IRepository;
import repo.Repository;
import type.*;
import value.BoolValue;
import value.IntValue;
import value.StringValue;
import value.Value;


import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

class Interpreter {
    public static void main(String[] args){

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));


        IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),

                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
        MyIStack<IStmt> exeStack1 = new ExeStack<>();
        MyIDictionary<String, Value> symTable1 = new MyDictionary<>();
        MyIList<Value> out1 = new Out<>();
        MyIDictionary<StringValue, BufferedReader>fileTable1 = new MyDictionary<>();
        Heap<Integer,Value> heap1 = new MyHeap<>();
        MyIDictionary<String, Type> typeEnv1 = new MyDictionary<>();
        try{
            ex1.typeCheck(typeEnv1);
            PrgState prg1 = new PrgState(exeStack1, symTable1, out1,fileTable1,heap1,PrgState.generateNewId(), ex1);
            List<PrgState>list1 = new ArrayList<>();
            //list1.add(prg1);
            IRepository repo1 = new Repository(list1, "log1.txt");
            repo1.add(prg1);
            Controller ctrl1 = new Controller(repo1);
            menu.addCommand(new RunExample("1", ex1.toString(), ctrl1));


        }catch (MyException e){
            System.out.println("ex1 failed");
        }


        IStmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()),
                new CompStmt(new VarDeclStmt("b", new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithExp(new ValueExp(new IntValue(2)), new ArithExp(new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5)), 3), 1)),
                                new CompStmt(new AssignStmt("b", new ArithExp(new VarExp("a"), new ValueExp(new IntValue(1)), 1)), new PrintStmt(new VarExp("b"))))));

        MyIStack<IStmt> exeStack2 = new ExeStack<>();
        MyIDictionary<String, Value> symTable2 = new MyDictionary<>();
        MyIList<Value> out2 = new Out<>();
        MyIDictionary<StringValue, BufferedReader>fileTable2 = new MyDictionary<>();
        Heap<Integer,Value> heap2 = new MyHeap<>();

        MyIDictionary<String, Type> typeEnv2 = new MyDictionary<>();
        try{
            ex2.typeCheck(typeEnv2);
            PrgState prg2 = new PrgState(exeStack2, symTable2, out2,fileTable2,heap2, PrgState.generateNewId(),ex2);
            List<PrgState>list2 = new ArrayList<>();
            //list2.add(prg2);
            IRepository repo2 = new Repository(list2, "log2.txt");
            repo2.add(prg2);
            Controller ctrl2 = new Controller(repo2);
            menu.addCommand(new RunExample("2", ex2.toString(), ctrl2));

        }catch (MyException e){
            System.out.println("ex2 failed");
        }






        IStmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new VarExp("v"))))));
        MyIStack<IStmt> exeStack3 = new ExeStack<>();
        MyIDictionary<String, Value> symTable3 = new MyDictionary<>();
        MyIList<Value> out3 = new Out<>();
        MyIDictionary<StringValue, BufferedReader>fileTable3 = new MyDictionary<>();
        Heap<Integer,Value> heap3 = new MyHeap<>();

        MyIDictionary<String, Type> typeEnv3 = new MyDictionary<>();
        try{
            ex3.typeCheck(typeEnv3);
            PrgState prg3 = new PrgState(exeStack3, symTable3, out3, fileTable3,heap3,PrgState.generateNewId(), ex3);
            List<PrgState>list3 = new ArrayList<>();
            // list1.add(prg3);
            IRepository repo3 = new Repository(list3, "log3.txt");
            repo3.add(prg3);
            Controller ctrl3 = new Controller(repo3);
            menu.addCommand(new RunExample("3", ex3.toString(), ctrl3));


        }catch (MyException e){
            System.out.println("ex3 failed");
        }





        IStmt stringDeclaration = new VarDeclStmt("varf", new StringType());
        IStmt assignment = new AssignStmt("varf", new ValueExp(new StringValue("test.in")));
        IStmt open = new OpenRFileStatement(new VarExp("varf"));
        IStmt intDeclaration = new VarDeclStmt("varc", new IntType());
        IStmt readFile = new ReadFileStatement(new VarExp("varf"), "varc");
        IStmt print = new PrintStmt(new VarExp("varc"));
        IStmt close = new CloseRFileStatement(new VarExp("varf"));

        IStmt ex4 = new CompStmt(stringDeclaration, new CompStmt(assignment, new CompStmt(open,
                new CompStmt(intDeclaration, new CompStmt(readFile, new CompStmt(print,
                        new CompStmt(readFile, new CompStmt(print, close))))))));
        MyIStack<IStmt> exeStack4 = new ExeStack<>();
        MyIDictionary<String, Value> symTable4 = new MyDictionary<>();
        MyIList<Value> out4 = new Out<>();
        MyIDictionary<StringValue, BufferedReader>fileTable4 = new MyDictionary<>();
        Heap<Integer,Value> heap4 = new MyHeap<>();

        MyIDictionary<String, Type> typeEnv4 = new MyDictionary<>();
        try{
            ex4.typeCheck(typeEnv4);
            PrgState prg4 = new PrgState(exeStack4, symTable4, out4, fileTable4,heap4,PrgState.generateNewId(), ex4);
            List<PrgState>list4 = new ArrayList<>();
            // list4.add(prg4);
            IRepository repo4 = new Repository(list4, "log4.txt");
            repo4.add(prg4);
            Controller ctrl4 = new Controller(repo4);
            menu.addCommand(new RunExample("4", "string varf; " +
                " varf=\"test.in\"; " +
                " openRFile(varf); " +
                " int varc; " +
                " readFile(varf,varc) ;print(varc); " +
                " readFile(varf,varc); print(varc) " +
                " closeRFile(varf) ", ctrl4));



        }catch (MyException e){
            System.out.println("ex4 failed");
        }



        IStmt refDeclaration = new VarDeclStmt("v",new RefType(new IntType()));
        IStmt alloc = new NewStatement("v",new ValueExp(new IntValue(20)));
        IStmt alloca = new VarDeclStmt("a",new RefType(new RefType(new IntType())));
        IStmt vina = new NewStatement("a",new VarExp("v"));
        IStmt alloc30 = new NewStatement("v",new ValueExp(new IntValue(30)));
        IStmt print1 =  new PrintStmt(new HeapReadingExp(new HeapReadingExp(new VarExp("a"))));
        IStmt ex5 = new CompStmt(refDeclaration,new CompStmt(alloc,new CompStmt(alloca,new CompStmt(vina,new CompStmt(alloc30,print1)))));


        MyIStack<IStmt> exeStack5 = new ExeStack<>();
        MyIDictionary<String, Value> symTable5 = new MyDictionary<>();
        MyIList<Value> out5 = new Out<>();
        MyIDictionary<StringValue, BufferedReader>fileTable5 = new MyDictionary<>();
        Heap<Integer,Value> heap5 = new MyHeap<>();

        MyIDictionary<String, Type> typeEnv5 = new MyDictionary<>();
        try{
            ex5.typeCheck(typeEnv5);
            PrgState prg5 = new PrgState(exeStack5, symTable5, out5, fileTable5,heap5,PrgState.generateNewId(), ex5);
            List<PrgState>list5 = new ArrayList<>();
            // list4.add(prg4);
            IRepository repo5 = new Repository(list5, "log5.txt");
            repo5.add(prg5);
            Controller ctrl5 = new Controller(repo5);
            menu.addCommand(new RunExample("5", ex5.toString(), ctrl5));


        }catch (MyException e){
            System.out.println("ex5 failed");
        }



        //Ref int v;new(v,20);Ref Ref int a; new(a,v);print v;print a

        IStmt declare_v = new VarDeclStmt("v",new RefType(new IntType()));
        IStmt alloc_v = new NewStatement("v",new ValueExp(new IntValue(20)));
        IStmt declare_a = new VarDeclStmt("a",new RefType(new RefType(new IntType())));
        IStmt alloc_a = new NewStatement("a",new VarExp("v"));
        IStmt print_v = new PrintStmt(new VarExp("v"));
        IStmt print_a = new PrintStmt(new VarExp("a"));
        IStmt ex6 = new CompStmt(declare_v,new CompStmt(alloc_v,new CompStmt(declare_a,new CompStmt(alloc_a,new CompStmt(print_v,print_a)))));

        MyIStack<IStmt> exeStack6 = new ExeStack<>();
        MyIDictionary<String, Value> symTable6 = new MyDictionary<>();
        MyIList<Value> out6 = new Out<>();
        MyIDictionary<StringValue, BufferedReader>fileTable6 = new MyDictionary<>();
        Heap<Integer,Value> heap6 = new MyHeap<>();

        MyIDictionary<String, Type> typeEnv6 = new MyDictionary<>();
        try{
            ex6.typeCheck(typeEnv6);
            PrgState prg6 = new PrgState(exeStack6, symTable6, out6, fileTable6,heap6,PrgState.generateNewId(), ex6);
            List<PrgState>list6 = new ArrayList<>();
            // list4.add(prg4);
            IRepository repo6 = new Repository(list6, "log6.txt");
            repo6.add(prg6);
            Controller ctrl6 = new Controller(repo6);
            menu.addCommand(new RunExample("6", ex6.toString(), ctrl6));


        }catch (MyException e){
            System.out.println("ex6 failed");
        }



        //ex7 Ref int v;new(v,20);Ref Ref int a; new(a,v);print(rH(v));print(rH(rH(a))+5)
        IStmt declare_v7 = new VarDeclStmt("v",new RefType(new IntType()));
        IStmt alloc_v7 = new NewStatement("v",new ValueExp(new IntValue(20)));
        IStmt declare_a7 = new VarDeclStmt("a",new RefType(new RefType(new IntType())));
        IStmt alloc_a7 = new NewStatement("a",new VarExp("v"));
        Exp read_v7 = new HeapReadingExp(new VarExp("v"));
        IStmt print_v7 = new PrintStmt(read_v7);
        Exp read_a7 = new HeapReadingExp(new HeapReadingExp(new VarExp("a")));
        Exp add_a7 = new ArithExp(read_a7, new ValueExp(new IntValue(5)),1);
        IStmt print_a7 = new PrintStmt(add_a7);
        IStmt ex7 = new CompStmt(declare_v7,new CompStmt(alloc_v7,new CompStmt(declare_a7,new CompStmt(alloc_a7,new CompStmt(print_v7,print_a7)))));

        MyIStack<IStmt> exeStack7 = new ExeStack<>();
        MyIDictionary<String, Value> symTable7 = new MyDictionary<>();
        MyIList<Value> out7 = new Out<>();
        MyIDictionary<StringValue, BufferedReader>fileTable7 = new MyDictionary<>();
        Heap<Integer,Value> heap7 = new MyHeap<>();

        MyIDictionary<String, Type> typeEnv7 = new MyDictionary<>();
        try{
            ex7.typeCheck(typeEnv7);
            PrgState prg7 = new PrgState(exeStack7, symTable7, out7, fileTable7,heap7,PrgState.generateNewId(), ex7);
            List<PrgState>list7 = new ArrayList<>();
            // list4.add(prg4);
            IRepository repo7 = new Repository(list7, "log7.txt");
            repo7.add(prg7);
            Controller ctrl7 = new Controller(repo7);
            menu.addCommand(new RunExample("7", ex7.toString(), ctrl7));


        }catch (MyException e){
            System.out.println("ex7 failed");
        }




        //ex8- Ref int v;new(v,20);print(rH(v)); wH(v,30);print(rH(v)+5);
        IStmt declare_v8 = new VarDeclStmt("v",new RefType(new IntType()));
        IStmt alloc_v8 = new NewStatement("v", new ValueExp(new IntValue(20)));
        Exp read_v8 = new HeapReadingExp(new VarExp("v"));
        IStmt print_v8 = new PrintStmt(read_v8);
        IStmt write_v8 = new HeapWritingStatement("v",new ValueExp(new IntValue(30)));
        Exp read_v82 = new HeapReadingExp(new VarExp("v"));
        Exp add8 = new ArithExp(read_v82,new ValueExp(new IntValue(5)),1);
        IStmt print_v82 = new PrintStmt(add8);
        IStmt ex8 = new CompStmt(declare_v8, new CompStmt(alloc_v8, new CompStmt(print_v8,new CompStmt(write_v8,print_v82))));

        MyIStack<IStmt> exeStack8 = new ExeStack<>();
        MyIDictionary<String, Value> symTable8 = new MyDictionary<>();
        MyIList<Value> out8 = new Out<>();
        MyIDictionary<StringValue, BufferedReader>fileTable8 = new MyDictionary<>();
        Heap<Integer,Value> heap8 = new MyHeap<>();

        MyIDictionary<String, Type> typeEnv8 = new MyDictionary<>();
        try{
            ex8.typeCheck(typeEnv8);
            PrgState prg8 = new PrgState(exeStack8, symTable8, out8, fileTable8,heap8,PrgState.generateNewId(), ex8);
            List<PrgState>list8 = new ArrayList<>();
            IRepository repo8 = new Repository(list8, "log8.txt");
            repo8.add(prg8);
            Controller ctrl8 = new Controller(repo8);
            menu.addCommand(new RunExample("8", ex8.toString(), ctrl8));


        }catch (MyException e){
            System.out.println("ex8 failed");
        }



        //ex9-int v; v=4; (while (v>0) print(v);v=v-1);print(v)
        IStmt declare_v9 = new VarDeclStmt("v",new IntType());
        IStmt alloc_v9 = new AssignStmt("v",new ValueExp(new IntValue(4)));
        Exp rel_expr = new RelationalExp(new VarExp("v"), new ValueExp(new IntValue(0)),4);
        IStmt print_v91 = new PrintStmt(new VarExp("v"));
        Exp arith =  new ArithExp(new VarExp("v"),new ValueExp(new IntValue(1)),2);
        IStmt assign_v10 = new AssignStmt("v",arith);
        IStmt comp = new CompStmt(print_v91,assign_v10);
        IStmt whileSt = new WhileStatement(rel_expr,comp);
        IStmt print_v92 = new PrintStmt(new VarExp("v"));
        IStmt ex9 = new CompStmt(declare_v9,new CompStmt(alloc_v9,new CompStmt(whileSt,print_v92)));
        //IStmt ex9 = new CompStmt(declare_v9,new CompStmt(alloc_v9,whileSt));

        MyIStack<IStmt> exeStack9 = new ExeStack<>();
        MyIDictionary<String, Value> symTable9 = new MyDictionary<>();
        MyIList<Value> out9 = new Out<>();
        MyIDictionary<StringValue, BufferedReader>fileTable9 = new MyDictionary<>();
        Heap<Integer,Value> heap9 = new MyHeap<>();

        MyIDictionary<String, Type> typeEnv9 = new MyDictionary<>();
        try{
            ex9.typeCheck(typeEnv9);
            PrgState prg9 = new PrgState(exeStack9, symTable9, out9, fileTable9,heap9, PrgState.generateNewId(),ex9);
            List<PrgState>list9 = new ArrayList<>();
            IRepository repo9 = new Repository(list9, "log9.txt");
            repo9.add(prg9);
            Controller ctrl9 = new Controller(repo9);
            menu.addCommand(new RunExample("9", ex9.toString(), ctrl9));


        }catch (MyException e){
            System.out.println("ex9 failed");
        }



        //int v;Ref int a; v = 10; new(a, 22);fork(wH(a,30);v=32;print(v);print(rH(a)));print(v);print(a)
        IStmt declare_v10 = new VarDeclStmt("v", new IntType());
        IStmt declare_a10 = new VarDeclStmt("a", new RefType(new IntType()));
        IStmt ass_v10 = new AssignStmt("v", new ValueExp(new IntValue(10)));
        IStmt alloc_a10 = new NewStatement("a", new ValueExp(new IntValue(22)));
        IStmt write_a10 = new HeapWritingStatement("a", new ValueExp(new IntValue(30)));
        IStmt ass32_v10 = new AssignStmt("v", new ValueExp(new IntValue(32)));
        IStmt print_v10 = new PrintStmt(new VarExp("v"));
        IStmt print_a10 = new PrintStmt(new VarExp("a"));
        Exp read_a10 = new HeapReadingExp(new VarExp("a"));
        IStmt print_read_a10 = new PrintStmt(read_a10);
        IStmt fork = new ForkStatement(new CompStmt(write_a10, new CompStmt(ass32_v10, new CompStmt(print_v10, print_read_a10))));
        IStmt ex10 = new CompStmt(declare_v10, new CompStmt(declare_a10, new CompStmt(ass_v10, new CompStmt(alloc_a10, new CompStmt(fork, new CompStmt(print_v10, print_read_a10))))));
        //IStmt ex11 = new CompStmt(declare_v10, new CompStmt(ass_v10, new CompStmt(print_v10, new CompStmt(new ForkStatement(new CompStmt(ass32_v10, print_v10)) ,print_v10))));

        MyIStack<IStmt> exeStack10 = new ExeStack<>();
        MyIDictionary<String, Value> symTable10 = new MyDictionary<>();
        MyIList<Value> out10 = new Out<>();
        MyIDictionary<StringValue, BufferedReader>fileTable10 = new MyDictionary<>();
        Heap<Integer,Value> heap10 = new MyHeap<>();

        MyIDictionary<String, Type> typeEnv10 = new MyDictionary<>();
        try{
            ex10.typeCheck(typeEnv10);
            PrgState prg10 = new PrgState(exeStack10, symTable10, out10, fileTable10,heap10, PrgState.generateNewId(),ex10);
            List<PrgState>list10 = new ArrayList<>();
            IRepository repo10 = new Repository(list10, "log10.txt");
            repo10.add(prg10);
            Controller ctrl10 = new Controller(repo10);
            menu.addCommand(new RunExample("10", ex10.toString(), ctrl10));


        }catch (MyException e){
            System.out.println("ex10 failed");

        }

        IStmt ex11 = new CompStmt(new VarDeclStmt("v", new IntType()),

                new CompStmt(new AssignStmt("v", new ValueExp(new BoolValue(false))), new PrintStmt(new VarExp("v"))));
        MyIStack<IStmt> exeStack11 = new ExeStack<>();
        MyIDictionary<String, Value> symTable11 = new MyDictionary<>();
        MyIList<Value> out11 = new Out<>();
        MyIDictionary<StringValue, BufferedReader>fileTable11 = new MyDictionary<>();
        Heap<Integer,Value> heap11 = new MyHeap<>();

        MyIDictionary<String, Type> typeEnv11 = new MyDictionary<>();
        try{
            ex11.typeCheck(typeEnv11);
            PrgState prg11 = new PrgState(exeStack11, symTable11, out11, fileTable11,heap11, PrgState.generateNewId(),ex11);
            List<PrgState>list11 = new ArrayList<>();
            IRepository repo11 = new Repository(list11, "log11.txt");
            repo11.add(prg11);
            Controller ctrl11 = new Controller(repo11);
            menu.addCommand(new RunExample("11", ex11.toString(), ctrl11));


        }catch (MyException e){
            System.out.println("ex11 failed"+"\n");

        }
        //Ref int a;new(a,20);for(v=0;v<3;v=v+1)fork(print(v);v=v*rh(a)));print(Rh(a))
//        IStmt ex12 = new CompStmt(new VarDeclStmt("v",new IntType()),
//                new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(20))),
//                        new ForStatement("v",new ValueExp(new IntValue(0)),
//                                new ValueExp(new IntValue(3)),new ArithExp(new VarExp("v"),new ValueExp(new IntValue(1)),1),new PrintStmt(new VarExp("v")))));
        IStmt declare_a12 = new VarDeclStmt("a",new RefType(new IntType()));
        IStmt declare_v12 = new VarDeclStmt("v",new IntType());
        IStmt alloc_a12 = new NewStatement("a",new ValueExp(new IntValue(20)));
        IStmt fork_stmt = new ForkStatement(new CompStmt(new PrintStmt(new VarExp("v")),new AssignStmt("v",new ArithExp(new VarExp("v"),new HeapReadingExp(new VarExp("a")),3))));
        IStmt for_stmt = new ForStatement("v",new ValueExp(new IntValue(0)),new ValueExp(new IntValue(3)),
                new ArithExp(new VarExp("v"),new ValueExp(new IntValue(1)),1),fork_stmt);
        IStmt print_a12=new PrintStmt(new HeapReadingExp(new VarExp("a")));

        IStmt ex12 = new CompStmt(declare_a12,new CompStmt(declare_v12,new CompStmt(alloc_a12,
                new CompStmt(for_stmt,print_a12))));



        MyIStack<IStmt> exeStack12 = new ExeStack<>();
        MyIDictionary<String, Value> symTable12 = new MyDictionary<>();
        MyIList<Value> out12 = new Out<>();
        MyIDictionary<StringValue, BufferedReader>fileTable12 = new MyDictionary<>();
        Heap<Integer,Value> heap12 = new MyHeap<>();
        MyIDictionary<String, Type> typeEnv12 = new MyDictionary<>();

        try{
            ex12.typeCheck(typeEnv12);
            PrgState prg12 = new PrgState(exeStack12, symTable12, out12, fileTable12,heap12, PrgState.generateNewId(),ex12);
            List<PrgState>list12 = new ArrayList<>();
            IRepository repo12 = new Repository(list12, "log12.txt");
            repo12.add(prg12);
            Controller ctrl12 = new Controller(repo12);
            menu.addCommand(new RunExample("12", ex12.toString(), ctrl12));


        }catch (MyException e){
            System.out.println("ex12 failed"+"\n");

        }

        IStmt ex13 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(4))),
                        new CompStmt(
                        new DoWhileStatement(new RelationalExp(new VarExp("v"),
                                new ValueExp(new IntValue(0)),5),new CompStmt(new PrintStmt(new VarExp("v")),
                                new AssignStmt("v", new ArithExp(new VarExp("v"),new ValueExp(new IntValue(1)),2)))),
                                new PrintStmt(new VarExp("v")))));

        MyIStack<IStmt> exeStack13 = new ExeStack<>();
        MyIDictionary<String, Value> symTable13 = new MyDictionary<>();
        MyIList<Value> out13 = new Out<>();
        MyIDictionary<StringValue, BufferedReader>fileTable13 = new MyDictionary<>();
        Heap<Integer,Value> heap13 = new MyHeap<>();
        MyIDictionary<String, Type> typeEnv13 = new MyDictionary<>();

        try{
            ex13.typeCheck(typeEnv13);
            PrgState prg13 = new PrgState(exeStack13, symTable13, out13, fileTable13,heap13, PrgState.generateNewId(),ex13);
            List<PrgState>list13 = new ArrayList<>();
            IRepository repo13 = new Repository(list13, "log13.txt");
            repo13.add(prg13);
            Controller ctrl13 = new Controller(repo13);
            menu.addCommand(new RunExample("13", ex13.toString(), ctrl13));


        }catch (MyException e){
            System.out.println("ex13 failed"+"\n");

        }

        //v=0;
        //(repeat (fork(print(v);v=v-1);v=v+1) until v==3);
        //x=1;y=2;z=3;w=4;
        //print (v*10);
        IStmt declare_x = new VarDeclStmt("x",new IntType());
        IStmt declare_y = new VarDeclStmt("y",new IntType());
        IStmt declare_z = new VarDeclStmt("z",new IntType());
        IStmt declare_w = new VarDeclStmt("w",new IntType());
        IStmt alloc_x = new AssignStmt("x",new ValueExp(new IntValue(1)));
        IStmt alloc_y = new AssignStmt("y",new ValueExp(new IntValue(2)));
        IStmt alloc_z = new AssignStmt("z",new ValueExp(new IntValue(3)));
        IStmt alloc_w = new AssignStmt("w",new ValueExp(new IntValue(4)));
        IStmt decxyz = new CompStmt(declare_x,new CompStmt(declare_y,new CompStmt(declare_z,declare_w)));
        IStmt allxyz = new CompStmt(alloc_x,new CompStmt(alloc_y,new CompStmt(alloc_w,alloc_z)));

        IStmt declare_v14 = new VarDeclStmt("v",new IntType());
        IStmt alloc_v14 = new AssignStmt("v",new ValueExp(new IntValue(0)));
        IStmt assign_vminus = new AssignStmt("v",new ArithExp(new VarExp("v"),new ValueExp(new IntValue(1)),2));
        IStmt fork14 = new ForkStatement(new CompStmt(new PrintStmt(new VarExp("v")),assign_vminus));
        IStmt assign_v_plus=new AssignStmt("v",new ArithExp(new VarExp("v"),new ValueExp(new IntValue(1)),1));
        Exp rel_exp14 = new RelationalExp(new VarExp("v"),new ValueExp(new IntValue(3)),3);
        IStmt reapeatStmt = new RepeatUntilStatement(new CompStmt(fork14,assign_v_plus),rel_exp14);
        IStmt print_v14 = new PrintStmt(new ArithExp(new VarExp("v"),new ValueExp(new IntValue(10)),3));
        IStmt ex14 = new CompStmt(decxyz,new CompStmt(declare_v14,new CompStmt(alloc_v14,new CompStmt(reapeatStmt,new CompStmt(allxyz,print_v14)))));

        MyIStack<IStmt> exeStack14 = new ExeStack<>();
        MyIDictionary<String, Value> symTable14 = new MyDictionary<>();
        MyIList<Value> out14 = new Out<>();
        MyIDictionary<StringValue, BufferedReader>fileTable14 = new MyDictionary<>();
        Heap<Integer,Value> heap14 = new MyHeap<>();
        MyIDictionary<String, Type> typeEnv14 = new MyDictionary<>();

        try{
            ex14.typeCheck(typeEnv14);
            PrgState prg14 = new PrgState(exeStack14, symTable14, out14, fileTable14,heap14, PrgState.generateNewId(),ex14);
            List<PrgState>list14 = new ArrayList<>();
            IRepository repo14 = new Repository(list14, "log14.txt");
            repo14.add(prg14);
            Controller ctrl14 = new Controller(repo14);
            menu.addCommand(new RunExample("14", ex14.toString(), ctrl14));


        }catch (MyException e){
            System.out.println("ex14 failed"+"\n");

        }


//        Ref int a; Ref int b; int v;
//        new(a,0); new(b,0);
//        wh(a,1); wh(b,2);
//        v=(rh(a)<rh(b))?100:200;
//        print(v);
//        v= ((rh(b)-2)>rh(a))?100:200;
//        print(v);
        IStmt declare_a15 = new VarDeclStmt("a",new RefType(new IntType()));
        IStmt declare_b15 = new VarDeclStmt("b",new RefType(new IntType()));
        IStmt declare_v15 = new VarDeclStmt("v",new IntType());
        IStmt alloc_a15 = new NewStatement("a",new ValueExp(new IntValue(0)));
        IStmt alloc_b15 = new NewStatement("b",new ValueExp(new IntValue(0)));
        IStmt wh_a15 = new HeapWritingStatement("a",new ValueExp(new IntValue(1)));
        IStmt wh_b15 = new HeapWritingStatement("b",new ValueExp(new IntValue(2)));
        Exp rel_exp_a_b = new RelationalExp(new HeapReadingExp(new VarExp("a")),new HeapReadingExp(new VarExp("b")),1);
        IStmt cond_stmt = new ConditionalStatement("v",rel_exp_a_b,new ValueExp(new IntValue(100)),new ValueExp(new IntValue(200)));
        IStmt print_v15 = new PrintStmt(new VarExp("v"));
        Exp b_minus = new ArithExp(new HeapReadingExp(new VarExp("b")),new ValueExp(new
                IntValue(2)),2);
        Exp rel2_exp = new RelationalExp(b_minus,new HeapReadingExp(new VarExp("a")),5);
        IStmt cond2_stmt = new ConditionalStatement("v",rel2_exp,new ValueExp(new IntValue(100)),new ValueExp(new IntValue(200)));
        IStmt ex15 = new CompStmt(declare_a15,new CompStmt(declare_b15,new CompStmt(declare_v15,new CompStmt(
                alloc_a15,new CompStmt(alloc_b15,new CompStmt(wh_a15,new CompStmt(wh_b15,
                new CompStmt(cond_stmt,new CompStmt(print_v15,new CompStmt(cond2_stmt,print_v15))))))))));



        MyIStack<IStmt> exeStack15 = new ExeStack<>();
        MyIDictionary<String, Value> symTable15 = new MyDictionary<>();
        MyIList<Value> out15 = new Out<>();
        MyIDictionary<StringValue, BufferedReader>fileTable15 = new MyDictionary<>();
        Heap<Integer,Value> heap15 = new MyHeap<>();
        MyIDictionary<String, Type> typeEnv15 = new MyDictionary<>();

        try{
            ex15.typeCheck(typeEnv15);
            PrgState prg15 = new PrgState(exeStack15, symTable15, out15, fileTable15,heap15, PrgState.generateNewId(),ex15);
            List<PrgState>list15 = new ArrayList<>();
            IRepository repo15 = new Repository(list15, "log15.txt");
            repo15.add(prg15);
            Controller ctrl15 = new Controller(repo15);
            menu.addCommand(new RunExample("15", ex15.toString(), ctrl15));


        }catch (MyException e){
            System.out.println("ex15 failed"+"\n");

        }

//
//        int a; int b; int c;
//        a=1;b=2;c=5;
//        (switch(a*10)
//        (case (b*c) : print(a);print(b))
//        (case (10) : print(100);print(200))
//        (default : print(300)));
//        print(300)

        IStmt declare_a16=new VarDeclStmt("a",new IntType());
        IStmt declare_b16=new VarDeclStmt("b",new IntType());
        IStmt declare_c16=new VarDeclStmt("c",new IntType());
        IStmt alloc_a16 = new AssignStmt("a",new ValueExp(new IntValue(1)));
        IStmt alloc_b16 = new AssignStmt("b",new ValueExp(new IntValue(2)));
        IStmt alloc_c16 = new AssignStmt("c",new ValueExp(new IntValue(5)));
        IStmt swirch_stmt = new SwitchStatement(new ArithExp(new VarExp("a"),new ValueExp(new IntValue(10)),3),
                new ArithExp(new VarExp("b"),new VarExp("c"),3),
                new ValueExp(new IntValue(10)),new CompStmt(new PrintStmt(new VarExp("a")),new PrintStmt(new VarExp("b"))),
                new CompStmt(new PrintStmt(new ValueExp(new IntValue(100))),new PrintStmt(new ValueExp(new IntValue(200)))),
                new PrintStmt(new ValueExp(new IntValue(300))));
        IStmt print_300 = new PrintStmt(new ValueExp(new IntValue(300)));
        IStmt ex16 = new CompStmt(declare_a16,new CompStmt(declare_b16,new CompStmt(declare_c16,
                new CompStmt(alloc_a16,new CompStmt(alloc_b16,new CompStmt(alloc_c16,
                        new CompStmt(swirch_stmt,print_300)))))));

        MyIStack<IStmt> exeStack16 = new ExeStack<>();
        MyIDictionary<String, Value> symTable16 = new MyDictionary<>();
        MyIList<Value> out16 = new Out<>();
        MyIDictionary<StringValue, BufferedReader>fileTable16 = new MyDictionary<>();
        Heap<Integer,Value> heap16 = new MyHeap<>();
        MyIDictionary<String, Type> typeEnv16 = new MyDictionary<>();

        try{
            ex16.typeCheck(typeEnv16);
            PrgState prg16 = new PrgState(exeStack16, symTable16, out16, fileTable16,heap16, PrgState.generateNewId(),ex16);
            List<PrgState>list16 = new ArrayList<>();
            IRepository repo16 = new Repository(list16, "log16.txt");
            repo16.add(prg16);
            Controller ctrl16 = new Controller(repo16);
            menu.addCommand(new RunExample("16", ex16.toString(), ctrl16));


        }catch (MyException e){
            System.out.println("ex16 failed"+"\n");

        }

//        v=0;
//        (while(v<3) (fork(print(v);v=v+1);v=v+1);
//        sleep(5);
//        print(v*10)
        IStmt declare_v17 = new VarDeclStmt("v",new IntType());
        IStmt v17_plus_1 = new AssignStmt("v",new ArithExp(new VarExp("v"),new ValueExp(new IntValue(1)),1));
        IStmt fork17 = new ForkStatement(new CompStmt(new PrintStmt(new VarExp("v")),v17_plus_1));
        IStmt while17 = new WhileStatement(new RelationalExp(new VarExp("v"),new ValueExp(new IntValue(3)),1),
                new CompStmt(fork17,v17_plus_1));
        IStmt sleepStmt = new SleepStatement(5);
        IStmt ex17 = new CompStmt(declare_v17,new CompStmt(while17,new CompStmt(sleepStmt,new PrintStmt(new ArithExp(new VarExp("v"),new ValueExp(new IntValue(10)),3)))));

        MyIStack<IStmt> exeStack17 = new ExeStack<>();
        MyIDictionary<String, Value> symTable17 = new MyDictionary<>();
        MyIList<Value> out17 = new Out<>();
        MyIDictionary<StringValue, BufferedReader>fileTable17 = new MyDictionary<>();
        Heap<Integer,Value> heap17 = new MyHeap<>();
        MyIDictionary<String, Type> typeEnv17 = new MyDictionary<>();

        try{
            ex17.typeCheck(typeEnv17);
            PrgState prg17 = new PrgState(exeStack17, symTable17, out17, fileTable17,heap17, PrgState.generateNewId(),ex17);
            List<PrgState>list17 = new ArrayList<>();
            IRepository repo17 = new Repository(list17, "log17.txt");
            repo17.add(prg17);
            Controller ctrl17 = new Controller(repo17);
            menu.addCommand(new RunExample("17", ex17.toString(), ctrl17));


        }catch (MyException e){
            System.out.println("ex17 failed"+"\n");

        }

        //v1=2;v2=3; (if (v1) then print(MUL(v1,v2)) else print (v1))
        IStmt declare_v1_18 = new VarDeclStmt("v1",new IntType());
        IStmt declare_v2_18 = new VarDeclStmt("v2",new IntType());
        IStmt alloc_v1_18 = new AssignStmt("v1",new ValueExp(new IntValue(2)));
        IStmt alloc_v2_18 = new AssignStmt("v2",new ValueExp(new IntValue(3)));
        IStmt if18 = new IfStmt(new RelationalExp(new VarExp("v1"),new ValueExp(new IntValue(0)),4),
                new PrintStmt(new MulExpression(new VarExp("v1"),new VarExp("v2"))),
                new PrintStmt(new VarExp("v1")));
        IStmt ex18 = new CompStmt(declare_v1_18,new CompStmt(declare_v2_18,
                new CompStmt(alloc_v1_18,new CompStmt(alloc_v2_18,if18))));
        MyIStack<IStmt> exeStack18 = new ExeStack<>();
        MyIDictionary<String, Value> symTable18 = new MyDictionary<>();
        MyIList<Value> out18 = new Out<>();
        MyIDictionary<StringValue, BufferedReader>fileTable18 = new MyDictionary<>();
        Heap<Integer,Value> heap18 = new MyHeap<>();
        MyIDictionary<String, Type> typeEnv18 = new MyDictionary<>();

        try{
            ex18.typeCheck(typeEnv18);
            PrgState prg18 = new PrgState(exeStack18, symTable18, out18, fileTable18,heap18, PrgState.generateNewId(),ex18);
            List<PrgState>list18 = new ArrayList<>();
            IRepository repo18 = new Repository(list18, "log18.txt");
            repo18.add(prg18);
            Controller ctrl18 = new Controller(repo18);
            menu.addCommand(new RunExample("18", ex18.toString(), ctrl18));


        }catch (MyException e){
            System.out.println("ex18 failed"+"\n");

        }



        menu.show();
    }
}
