package Model;

public class SymTableEntry {
    private String varName;
    private String value;

    public SymTableEntry(String varName, String value) {
        this.varName = varName;
        this.value = value;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName = varName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SymTableEntry{" +
                "varName='" + varName + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
