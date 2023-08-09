package effective.item13;

import lombok.ToString;

import java.util.Arrays;
import java.util.EmptyStackException;

@ToString
class Stack implements Cloneable{
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        this.elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop() {
        if(size == 0) {
            throw new EmptyStackException();
        }

        Object result = elements[--size];
        elements[size] = null;
        return result;
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }

    @Override
    public Stack clone() throws CloneNotSupportedException {
        Stack clonedStack = (Stack) super.clone();
        //clonedStack.elements = this.elements.clone();
        return clonedStack;
    }
}
