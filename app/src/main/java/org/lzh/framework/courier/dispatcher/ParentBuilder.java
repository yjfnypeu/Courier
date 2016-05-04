package org.lzh.framework.courier.dispatcher;

/**
 * @author Kale
 * @date 2016/5/4
 */
public class ParentBuilder<T>{

    public T message() {
        return (T) this;
    }

    public void build() {
    }
}
