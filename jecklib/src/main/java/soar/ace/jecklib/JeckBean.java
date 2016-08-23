package soar.ace.jecklib;

/**
 * Created by gaofei on 2016/8/1.
 */
public class JeckBean {
    private Object object;
    private Class<?> clz;

    public JeckBean(Object object, Class<?> clz) {
        this.object = object;
        this.clz = clz;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Class<?> getClz() {
        return clz;
    }

    public void setClz(Class<?> clz) {
        this.clz = clz;
    }
}
