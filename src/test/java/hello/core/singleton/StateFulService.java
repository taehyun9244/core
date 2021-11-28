package hello.core.singleton;

public class StateFulService {

    private int price; //상태를 유지하는 필드　stateを維持するィールド

    public void order(String name, int price){
        System.out.println("name = " + name + "price = " + price);
        this.price = price; //여기서 문제가 발생 ここで問題が発生
    }

    public int getPrice(){
        return price;
    }
}
