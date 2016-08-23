package soar.ace.jecklib;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by gaofei on 2016/7/29.
 */
public class JeckHelper {

    public final static int TYPE_FIELD = 0;
    public final static int TYPE_METHOD = 1;
    public final static int CURPARAMS = 3;

    private static HashMap<Integer ,JeckBean>  msp = new HashMap<>();


    public static void jeck(Object object){
        Field[] fields = object.getClass().getDeclaredFields();
        Method[] methods = object.getClass().getDeclaredMethods();
        Constructor[] constructors =object.getClass().getDeclaredConstructors();
        for(int a = 0 ; a < fields.length ; a++){
            if(fields[a].isAnnotationPresent(StringJeck.class)){
                msp.put(msp.size()+1,new JeckBean(fields[a] , StringJeck.class));
            }
        }

        for(int b = 0 ; b < methods.length ; b++){
            if(methods[b].isAnnotationPresent(MethodJeck.class)){
                msp.put(msp.size()+1 , new JeckBean(methods[b], MethodJeck.class));
            }
        }

        for(int c = 0 ; c < constructors.length ; c++){
            if(constructors[c].isAnnotationPresent(ParamsJeck.class)){
                msp.put(msp.size()+1, new JeckBean(constructors[c] , ParamsJeck.class));
            }
        }

      jeckAll(object);

    }


    private static void jeckAll(Object object){
        if(msp.size() == 0){
            return ;
        }

        for (int x = 1 ; x <= msp.size() ; x++){
            JeckBean jeckBean = msp.get(x);
            if(jeckBean.getObject() instanceof Field){
                jeckField((Field)jeckBean.getObject() , object);
            }else if(jeckBean.getObject() instanceof Method){
                jeckMethod((Method)jeckBean.getObject() , object);
            }else if(jeckBean.getObject() instanceof Constructor){
                jeckParams((Constructor)jeckBean.getObject() , object);
            }
        }
    }

    private  static void jeckField(Field field , Object object){
        try {
            StringJeck check = field.getAnnotation(StringJeck.class);
            field.setAccessible(true);
            String value = check.value();
            if (field.getType().getSimpleName().contains("int")) {
                field.set(object, Integer.valueOf(value));
            } else if (field.getType().getSimpleName().contains("String")) {
                field.set(object, value);
            } else if (field.getType().getSimpleName().contains("boolean")) {
                field.set(object, Boolean.valueOf(value));
            } else if (field.getType().getSimpleName().contains("float")) {
                field.set(object, Float.valueOf(value));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void jeckMethod(Method method ,Object object){
        try {
            MethodJeck methodJeck = method.getAnnotation(MethodJeck.class);
            int id = methodJeck.id();
            method.setAccessible(true);
            method.invoke(object , id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void jeckParams(Constructor constructor , Object object){
        try {

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
