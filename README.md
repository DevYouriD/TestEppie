# TestEppie

The test app used to try different methods and technics within android (kotlin, _some parts are
still in java_) development.  
For questions, contact one of the developers.

## Requirements

You need to meet the requirements to run the application or if you want to make changes to the
project yourself.

- Your favorite IDE;
- Android SDK;
- Add `app/src/main/res/raw/secrets.properties`file with `fire_base_url`;  
  This is the location where you can store the database URL (This isn't added to the version
  control).

## ToDo (wishlist)

### Functional

- Add Db functionality;
    - <s>Firebase</s>;
        - User management.
            - Forgot password;
            - Validate phone numbers (all input is now allowed);
            - <s>Create user</s>;
            - <s>Authenticate user</s>.
    - SQL;
    - .....
- Add navigation menu.
    - Add _about_ page;
    - Add _account_ page.
- _Main_ application functionality.

### Non functional

- Add commenting page (anonymously?/ via account credentials?);
- Change java code to Kotlin or vise versa, but only use one language;
- Cleanup code, add directories, remove redundant code;
- Add default coding style, for example google code style;
- Add tests (itest/ unit tests);
- Add code quality checks (PMD, google non-prone, Checkstyle, _don't know what is used for android
  apps_);
- Add documentation in code - add javadocs.

## Extra

Tip, connect to your own phone for better performance. In that case you don't need the emulators.