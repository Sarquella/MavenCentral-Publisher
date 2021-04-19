# MavenCentral Publisher

A gradle plugin to easily upload libraries to MavenCentral for distribution.

# Requirements

//TODO

# Usage

### 1.- Apply the plugin

Apply the plugin in your `build.gradle`:

<details>
  <summary>Groovy</summary>
  
``` groovy
plugins {
	id 'dev.sarquella.mavencentral-publisher' version '1.0'
}
```
  
</details>
<details open>
  <summary>Kotlin</summary>
  
``` kotlin
plugins {
	id("dev.sarquella.mavencentral-publisher") version "1.0"
}
```
  
</details>

### 2.- Configure credentials

Add the following to your `local.properties` file, or create your own `*.properties` file (e.g. `maven-publish.properties`). If you use a custom properties file, you should specify its path in the publication configuration (See next step).

> Important: Make sure it is listed in your `.gitignore` file to avoid publishing your credentials

```
ossrhUsername=OSSRH_USERNAME
ossrhPassword=OSSR_PASSWORD
signingKeyId=SIGNING_KEY_ID
signingKeyPassword=SIGNING_KEY_PASSWORD
signingKeyFile=SIGNING_KEY_FILE.gpg
```
Replace with your own values:
* `OSSRH_USERNAME` and `OSSR_PASSWORD` are your *SonaType Jira*'s account credentials.
* `SIGNING_KEY_ID`, `SIGNING_KEY_PASSWORD` and `SIGNING_KEY_FILE` are your *GPG* key id, passphrase and file path respectively.

### 3.- Configure publication
Add the following to your `build.gradle` and replace with your own values:

**Note:** Your library will be finally published as `groupId:artifact:version`

> Only the mandatory parameters are listed in this example. For a full list of all the available parameters, as well as a brief explanation of each, see the Parameters section

<details>
  <summary>Groovy</summary>
  
  ``` groovy
mavenCentralPublication {
	groupId = 'GROUP_ID'
	artifact = 'ARTIFACT'
	version = 'VERSION'

	repoUrl = 'REPO_URL'
	vcsUrl = 'VCS_URL'

	developer {
		id = 'DEVELOPER_ID'
		name = 'DEVELOPER_NAME'
		email = 'DEVELOPER_EMAIL'
	}

	license {
		name = 'LICENSE_NAME'
		url = 'LICENSE_URL'
	}
}
  ```
  
</details>

<details open>
  <summary>Kotlin</summary>
  
  ``` kotlin
mavenCentralPublication {
	groupId.set("GROUP_ID")
	artifact.set("ARTIFACT")
	version.set("VERSION")

	repoUrl.set("REPO_URL")
	vcsUrl.set("VCS_URL")

	developer {
		id.set("DEVELOPER_ID")
		name.set("DEVELOPER_NAME")
		email.set("DEVELOPER_EMAIL")
	}

	license {
		name.set("LICENSE_NAME")
		url.set("LICENSE_URL")
	}
}
  ```
  
</details>

### 4.- Publish to MavenCentral
Run the following command to upload the library to *MavenCentral*

```
./gradlew publishToMavenCentral
```

### 5.- Promote to *Release*
//TODO

# Parameters

* `groupId`: Library's group name (e.g., *com.company.library*)

* `artifact`: Library's concrete artifact name

* `version`: Library's current version (e.g., *1.0.0*) 

* `description` [*Optional*]: Short description about the library 

* `repoUrl`: Code repository url (e.g., *https://github.com/<username/<repository>*)

* `vcsUrl`: Version control url (e.g., *https://github.com/<username/<repository>.git*)

* `webUrl` [*Optional*]: Library's website url. If not set, defaults to `repoUrl` value.

* `developer`
	* `id`: Library's developer id
	* `name`: Library's developer name
	* `email`: Library's developer email

* `license`
	* `name`: Library's license name (e.g., *The Apache Software License, Version 2.0*) 
	* `url`: Library's license url (e.g., *http://www.apache.org/licenses/LICENSE-2.0.txt*)

* `propertiesFile` [*Optional*]: Path to custom **.properties file* where credentials are defined. If not set, defaults to *local.properties*.

# License
//TODO


  
