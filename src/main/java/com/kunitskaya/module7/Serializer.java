package com.kunitskaya.module7;

import java.io.*;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;


public class Serializer<T> {

    public void serialize(String serFilePath, T instance) {
        LOGGER.info("Serializing instance of: " + instance.getClass().getSimpleName());

        try (FileOutputStream fileOutputStream = new FileOutputStream(serFilePath);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)
        ) {
            objectOutputStream.writeObject(instance);
        } catch (IOException e) {
            e.printStackTrace();
        }

        LOGGER.info("Serialized successfully to: " + serFilePath);

    }

    public T deserialize(String serFilePath, Class<T> type) {
        LOGGER.info("Deserializing from: " + serFilePath);
        try (
                FileInputStream fileInputStream = new FileInputStream(serFilePath);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)
        ) {
         return type.cast(objectInputStream.readObject());

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        LOGGER.info("Deserialized successfully instance of: " + type.getClass().getSimpleName());
        return null;
    }
    //    public static <T> T instantiate(Class<T> clazz) {
    //        T instance = null;
    //        try {
    //            Constructor<?> constructor = clazz.getConstructor();
    //            instance = (T) constructor.newInstance();
    //            LOGGER.info(String.format(SUCCESS_MESSAGE, clazz.getSimpleName(), StringUtils.EMPTY));
    //
    //        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
    //            LOGGER.error(String.format(ERROR_MESSAGE, clazz.getSimpleName()));
    //            e.printStackTrace();
    //        }
    //        return instance;
    //    }
}
