import java.lang.reflect.Array;
import java.util.Arrays;

public class Stack<T> {
    private int currLength;
    private int maxLength;
    private T[] stackRef;
    
    Stack(int max, Class<T> classT) {
        @SuppressWarnings("unchecked")
        final T[] stack = (T[]) Array.newInstance(classT, max);
        maxLength = max;
        stackRef = stack;
    }
    
    public boolean isEmpty() {
        return currLength==0;
    }

    public boolean isFull() {
        return (currLength == maxLength);
    }

    public void push(T x) throws StackOverflowException {
        
        if(!isFull())
        {
            currLength++;
            stackRef[currLength-1] = x;
        }
        else{
            throw new StackOverflowException();
        }
        
    }

    public T pop() throws StackUnderflowException {
        if(isEmpty())
            throw new StackUnderflowException();
        
        T result = stackRef[currLength-1];
        stackRef[currLength-- - 1] = null;

        return result;
    }

    @Override
    public String toString() {
        return Arrays.toString(stackRef);
    }
}

class StackUnderflowException extends Exception {
    public StackUnderflowException() {
        System.out.println("Stack Underflow");
    }
}

class StackOverflowException extends Exception {
    public StackOverflowException() {
        System.out.println("Stack Overflow");
    }
}