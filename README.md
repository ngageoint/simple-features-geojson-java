# Simple Features GeoJSON Java

#### Simple Features GeoJSON Lib ####

The Simple Features Libraries were developed at the [National Geospatial-Intelligence Agency (NGA)](http://www.nga.mil/) in collaboration with [BIT Systems](http://www.bit-sys.com/). The government has "unlimited rights" and is releasing this software to increase the impact of government investments by providing developers with the opportunity to take things in new directions. The software use, modification, and distribution rights are stipulated within the [MIT license](http://choosealicense.com/licenses/mit/).

### Pull Requests ###
If you'd like to contribute to this project, please make a pull request. We'll review the pull request and discuss the changes. All pull request contributions to this project will be released under the MIT license.

Software source code previously released under an open source license and then modified by NGA staff is considered a "joint work" (see 17 USC § 101); it is partially copyrighted, partially public domain, and as a whole is protected by the copyrights of the non-government authors and must be released according to the terms of the original open source license.

### About ###

[Simple Features GeoJSON](http://ngageoint.github.io/simple-features-geojson-java/) is a Java library for writing and reading [Simple Feature](https://github.com/ngageoint/simple-features-java) Geometries to and from GeoJSON.

### Usage ###

View the latest [Javadoc](http://ngageoint.github.io/simple-features-geojson-java/docs/api/)

#### Read ####

```java

//String content = ...    

Geometry geometry = FeatureConverter.toGeometry(content);
mil.nga.sf.Geometry simpleGeometry = geometry.getGeometry();

/* Read as a generic GeoJSON object, Feature, or Feature Collection */
//GeoJsonObject geoJsonObject = FeatureConverter.toGeoJsonObject(content);
//Feature feature = FeatureConverter.toFeature(content);
//FeatureCollection featureCollection = FeatureConverter.toFeatureCollection(content);

```

#### Write ####

```java

//Geometry geometry = ...

String content = FeatureConverter.toStringValue(geometry);

Feature feature = FeatureConverter.toFeature(geometry);
String featureContent = FeatureConverter.toStringValue(feature);

FeatureCollection featureCollection = FeatureConverter.toFeatureCollection(geometry);
String featureCollectionContent = FeatureConverter.toStringValue(featureCollection);

Map<String, Object> contentMap = FeatureConverter.toMap(geometry);

```

### Installation ###

Pull from the [Maven Central Repository](http://search.maven.org/#artifactdetails|mil.nga.sf|sf-geojson|2.0.4|jar) (JAR, POM, Source, Javadoc)

```xml

<dependency>
    <groupId>mil.nga.sf</groupId>
    <artifactId>sf-geojson</artifactId>
    <version>2.0.4</version>
</dependency>

```

### Build ###

[![Build & Test](https://github.com/ngageoint/simple-features-geojson-java/workflows/Build%20&%20Test/badge.svg)](https://github.com/ngageoint/simple-features-geojson-java/actions?query=workflow%3A%22Build+%26+Test%22)

Build this repository using Eclipse and/or Maven:

    mvn clean install

### Remote Dependencies ###

* [Simple Features](https://github.com/ngageoint/simple-features-java) (The MIT License (MIT)) - Simple Features Lib
* [Jackson Data Processor](https://github.com/FasterXML/jackson-databind) (Apache License, Version 2.0) - Jackson data-binding functionality and tree-model
