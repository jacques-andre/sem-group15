# SET08103 SEM Group 15 Coursework

[![Unit test, coverage and build containers.](https://github.com/jacques-andre/sem-group15/actions/workflows/test-image.yml/badge.svg)](https://github.com/jacques-andre/sem-group15/actions/workflows/test-image.yml)

[![codecov](https://codecov.io/gh/jacques-andre/sem-group15/branch/master/graph/badge.svg?token=S7XK0I86OQ)](https://codecov.io/gh/jacques-andre/sem-group15)

All reports are automatically generated on every merge to release and uploaded to github pages: https://jacques-andre.github.io/sem-group15/


# Tools for development:

## Docker:

**Build an image:**
``docker build -t NAME .``

**Run built image:**
``docker run NAME``

## docker-compose:

Run compose in detached: `docker-compose up -d`

Check logs of a container: `docker logs NAME`

## Build
Build mvn: `mvn -f pom.xml clean compile assembly:single`

## Kaban/Sprint Board
Use the kaban and sprint board to manage issues: https://zube.io


## Lazy Docker:
Use this to understand what is happening with the docker containers easier:

https://github.com/jesseduffield/lazydocker

## Lazygit:
Similar to lazy docker but makes the repo easier to understand

https://github.com/jesseduffield/lazygit
