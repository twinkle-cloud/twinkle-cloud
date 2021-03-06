package com.twinkle.cloud.common.compiler;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019-06-20 17:40<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public class JavaStringCompiler {
    private JavaCompiler compiler;
    private StandardJavaFileManager stdManager;

    public JavaStringCompiler() {
        this.compiler = ToolProvider.getSystemJavaCompiler();
        this.stdManager = compiler.getStandardFileManager(null, null, null);
    }

    /**
     * Compile Java sources in memory.
     *
     * @param fileNameToSources A map of sources with their class names.
     * @return The compiled results as Map that contains class name as key,
     * class binary as value.
     * @throws IOException If compile com.twinkle.cloud.common.asm.error.
     */
    public Map<String, byte[]> compile(Map<String, String> fileNameToSources) throws IOException {
        try (MemoryJavaFileManager manager = new MemoryJavaFileManager(stdManager)) {
            List<JavaFileObject> fileObjects = fileNameToSources.entrySet().stream()
                    .map(it -> manager.makeStringSource(it.getKey(), it.getValue()))
                    .collect(Collectors.toList());
            CompilationTask task = compiler.getTask(null, manager, null, null, null, fileObjects);
            Boolean result = task.call();
            if (result == null || !result) {
                throw new RuntimeException("Compilation failed.");
            }
            return manager.getClassBytes();
        }
    }

    /**
     * Load class from compiled classes.
     *
     * @param name       Full class name.
     * @param classBytes Compiled results as a Map.
     * @return The Class instance.
     * @throws ClassNotFoundException If class not found.
     * @throws IOException            If load com.twinkle.cloud.common.asm.error.
     */
    public Class<?> loadClass(String name, Map<String, byte[]> classBytes) throws ClassNotFoundException, IOException {
        try (MemoryClassLoader classLoader = new MemoryClassLoader(classBytes)) {
            return classLoader.loadClass(name);
        }
    }
}
