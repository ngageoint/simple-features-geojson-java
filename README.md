# GeoPackage WKB Java

#### GeoPackage Well Known Binary Lib ####

The [GeoPackage Libraries](http://ngageoint.github.io/GeoPackage/) were developed at the [National Geospatial-Intelligence Agency (NGA)](http://www.nga.mil/) in collaboration with [BIT Systems](http://www.bit-sys.com/). The government has "unlimited rights" and is releasing this software to increase the impact of government investments by providing developers with the opportunity to take things in new directions. The software use, modification, and distribution rights are stipulated within the [MIT license](http://choosealicense.com/licenses/mit/).

### Pull Requests ###
If you'd like to contribute to this project, please make a pull request. We'll review the pull request and discuss the changes. All pull request contributions to this project will be released under the MIT license.

Software source code previously released under an open source license and then modified by NGA staff is considered a "joint work" (see 17 USC § 101); it is partially copyrighted, partially public domain, and as a whole is protected by the copyrights of the non-government authors and must be released according to the terms of the original open source license.

### About ###

[WKB](http://ngageoint.github.io/geopackage-wkb-java/) is a Java library for writing and reading Well-Known Binary Geometries to and from bytes. The library includes a hierarchy of Geometry objects. Although developed as part of the [GeoPackage Libraries](http://ngageoint.github.io/GeoPackage/), this library does not contain GeoPackage functionality and can be used separately.

### Usage ###

View the latest [Javadoc](http://ngageoint.github.io/geopackage-wkb-java/docs/api/)

#### Read ####

```java

//byte[] bytes = ...    

ByteReader reader = new ByteReader(bytes);
reader.setByteOrder(ByteOrder.BIG_ENDIAN);
Geometry geometry = WkbGeometryReader.readGeometry(reader);
GeometryType geometryType = geometry.getGeometryType();

```

#### Write ####

```java

//Geometry geometry = ...

ByteWriter writer = new ByteWriter();
writer.setByteOrder(ByteOrder.BIG_ENDIAN);
WkbGeometryWriter.writeGeometry(writer, geometry);
byte[] bytes = writer.getBytes();
writer.close();

```

### Installation ###

Pull from the [Maven Central Repository](http://search.maven.org/#artifactdetails|mil.nga|wkb|1.0.2|jar) (JAR, POM, Source, Javadoc)

```xml

<dependency>
    <groupId>mil.nga</groupId>
    <artifactId>wkb</artifactId>
    <version>1.0.2</version>
</dependency>

```

### Build ###

Build this repository using Eclipse and/or Maven:

    mvn clean install
