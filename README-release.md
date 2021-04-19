# Promote to Release

### 1.- Promote to *Staging*

**1.1.** Go to [https://s01.oss.sonatype.org](https://s01.oss.sonatype.org) and sign with the same *Sonatype Jira* credentials

**1.2.** Select *Staging Repositories* from the menu and find the one containing the uploaded library

**1.3.** Select the repository and click on **Close** (No need to provide a description)

### 2.- Check that everything is correct

Your library has now been promoted to *Staging*. Before promoting to *Release*, make sure everything is correct for publishing.

**2.1.** Select *Repositories* in the *Sonatype* menu and copy the **Staging** repository url

**2.2.** Add the **Staging** repository to your `build.gradle`

<details>
  <summary>Groovy</summary>
  
``` groovy
repositories {
    maven {
        url = "STAGING_REPO_URL"
    }
}
```
  
</details>
<details open>
  <summary>Kotlin</summary>
  
``` kotlin
repositories {
    maven {
        url = uri("STAGING_REPO_URL")
    }
}
```
  
</details>

**2.3.** Import your library and make sure everything is correct

### 3.- Promote to *Release*
**IMPORTANT:** Once published to *Release*, there is no way to go back or remove it. It is only possible to update it by publishing a new version. Make sure you have double-checked everything in *Staging* is correct.

**3.1.** Select your repository at *Sonatype* again and click on **Release** (No need to provide a description)


