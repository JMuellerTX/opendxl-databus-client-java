# OpenDXL Databus Java Client Library

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Build Status](https://github.com/opendxl/opendxl-databus-client-java/workflows/build/badge.svg?branch=master)](https://github.com/opendxl/opendxl-databus-client-java/actions)

## Overview

The OpenDXL Databus Java client library is used to consume and produce records from/to a [Data Exchange Layer](http://www.mcafee.com/us/solutions/data-exchange-layer.aspx)
(DXL) Streaming Service.

## Documentation

See the
[OpenDXL Databus Java Client Library Documentation](https://opendxl.github.io/opendxl-databus-client-java/docs/index.html)
for API documentation and examples.

## Installation

To start using the OpenDXL Databus Java client library:

* Download the [Latest Release](https://github.com/opendxl/opendxl-databus-client-java/releases/latest)
* Extract the release .zip file
* View the `README.html` file located at the root of the extracted files.
  * The `README` links to the documentation which includes installation instructions, API details, and samples.
  * The SDK documentation is also available on-line [here](https://opendxl.github.io/opendxl-databus-client-java/docs/index.html).

## Maven Repository

Visit the [OpenDXL Databus Java Client Maven Repository](https://search.maven.org/artifact/com.opendxl/dxldatabusclient) for
access to all released versions including the appropriate dependency syntax for a large number of management
systems (Maven, Gradle, SBT, Ivy, Grape, etc.).

Maven:

```xml
<dependency>
  <groupId>com.opendxl</groupId>
  <artifactId>dxldatabusclient</artifactId>
  <version>2.4.12</version>
</dependency>
```
or Gradle:
```groovy
compile 'com.opendxl:dxldatabusclient:2.4.12'
```

## Branches

Each branch builds and tests the library with one JDK; the library API and the wire format are the same on all
branches. `build.gradle` pins the JDK with a Gradle toolchain (`def javaVersion = N`), so `./gradlew build` uses that
JDK whatever JDK runs Gradle, and downloads it when it is not installed (`settings.gradle`, foojay resolver).

| Branch   | JDK (toolchain, bytecode) | CI                                        |
|----------|---------------------------|-------------------------------------------|
| `master` | 21                        | `.github/workflows/main.yml`, JDK 21 only |
| `jdk17`  | 17                        | same workflow, JDK 17 only                |
| `jdk11`  | 11                        | same workflow, JDK 11 only                |
| `jdk8`   | 8                         | same workflow, JDK 8 only                 |

Fixes land on `master` first and are then cherry-picked into `jdk17` -> `jdk11` -> `jdk8` where they apply
(the branches differ only in `build.gradle`, the workflow triggers and JDK specific build settings):

```sh
git checkout master && git pull
# fix, commit, push; wait until the master run is green:
gh run list --branch master

git checkout jdk17 && git pull && git cherry-pick -x <sha> && git push origin jdk17
git checkout jdk11 && git pull && git cherry-pick -x <sha> && git push origin jdk11
git checkout jdk8  && git pull && git cherry-pick -x <sha> && git push origin jdk8

# run and check the branch CI (or start it by hand with "gh workflow run main.yml --ref <branch>")
gh run list --branch jdk17
gh run view <run-id> --log-failed
```

Resolve cherry-pick conflicts in `build.gradle` in favour of the branch's `javaVersion` line and JDK specific
settings. Do not merge the branches into each other.

## Bugs and Feedback

For bugs, questions and discussions please use the
[GitHub Issues](https://github.com/opendxl/opendxl-databus-client-java/issues).

## LICENSE

Copyright 2024 Musarubra, LLC

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.

