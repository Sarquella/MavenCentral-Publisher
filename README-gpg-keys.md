# Create a GPG key pair

### 1.- Generate the key pair

**1.1.** Generate a **GPG** key pair:

```
gpg --full-gen-key
```

**1.2.** Fill the prompted fields:

* `Kind of key`: (1) RSA and RSA.
* `Key size`: 4096.
* `Expiration`: Enter the key expiration or leave it to 0 for a key that never expires.
* `Real name`: Your name.
* `Email address`: Your email.
* `Comment`: Optional.

**1.3.** Enter a **passprhase**

**IMPORTANT:** Your **key id** corresponds to the **8** final characters of the *pub*.

**Note:** You can check the available keys via:
```
gpg --list-keys
```

### 2.-  Upload the *Public* key
Run the following command, replacing with your own key id:

```
gpg --keyserver hkp://pool.sks-keyservers.net --send-keys <key id>
```

### 3.- Extract the *Private* key
Retrieve the private with the following command, replacing with your own key id:

```
gpg --export-secret-keys <key id> > <key id>.gpg
```


