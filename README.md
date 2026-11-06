# Lon y Samuel

App Android sencilla: escribes **"Lon"** en el buscador y muestra el mensaje
**"Samuel, ¡cállate!"**. Ignora mayúsculas y tildes.

## Compilar el APK

Requisitos: JDK 17 y Android SDK (API 34). Desde Android Studio: *Open* en esta
carpeta y luego *Build > Build APK(s)*. O por terminal:

```bash
export ANDROID_HOME=/ruta/a/tu/Android/Sdk
./gradlew assembleDebug
```

El APK queda en `app/build/outputs/apk/debug/app-debug.apk`.
