![github actions](https://github.com/jacques-andre/sem-group15/actions/workflows/update-image.yml/badge.svg)
[![codecov](https://codecov.io/gh/jacques-andre/sem-group15/branch/master/graph/badge.svg?token=S7XK0I86OQ)](https://codecov.io/gh/jacques-andre/sem-group15)

## Docker:

**Build an image:**
``docker build -t NAME .``

**Run built image:**
``docker run NAME``

## Running docker-compose

Run compose in detached: `docker-compose up -d`
Check logs of a container: `docker logs NAME`

## Build
To buld run this: `mvn -f pom.xml clean compile assembly:single`

## Kaban/Sprint Board
Use the kaban and sprint board to manage issues.
`zube.io`

## Lazy Docker:
Use this to understand what is happening with the docker containers easier:

https://github.com/jesseduffield/lazydocker
