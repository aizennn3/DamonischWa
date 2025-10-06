# Basic optimization
-dontobfuscate
-keepattributes Signature
-keepattributes InnerClasses

# Keep relevant classes
-keep class com.damonisch.wa.** { *; }
-keep class androidx.preference.** { *; }

# Material components
-keep class com.google.android.material.** { *; }
-keep public class * extends androidx.appcompat.view.ActionMode

# AndroidX
-keep class androidx.** { *; }
-keep interface androidx.** { *; }

# Kotlin
-keep class kotlin.** { *; }
-keep class kotlin.Metadata { *; }
-keepclassmembers class **$WhenMappings { <fields>; }