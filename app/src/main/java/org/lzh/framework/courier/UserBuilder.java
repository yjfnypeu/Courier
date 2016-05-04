package org.lzh.framework.courier;

/**
 * @author Administrator
 */
public abstract class UserBuilder<T extends UserBuilder> {
    SubFragment_Builder builder = SubFragment_Builder.create();

    public T setUsername(String username) {
        builder.setUsername(username);
        return (T) this;
    }

    public T setPassword(String password) {
        builder.setPassword(password);
        return (T) this;
    }

    protected abstract static class Builder<E extends Builder> {
        SubFragment_Builder builder = SubFragment_Builder.create();
        public static void test() {
            SubFragment_Builder subFragment_builder = SubFragment_Builder.create();
        }
    }
}