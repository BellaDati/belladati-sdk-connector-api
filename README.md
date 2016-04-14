# BellaDati SDK Connector Interfaces Definitions

This repository contains the interface definitions of the BellaDati SDK for custom connectors.

Related repository is [belladati-sdk-connector-example](https://github.com/BellaDati/belladati-sdk-connector-example/) containing the sample API implementation.

## Usage

The compiled SDK libraries are available from BellaDati: [Connector API](http://api.belladati.com/sdk-connector/0.9.0/sdk-connector-api-0.9.0.jar), [Connector API Javadoc](http://api.belladati.com/sdk-connector/0.9.0/sdk-connector-api-0.9.0-javadoc.jar) and [sample implementation](http://api.belladati.com/sdk-connector/0.9.0/sdk-connector-example-0.9.0.jar).

For setup instructions, dependencies, and example usage, please refer to the [BellaDati SDK Connector documentation](http://support.belladati.com/techdoc/Connector+SDK) or view the [SDK Connector Javadoc](http://api.belladati.com/sdk-connector/0.9.0/javadoc/).

## Build Instructions

A Java 7 JDK and [Apache Maven](http://maven.apache.org/) are required to build the BellaDati SDK Connector. Maven is included in most Eclipse for Java distributions.

To prepare building the SDK, clone [this repository](https://github.com/BellaDati/belladati-sdk-connector-api).

You will need [GnuPG and a signing key](https://docs.sonatype.org/display/Repository/How+To+Generate+PGP+Signatures+With+Maven) in order to build signed jars. If you're fine with unsigned jars, you can go to each project's `pom.xml` and remove the plugin setup for `maven-gpg-plugin`.

When you're ready, call `mvn clean install` to build each project. Maven will create a `target` directory for this repository, containing the project's jar file and other build artifacts.
