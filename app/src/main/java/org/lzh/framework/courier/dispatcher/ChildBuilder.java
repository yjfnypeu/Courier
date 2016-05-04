package org.lzh.framework.courier.dispatcher;

/**
 * @author Kale
 * @date 2016/5/4
 */
public class ChildBuilder extends ParentBuilder<ChildBuilder> {

    public ChildBuilder title() {
        return this;
    }
}
