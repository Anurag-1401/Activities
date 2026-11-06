CNS Java Practicals
===================

1. ShiftCipher.java
2. MonoCipher.java
3. OneTimePad.java
4. MAC.java (HMAC-SHA256)
5. HashFunction.java (SHA-256)
6. DES.java
7. AES.java (AES-CBC with IV)
8. DiffieHellman.java

Compile and run one file at a time, for example:
    javac ShiftCipher.java
    java ShiftCipher

The programs use Java's built-in libraries only; no third-party library is required.

Notes:
- OneTimePad.java is the alphabet/mod-26 classroom version. For perfect secrecy, a real OTP requires a truly random key as long as the message, kept secret and never reused.
- DES is obsolete and insecure; included for coursework only.
- AES-CBC is for learning. Real applications should authenticate ciphertext (e.g., AES-GCM).
- Diffie-Hellman uses tiny educational values, not production-secure parameters.
