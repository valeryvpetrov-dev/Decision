module.exports = function (plop) {
    plop.setGenerator('feature-module', {
        description: 'Create a new feature module',
        prompts: [
            {
                type: 'input',
                name: 'moduleName',
                message: 'Feature module name (in kebab case):',
                default: 'my-module',
                validate: (value) => {
                    const kebabCaseRegex = /^[a-z]+(-[a-z0-9]+)*$/;
                    if (!value) return 'Module name is required';
                    if (!kebabCaseRegex.test(value)) {
                        return 'Invalid module name. It should be in kebab-case, using only lowercase letters, digits, and dashes (e.g., "my-feature-module").';
                    }
                    return true;
                },
            },
            {
                type: 'input',
                name: 'packageName',
                message: 'Package name (in dot case):',
                default: 'dev.valeryvpetrov.decision',
                validate: (value) => {
                    const packageNameRegex = /^[a-z]+(\.[a-z][a-z0-9]*)*$/;
                    if (!value) return 'Package name is required';
                    if (!packageNameRegex.test(value)) {
                        return 'Invalid package name. It should be in the form of "com.example.app", using only lowercase letters, digits, and dots. (e.g., "dev.plop.generator")';
                    }
                    return true;
                },
            },
            {
                type: 'input',
                name: 'entityName',
                message: 'Domain entity name (in pascal case):',
                default: 'MyDomain',
                validate: (value) => {
                    const pascalCaseRegex = /^[A-Z][a-zA-Z0-9]*$/;
                    if (!value) return 'Entity name is required';
                    if (!pascalCaseRegex.test(value)) {
                        return 'Invalid entity name. It should be in PascalCase, starting with an uppercase letter and using only letters and digits (e.g., "MyEntityName").';
                    }
                    return true;
                },
            },
        ],
        actions: [
            // Add build.gradle.kts files for each sub-module
            {
                type: 'add',
                path: 'feature/{{moduleName}}/api/build.gradle.kts',
                templateFile: 'generator/plop/feature/module/api/build.gradle.kts.hbs',
            },
            {
                type: 'add',
                path: 'feature/{{moduleName}}/impl/build.gradle.kts',
                templateFile: 'generator/plop/feature/module/impl/build.gradle.kts.hbs',
            },
            {
                type: 'add',
                path: 'feature/{{moduleName}}/di/build.gradle.kts',
                templateFile: 'generator/plop/feature/module/di/build.gradle.kts.hbs',
            },
            {
                type: 'add',
                path: 'feature/{{moduleName}}/presentation/build.gradle.kts',
                templateFile: 'generator/plop/feature/module/presentation/build.gradle.kts.hbs',
            },
            {
                type: 'add',
                path: 'feature/{{moduleName}}/ui/compose/build.gradle.kts',
                templateFile: 'generator/plop/feature/module/ui/compose/build.gradle.kts.hbs',
            },
            {
                type: 'add',
                path: 'feature/{{moduleName}}/sample/build.gradle.kts',
                templateFile: 'generator/plop/feature/module/sample/build.gradle.kts.hbs',
            },
            // Add feature module dependencies to settings.gradle.kts
            {
                type: 'modify',
                path: 'settings.gradle.kts',
                pattern: /\/\/ Do not remove this line\. It is used by generator to put feature-module dependencies/g,
                template: `":feature:{{dashCase moduleName}}:api",\n` +
                          `    ":feature:{{dashCase moduleName}}:impl",\n` +
                          `    ":feature:{{dashCase moduleName}}:di",\n` +
                          `    ":feature:{{dashCase moduleName}}:presentation",\n` +
                          `    ":feature:{{dashCase moduleName}}:ui:compose",\n` +
                          `    ":feature:{{dashCase moduleName}}:sample",\n\n` +
                          `    // Do not remove this line. It is used by generator to put feature-module dependencies`
            },
            // Add base classes to api sub-module
            {
                type: 'add',
                path: 'feature/{{moduleName}}/api/src/commonMain/kotlin/{{pathCase packageName}}/feature/{{snakeCase moduleName}}/api/model/{{entityName}}.kt',
                templateFile: 'generator/plop/feature/module/api/src/commonMain/kotlin/packageName/feature/moduleName/api/model/Entity.kt.hbs',
            },
            {
                type: 'add',
                path: 'feature/{{moduleName}}/api/src/commonMain/kotlin/{{pathCase packageName}}/feature/{{snakeCase moduleName}}/api/repository/{{entityName}}Repository.kt',
                templateFile: 'generator/plop/feature/module/api/src/commonMain/kotlin/packageName/feature/moduleName/api/repository/EntityRepository.kt.hbs',
            },
            // Add base classes to impl sub-module
            {
                type: 'add',
                path: 'feature/{{moduleName}}/impl/src/commonMain/kotlin/{{pathCase packageName}}/feature/{{snakeCase moduleName}}/impl/repository/{{entityName}}RepositoryImpl.kt',
                templateFile: 'generator/plop/feature/module/impl/src/commonMain/kotlin/packageName/feature/moduleName/impl/repository/EntityRepositoryImpl.kt.hbs',
            },
            // Add base classes to di sub-module
            {
                type: 'add',
                path: 'feature/{{moduleName}}/di/src/commonMain/kotlin/{{pathCase packageName}}/feature/{{snakeCase moduleName}}/di/{{entityName}}ComponentModule.kt',
                templateFile: 'generator/plop/feature/module/di/src/commonMain/kotlin/packageName/feature/moduleName/di/EntityComponentModule.kt.hbs',
                path: 'feature/{{moduleName}}/di/src/commonMain/kotlin/{{pathCase packageName}}/feature/{{snakeCase moduleName}}/di/{{entityName}}PresentationModule.kt',
                templateFile: 'generator/plop/feature/module/di/src/commonMain/kotlin/packageName/feature/moduleName/di/EntityPresentationModule.kt.hbs',
            },
            {
                type: 'add',
                path: 'feature/{{moduleName}}/di/src/commonMain/kotlin/{{pathCase packageName}}/feature/{{snakeCase moduleName}}/di/{{entityName}}FeatureModule.kt',
                templateFile: 'generator/plop/feature/module/di/src/commonMain/kotlin/packageName/feature/moduleName/di/EntityFeatureModule.kt.hbs',
            },
            {
                type: 'add',
                path: 'feature/{{moduleName}}/di/src/commonMain/kotlin/{{pathCase packageName}}/feature/{{snakeCase moduleName}}/di/{{entityName}}ImplModule.kt',
                templateFile: 'generator/plop/feature/module/di/src/commonMain/kotlin/packageName/feature/moduleName/di/EntityImplModule.kt.hbs',
            },
            {
                type: 'modify',
                path: 'base/di/src/commonMain/kotlin/{{pathCase packageName}}/base/di/Qualifier.kt',
                pattern: /\/\/ Do not remove this line\. It is used by generator to put feature-module qualifiers/g,
                template: `sealed class {{pascalCase moduleName}} : Feature() {\n\n` +
                          `            object StoreName : {{pascalCase moduleName}}()\n` +
                          `            object StoreFactoryProvider : {{pascalCase moduleName}}()\n` +
                          `        }\n\n` +
                          `        // Do not remove this line. It is used by generator to put feature-module qualifiers`
            },
            // Add base classes to presentation sub-module
            {
                type: 'add',
                path: 'feature/{{moduleName}}/presentation/src/commonMain/kotlin/{{pathCase packageName}}/feature/{{snakeCase moduleName}}/presentation/component/{{entityName}}Component.kt',
                templateFile: 'generator/plop/feature/module/presentation/src/commonMain/kotlin/packageName/feature/moduleName/presentation/component/EntityComponent.kt.hbs',
            },
            {
                type: 'add',
                path: 'feature/{{moduleName}}/presentation/src/commonMain/kotlin/{{pathCase packageName}}/feature/{{snakeCase moduleName}}/presentation/component/{{entityName}}ComponentImpl.kt',
                templateFile: 'generator/plop/feature/module/presentation/src/commonMain/kotlin/packageName/feature/moduleName/presentation/component/EntityComponentImpl.kt.hbs',
            },
            {
                type: 'add',
                path: 'feature/{{moduleName}}/presentation/src/commonMain/kotlin/{{pathCase packageName}}/feature/{{snakeCase moduleName}}/presentation/component/{{entityName}}ComponentPreview.kt',
                templateFile: 'generator/plop/feature/module/presentation/src/commonMain/kotlin/packageName/feature/moduleName/presentation/component/EntityComponentPreview.kt.hbs',
            },
            {
                type: 'add',
                path: 'feature/{{moduleName}}/presentation/src/commonMain/kotlin/{{pathCase packageName}}/feature/{{snakeCase moduleName}}/presentation/mvi/{{entityName}}Executor.kt',
                templateFile: 'generator/plop/feature/module/presentation/src/commonMain/kotlin/packageName/feature/moduleName/presentation/mvi/EntityExecutor.kt.hbs',
            },
            {
                type: 'add',
                path: 'feature/{{moduleName}}/presentation/src/commonMain/kotlin/{{pathCase packageName}}/feature/{{snakeCase moduleName}}/presentation/mvi/{{entityName}}Intent.kt',
                templateFile: 'generator/plop/feature/module/presentation/src/commonMain/kotlin/packageName/feature/moduleName/presentation/mvi/EntityIntent.kt.hbs',
            },
            {
                type: 'add',
                path: 'feature/{{moduleName}}/presentation/src/commonMain/kotlin/{{pathCase packageName}}/feature/{{snakeCase moduleName}}/presentation/mvi/{{entityName}}Label.kt',
                templateFile: 'generator/plop/feature/module/presentation/src/commonMain/kotlin/packageName/feature/moduleName/presentation/mvi/EntityLabel.kt.hbs',
            },
            {
                type: 'add',
                path: 'feature/{{moduleName}}/presentation/src/commonMain/kotlin/{{pathCase packageName}}/feature/{{snakeCase moduleName}}/presentation/mvi/{{entityName}}Message.kt',
                templateFile: 'generator/plop/feature/module/presentation/src/commonMain/kotlin/packageName/feature/moduleName/presentation/mvi/EntityMessage.kt.hbs',
            },
            {
                type: 'add',
                path: 'feature/{{moduleName}}/presentation/src/commonMain/kotlin/{{pathCase packageName}}/feature/{{snakeCase moduleName}}/presentation/mvi/{{entityName}}Reducer.kt',
                templateFile: 'generator/plop/feature/module/presentation/src/commonMain/kotlin/packageName/feature/moduleName/presentation/mvi/EntityReducer.kt.hbs',
            },
            {
                type: 'add',
                path: 'feature/{{moduleName}}/presentation/src/commonMain/kotlin/{{pathCase packageName}}/feature/{{snakeCase moduleName}}/presentation/mvi/{{entityName}}State.kt',
                templateFile: 'generator/plop/feature/module/presentation/src/commonMain/kotlin/packageName/feature/moduleName/presentation/mvi/EntityState.kt.hbs',
            },
            {
                type: 'add',
                path: 'feature/{{moduleName}}/presentation/src/commonMain/kotlin/{{pathCase packageName}}/feature/{{snakeCase moduleName}}/presentation/mvi/{{entityName}}StoreFactory.kt',
                templateFile: 'generator/plop/feature/module/presentation/src/commonMain/kotlin/packageName/feature/moduleName/presentation/mvi/EntityStoreFactory.kt.hbs',
            },
            {
                type: 'add',
                path: 'feature/{{moduleName}}/presentation/src/commonMain/moko-resources/base/strings.xml',
                templateFile: 'generator/plop/feature/module/presentation/src/commonMain/moko-resources/base/strings.xml.hbs',
            },
            // Add base classes to ui:compose sub-module
            {
                type: 'add',
                path: 'feature/{{moduleName}}/ui/compose/src/commonMain/kotlin/{{pathCase packageName}}/feature/{{snakeCase moduleName}}/ui/compose/screen/{{entityName}}Screen.kt',
                templateFile: 'generator/plop/feature/module/ui/compose/src/commonMain/kotlin/packageName/feature/moduleName/ui/compose/screen/EntityScreen.kt.hbs',
            },
            // Add base classes to sample module for commonMain
            {
                type: 'addMany',
                destination: 'feature/{{moduleName}}/sample/src/commonMain/kotlin/{{pathCase packageName}}/feature/{{snakeCase moduleName}}/sample/',
                base: 'generator/plop/feature/module/sample/src/commonMain/kotlin/packageName/feature/moduleName/sample/',
                templateFiles: 'generator/plop/feature/module/sample/src/commonMain/kotlin/packageName/feature/moduleName/sample/*.hbs',
                abortOnFail: false,
            },
            {
                type: 'addMany',
                destination: 'feature/{{moduleName}}/sample/src/commonMain/composeResources/',
                base: 'generator/plop/feature/module/sample/src/commonMain/composeResources/',
                templateFiles: 'generator/plop/feature/module/sample/src/commonMain/composeResources/**/*.xml',
                abortOnFail: false,
            },
            // Add base classes to sample module for androidMain
            {
                type: 'add',
                path: 'feature/{{moduleName}}/sample/src/androidMain/AndroidManifest.xml',
                templateFile: 'generator/plop/feature/module/sample/src/androidMain/AndroidManifest.xml.hbs',
            },
            {
                type: 'addMany',
                destination: 'feature/{{moduleName}}/sample/src/androidMain/kotlin/{{pathCase packageName}}/feature/{{snakeCase moduleName}}/sample/',
                base: 'generator/plop/feature/module/sample/src/androidMain/kotlin/packageName/feature/moduleName/sample/',
                templateFiles: 'generator/plop/feature/module/sample/src/androidMain/kotlin/packageName/feature/moduleName/sample/*.hbs',
                abortOnFail: false,
            },
            {
                type: 'addMany',
                destination: 'feature/{{moduleName}}/sample/src/androidMain/res/',
                base: 'generator/plop/feature/module/sample/src/androidMain/res/',
                templateFiles: 'generator/plop/feature/module/sample/src/androidMain/res/**/*.xml',
                abortOnFail: false,
            },
            {
                type: 'addMany',
                destination: 'feature/{{moduleName}}/sample/src/androidMain/res/',
                base: 'generator/plop/feature/module/sample/src/androidMain/res/',
                templateFiles: 'generator/plop/feature/module/sample/src/androidMain/res/**/*.png',
                abortOnFail: false,
            },
            // Add base classes to sample module for iosMain
            {
                type: 'add',
                path: 'feature/{{moduleName}}/sample/src/iosMain/kotlin/MainViewController.kt',
                templateFile: 'generator/plop/feature/module/sample/src/iosMain/kotlin/MainViewController.kt.hbs',
            },
            // Add base classes to sample module for jvmMain
            {
                type: 'add',
                path: 'feature/{{moduleName}}/sample/src/jvmMain/kotlin/main.kt',
                templateFile: 'generator/plop/feature/module/sample/src/jvmMain/kotlin/main.kt.hbs',
            }
        ],
    });
};
