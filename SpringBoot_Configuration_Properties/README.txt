This app is an example in usage of:
@PropertySources, @PropertySource, @ConfigurationProperties and @Value annotation's.
It is using different Configuration files stored in external ../configs and local ../src/main/resources.


- @PropertySources:
@PropertySources({
        @PropertySource("file:configs/aws/eureka-aws-config.properties"),
        @PropertySource("file:configs/aws/aws-config.properties")
})

Will import to class sources listed under it and us them to assign values.


- @PropertySource:
   As a single data source it will provide class with values from file.properties
   For location under resources folder use:
    @PropertySource("classpath:config/myconfig.properties")
   For locations under main app file use:
    @PropertySource("file:configs/aws/aws-config.properties"

   REMEMBER @PropertySource WILL WORK BY DEFAULT ONLY FOR .properties FILE!!
   FOR .yml YOU NEED TO IMPLEMENT PARSER!!

   Example for .yml:
    See: src/main/java/configuration/YamlPropertySourceFactory.class
         src/main/java/configuration/DbConfigFomYML.class



- @ConfigurationProperties:
    It will provide class with information about PREFIX for particular data source:
    @ConfigurationProperties(prefix = "app") is pointing on all the values beginning with "app."

    For .properties file:
        app.name=name_1_fromConfigAWS
        app.version=version_1_FromConfigAWS
    For .yml file:
        app:
            name: name1
            version: 1.0


- @Value:
    By using @Value annotation we can use two options:
    @Value("${spring.datasource.password}") <--- it will be set from application.properties
    @Value(${DB_PASS}) <--- it will use directly System Variable as a source of injection

    It will inject @Value to field variable from application.properties called "spring.datasource.password_to_char":
        @Value("${spring.datasource.password_to_char}")
        private String passwordEnv;


    It will inject @Value to Constructor from application.properties called "app.secret-key":

        private final String secretKey;

        public SecretService(@Value("${app.secret-key}") String secretKey) {
            this.secretKey = secretKey;
        }

----------------------------------------------------------

Before run remember to set System Variables DB_PASS and PASSWORD_TO_CHAR:
    application.properties is using reassignment of System Variables:
    spring.datasource.password=${DB_PASS:defaultValue1} <--- if DB_PASS don't exist as a System variable,
                                                             will be set as "defaultValue1" and so on.



