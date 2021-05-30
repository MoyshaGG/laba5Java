package lab5.store;

import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class AbstractStore implements java.io.Serializable, Iterable <Object>

{

    protected int count = 0;
    protected Object[]arr = new Object[3];

    public int getCount() {
        return count;
    }
    public Object[] getArr()
    {
        return Arrays.copyOf(arr,count);
    }

    protected void add(Object newObject)
    {
        if(arr.length == count)
            arr= Arrays.copyOf(arr, count +count/2);
        arr[count++]=newObject;

    }
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for(Object obj : this)
        {
            sb.append(obj).append("\n");
        }
        return sb.toString();
    }

    public void remove(Predicate<Object> prd)
    {
        Iterator<Object> itr = this.iterator();
        while(itr.hasNext())
        {
            Object obj = (Object) itr.next();
            if(prd.test(obj))
            {
                itr.remove();
            }
        }

    }
    public void doForAll(Consumer<Object> cns){
        Iterator<Object> itr = this.iterator();
        while (itr.hasNext()){
            Object obj = (Object) itr.next();
            cns.accept(obj);
        }
    }

    public void doOnlyFor(Predicate<Object> prd, Consumer<Object> cns){
        Iterator<Object> itr= this.iterator();
        while (itr.hasNext()){
            Object obj=(Object) itr.next();
            if (prd.test(obj)){
                cns.accept(obj);
            }
        }
    }

    @Override
    public Iterator<Object> iterator() {
        return new StoreIterator();
    }
    public ListIterator <Object> listIterator()
    {
        return new ListStoreIterator();
    }

    private class StoreIterator implements Iterator<Object> {

        int current = 0;

        @Override
        public boolean hasNext() {
            return current < count;
        }
        @Override
        public Object next() {
            return arr[current++];
        }

        @Override
        public void remove() {
            System.arraycopy(arr, current, arr, current -1, count-- -current--);
        }


        }

    private class ListStoreIterator extends StoreIterator implements ListIterator <Object> {

        @Override
        public boolean hasPrevious() {
            return current>0;
        }

        @Override
        public Object previous() {
            return arr[current--];
        }

        @Override
        public int nextIndex() {

            return current+1;
        }

        @Override
        public int previousIndex() {
            return current -1;
        }

        @Override
        public void set(Object o) {
         arr[current]= o;
        }
        @Override
        public void add(Object o) {
            if(current == arr.length-1)
            {
                arr = Arrays.copyOf(arr,arr.length+1);             }
            System.arraycopy(arr,current,arr,current +1, count - current);
            arr[current]=0;
            current++;
            count++;
        }
    }
}



