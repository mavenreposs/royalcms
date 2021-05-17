package io.github.mavenreposs.royalcms.component.facades;

import cn.stackbox.phpserialize.PhpDecoder;
import com.alibaba.fastjson.JSONArray;
import com.marcospassos.phpserializer.*;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class RC_Serializer {

    private static final Serializer serializer;

    static {
        serializer = initialize();
    }

    private static Serializer initialize() {
        Serializer serializer = new SerializerBuilder()

            // Adds a custom exclusion strategy to determine which field
            // should be serialized or not (default: no exclusion)
//            .addExclusionStrategy(new MyCustomExclusionStrategy())

            // Sets the naming strategy to convert the name of classes
            // and fields from Java to PHP (default: PsrNamingStrategy)
            .setNamingStrategy(new UnderscoreNamingStrategy())

            // Registers all builtin adapters, using UTF-8 for encoding strings
            // (default: all built-in adapters, UTF-8 charset)
            .registerBuiltinAdapters()

            // Sets ISO-8859-1 as the default charset
            //
            // Notice that setCharset() register a new adapter configured with the
            // specified charset. Calling setCharset() prior to registerBuiltinAdapters()
            // will have no effect as the previous configuration will get overriden
            // by the default adapter which encodes strings in UTF-8.
            .setCharset(StandardCharsets.ISO_8859_1)

            // Register a custom type adapter
            .registerAdapter(CustomObject.class, new MyCustomAdapter())

            // Creates the serialized based on the given configuration
            .build();

        return serializer;
    }

    private RC_Serializer() {

    }

    /**
     * 将对象序列化字符串
     * @param object POJO JAVA对象
     * @return 字符串
     */
    public static String serialize(Object object) {
        return serializer.serialize(object);
    }

    /**
     * 将字符串反序列化对象
     * @param serialized PHP序列化后的字符串
     * @return Map对象
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> unserialize(String serialized) {
        serialized = serialized.replaceAll("style=\"([\\s\\S]+);\"", "");
        PhpDecoder decoder = PhpDecoder.newInstance(serialized);
        JSONArray ret = decoder.decode();
        return (Map<String, Object>) ret.get(0);
    }

    public static class UnderscoreNamingStrategy implements NamingStrategy
    {
        public String getClassName(Class type)
        {
            return type.getName();
        }

        public String getFieldName(Field field)
        {
            if (Modifier.isPrivate(field.getModifiers())) {
                return "_" + field.getName();
            }

            return field.getName();
        }
    }

    public static class EnumTypeAdapter implements TypeAdapter<Enum>
    {
        public void write(Enum value, Writer writer, Context context)
        {
            writer.writeString(value.name());
        }
    }

    public static class MyCustomAdapter implements TypeAdapter<CustomObject>
    {
        @Override
        public void write(CustomObject object, Writer writer, Context context)
        {
            int reference = context.getReference(object);

            if (reference > 0) {
                writer.writeObjectReference(reference);

                return;
            }

            context.setReference(reference, object);

            // Custom serialization logic
        }
    }

    public static class CustomObject {

    }


}
