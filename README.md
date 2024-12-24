# MPTest

Find out how to use multiprofile maven projects

Setup:

| profile name | database   |
|--------------|------------|
| h2           | h2         |
| postgresql   | postgresql |

## Commandline

| action | command                                                 | description           |
|--------|---------------------------------------------------------|-----------------------|
| test   | mvn clean test -P h2 -DactiveProfile=h2                 | Test using h2 profile |
| test   | mvn clean test -P postgresql -DactiveProfile=postgresql | Test using h2 profile |

