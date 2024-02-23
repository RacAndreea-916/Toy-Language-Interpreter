package Statement;

import Exceptions.EmptyTableException;
import Exceptions.MyException;
import Model.MyIDictionary;
import State.PrgState;
import exp.Exp;
import exp.RelationalExp;
import type.Type;

import java.io.IOException;

public class SwitchStatement implements IStmt{

    private Exp mainExpression;
    private Exp expression1;
    private Exp expression2;

    private IStmt statement1;
    private IStmt statement2;
    private IStmt defaultStatement;

    public SwitchStatement(Exp main,Exp e1,Exp e2,IStmt
                           s1, IStmt s2,IStmt defaultStatement){
        this.mainExpression=main;
        this.expression1=e1;
        this.expression2=e2;
        this.statement1=s1;
        this.statement2=s2;
        this.defaultStatement = defaultStatement;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException, IOException, EmptyTableException {
        IStmt converted = new IfStmt(new RelationalExp(mainExpression,expression1,3),statement1,
                new IfStmt(new RelationalExp(mainExpression,expression2,3),statement2,defaultStatement));
        state.getStack().push(converted);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type type1 = mainExpression.typeCheck(typeEnv);
        Type type2 = expression1.typeCheck(typeEnv);
        Type type3 = expression2.typeCheck(typeEnv);
        if(type1.equals(type2) && type1.equals(type3))
        {
            statement1.typeCheck(typeEnv.copy());
            statement2.typeCheck(typeEnv.copy());
            defaultStatement.typeCheck(typeEnv.copy());
            return typeEnv;
        }
        else throw new MyException("in switch statement th etypes are not the same");

    }

    @Override
    public IStmt deepCopy() {
        return new SwitchStatement(mainExpression.deepCopy(),expression1.deepCopy(),expression2.deepCopy(),
                statement1.deepCopy(),statement2.deepCopy(),defaultStatement.deepCopy());
    }

    @Override
    public String toString() {
        return "switch("+mainExpression.toString()+")case( "+expression1.toString()
                +"):"+statement1.toString()+"case( "+expression2.toString()+
                "):"+statement2.toString()+")default :"+defaultStatement.toString();
    }
}
