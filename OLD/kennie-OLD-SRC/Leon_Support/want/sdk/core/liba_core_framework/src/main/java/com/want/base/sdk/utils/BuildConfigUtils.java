package com.want.base.sdk.utils;

import android.content.Context;

import java.lang.reflect.Field;

/**
 * <b>Project:</b> android<br>
 * <b>Create Date:</b> 16/3/3<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * Utils for BuildConfig.
 * <p/>
 * links: http://stackoverflow.com/questions/21365928/gradle-how-to-use-buildconfig-in-an-android-library-with-a-flag-that-gets-set
 * <br>
 */
public class BuildConfigUtils {

    private BuildConfigUtils() {
        // hide
    }


    /**
     * Gets a field from the project's BuildConfig. This is useful when, for example, flavors
     * are used at the project level to set custom fields.
     *
     * @param packageName The name of the application package
     * @param fieldName   The name of the field-to-access
     *
     * @return The value of the field, or {@code null} if the field is not found.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBuildConfigValue(String packageName, String fieldName) {
        try {
            Class<?> clazz = Class.forName(packageName + ".BuildConfig");
            Field field = clazz.getField(fieldName);
            return (T) field.get(null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets a field from the project's BuildConfig by the resname define in the build.gradle file.
     *
     * @param context   {@link Context}
     * @param resName   resname define in the build.gradle
     * @param filedName the name of the field-to-access
     * @param <T>       the result type
     *
     * @return
     */
    public static <T> T getBuildConfigValue(Context context, String resName, String filedName) {
        int resId = context.getResources()
                           .getIdentifier(resName, "string", context.getPackageName());
        return getBuildConfigValue(context.getString(resId), filedName);
    }
}
