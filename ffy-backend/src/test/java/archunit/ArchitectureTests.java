package archunit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import jakarta.persistence.Entity;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "ia.ffy.foodforyou", importOptions = ImportOption.DoNotIncludeTests.class)
public class ArchitectureTests {

    // Constraint 1: Controllers cannot be accessed by any other layer.
    @ArchTest
    public static final ArchRule controllers_should_not_be_accessed_by_any_other_layer =
            noClasses().that().areAnnotatedWith(RestController.class)
                    .should().onlyBeAccessed().byClassesThat().areNotAnnotatedWith(RestController.class);

    // Constraint 2: Services can be accessed only by controllers and other services.
    @ArchTest
    public static final ArchRule services_should_only_be_accessed_by_controllers_and_other_services =
            classes().that().areAnnotatedWith(Service.class)
                    .should().onlyBeAccessed().byClassesThat().areAnnotatedWith(RestController.class).orShould()
                    .onlyBeAccessed().byClassesThat().areAnnotatedWith(Service.class);

    // Constraint 3: Entities can be accessed only by repositories, services and configuration classes.
    @ArchTest
    public static final ArchRule entities_should_only_be_accessed_by_repositories_services_and_configuration_classes =
            noClasses().that().areAnnotatedWith(Entity.class)
                    .should().onlyBeAccessed().byClassesThat().areAnnotatedWith(Repository.class)
                    .orShould().onlyBeAccessed().byClassesThat().areAnnotatedWith(Service.class)
                    .orShould().onlyBeAccessed().byClassesThat().areAnnotatedWith(Configuration.class);

    // Constraint 4: Repositories can be accessed only by services and configuration classes.
    @ArchTest
    public static final ArchRule repositories_should_only_be_accessed_by_services_and_configuration_classes =
            classes().that().areAnnotatedWith(Repository.class)
                    .should().onlyBeAccessed().byClassesThat().areAnnotatedWith(Configuration.class)
                    .orShould().onlyBeAccessed().byClassesThat().areAnnotatedWith(Service.class);

    // Constraint 5: Classes cannot use field injection.
    @ArchTest
    public static final ArchRule no_field_injection_allowed =
            noClasses().should().dependOnClassesThat().resideInAPackage("java..")
                    .andShould().dependOnClassesThat().areAssignableTo(Field.class);

    // Constraint 6: No class in the project uses System.out methods.
    @ArchTest
    public static final ArchRule no_system_out_allowed =
            noClasses().should().accessClassesThat().resideInAPackage("java.lang")
                    .andShould().accessClassesThat().haveNameMatching(".*PrintStream")
                    .andShould().accessClassesThat().haveNameMatching(".*out.*");
}