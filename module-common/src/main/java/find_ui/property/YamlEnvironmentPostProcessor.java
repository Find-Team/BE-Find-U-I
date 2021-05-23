package find_ui.property;

import static find_ui.constant.Constant.BETA;
import static find_ui.constant.Constant.LOCAL;
import static find_ui.constant.Constant.REAL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Profiles;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import lombok.extern.slf4j.Slf4j;

/**
 * resources/META-INF/spring.factories에 YamlEnvironmentPostProcessor.class를 선언해줘야한다.
 */
@Slf4j
public class YamlEnvironmentPostProcessor implements EnvironmentPostProcessor {

    private static final String[] propertyUris = { "classpath*:config/custom/*.yml", "classpath*:*.yml" };
    private static final String[] acceptsProfiles = { LOCAL, BETA, REAL };

    private final YamlPropertySourceLoader loader = new YamlPropertySourceLoader();
    private final ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {

        // Set Default Profile
        boolean isNotValidProfileActive = !environment.acceptsProfiles(Profiles.of(acceptsProfiles));

        if (isNotValidProfileActive) {
            environment.setActiveProfiles(LOCAL);
        }

        // Load Custom *.yml
        try {
            List<Resource> resourceList = new ArrayList<>();
            for (String propertyUri : propertyUris) {
                resourceList.addAll(List.of(resourcePatternResolver.getResources(propertyUri)));
            }

            resourceList.stream().map(this::loadYaml).forEach(them -> {
                if (them != null) {
                    for (PropertySource<?> it : them) {
                        environment.getPropertySources().addLast(it);
                    }
                }
            });
        } catch (Exception e) {
            throw new BeanCreationException(e.getMessage(), e);
        }
    }

    private List<PropertySource<?>> loadYaml(Resource resource) {
        if (!resource.exists()) {
            throw new IllegalArgumentException("Resource " + resource + " does not exist");
        }
        try {
            return loader.load(resource.getURL().toString(), resource);
        } catch (IOException ex) {
            throw new IllegalStateException("Failed to load yaml configuration from " + resource, ex);
        }
    }
}