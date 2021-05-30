package lab5.test;

import lab5.model.*;
import lab5.store.ProductStore;
import lab5.store.WoodDirectory;

import javax.swing.*;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.function.Predicate;

public class testApp {

    private WoodDirectory wd = new WoodDirectory();
    //Каталог для брусів
    private ProductStore ps = new ProductStore();

    Predicate<Object> prd = new Predicate<Object>()
    {
        @Override
        public boolean test(Object o) {
            return false;

        }
    };


    public void startApp() throws Exception {

        wd.add(new Wood(0, "Мавпобаобаб", 0.3f));
        wd.add(new Wood(1, "Куркодерево", 0.6f));
        wd.add(new Wood(2, "Птаходуб", 0.9f));

        try {
            ps.add(new Timber(wd.get(0), 5f, 0.5f, 0.4f));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Введення продуктiв", JOptionPane.ERROR_MESSAGE);
        }
        try {
            ps.add(new Timber(wd.get(1), 10f, 0.6f, 0.7f));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Введення продуктiв", JOptionPane.ERROR_MESSAGE);
        }
        try {
            ps.add(new Timber(wd.get(0), 20f, 20f, 20f));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Введення продуктiв", JOptionPane.ERROR_MESSAGE);
        }
        try {
            ps.add(new Cylinder(wd.get(2), 3f, 0.3f));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Введення продуктiв", JOptionPane.ERROR_MESSAGE);
        }
        try {
            ps.add(new Waste(51f));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Введення продуктiв", JOptionPane.ERROR_MESSAGE);
        }
        // перелік продуктів
        System.out.println(wd);
        System.out.println(ps);
        // вага продуктів
        System.out.printf("Загальна вага: %1.3f", calcWeight());

        System.out.println();
        System.out.println("Перелік виробів виробів до вилучення");
        System.out.println(ps);
        Iterator<Object> itr = ps.iterator();
        while (itr.hasNext()) {
            IWeight obj = (IWeight) itr.next();
            if (obj.weight() > 54) itr.remove();
        }
        System.out.println("Перелік виробів після вилучення");
        System.out.println(ps.toString());
// literator
////////////////////////////////////////////////////////////////////////////////////////
// listIterator
        System.out.println();
        System.out.println("Перелік виробів до вилучення");
        System.out.println(ps.toString());
        ListIterator<Object> listItr = ps.listIterator();
        while (listItr.hasNext()) {
            IWeight obj = (IWeight) listItr.next();
            if (obj.weight() < 1) listItr.remove();
        }
        System.out.println("Перелік виробів після вилучення");
        System.out.println(ps.toString());
/////////////// 5lab
        System.out.println("До вилучення");

        System.out.println(ps);
        float maxWeight = 50f;
        ps.remove((t) -> t instanceof Waste && ((IWeight) t).weight() > maxWeight);
        ps.remove((t) -> t instanceof Timber && ((IWeight) t).weight() > maxWeight);
        System.out.println("Після вилучення");
        System.out.println(ps);


    }

    private float calcWeight(){
        float fullWeight = 0;
        for (Object timber : ps.getArr()){
            fullWeight+=((IWeight)timber).weight();
        }
        return fullWeight;
    }

}
