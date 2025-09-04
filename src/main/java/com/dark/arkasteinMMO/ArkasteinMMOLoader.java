package com.dark.arkasteinMMO;

import io.papermc.paper.plugin.loader.PluginClasspathBuilder;
import io.papermc.paper.plugin.loader.PluginLoader;
import io.papermc.paper.plugin.loader.library.impl.JarLibrary;
import io.papermc.paper.plugin.loader.library.impl.MavenLibraryResolver;
import org.apache.maven.artifact.DefaultArtifact;
import org.apache.maven.model.Dependency;

import java.nio.file.Path;

public class ArkasteinMMOLoader implements PluginLoader {
    @Override
    public void classloader(PluginClasspathBuilder classpathBuilder) {
        classpathBuilder.addLibrary(new JarLibrary(Path.of("dependency.jar")));

        //MavenLibraryResolver resolver = new MavenLibraryResolver();
        //resolver.addDependency(new Dependency(new DefaultArtifact("com.example:example:version"), null));
        //resolver.addRepository(new RemoteRepository.Builder("paper", "default", "https://repo.papermc.io/repository/maven-public/").build());

        //classpathBuilder.addLibrary(resolver);
    }
}
