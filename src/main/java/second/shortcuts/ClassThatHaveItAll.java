package second.shortcuts;


import java.util.List;

public class ClassThatHaveItAll implements InterfaceOne {
    List<Long> list;
    String name;
    Integer number;

    public ClassThatHaveItAll(List<Long> list, String name, Integer number) {
        this.list = list;
        this.name = name;
        this.number = number;
    }

    public List<Long> getList() {
        return list;
    }

    public void setList(List<Long> list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public void printMe(String info) {
    }
}
