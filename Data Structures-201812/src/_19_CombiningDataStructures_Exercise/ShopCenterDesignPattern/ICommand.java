package _19_CombiningDataStructures_Exercise.ShopCenterDesignPattern;

public interface ICommand<T> {
    T execute(ShoppingCenter shoppingCenter);
}
